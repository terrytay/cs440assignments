package simpledb.buffer;

import java.util.ArrayList;
import simpledb.file.*;

/**
 * Manages the pinning and unpinning of buffers to blocks.
 *
 * @author Edward Sciore
 *
 */
class BasicBufferMgr {

    private Buffer[] bufferpool;
    private int numAvailable;
    private int strategy;
    private int counterPin;
    private int counterUnpin;
    private boolean clockStart = false;
    
    private int clockNextFree;
    

    /**
     * Creates a buffer manager having the specified number of buffer slots.
     * This constructor depends on both the {@link FileMgr} and
     * {@link simpledb.log.LogMgr LogMgr} objects that it gets from the class
     * {@link simpledb.server.SimpleDB}. Those objects are created during system
     * initialization. Thus this constructor cannot be called until
     * {@link simpledb.server.SimpleDB#initFileAndLogMgr(String)} or is called
     * first.
     *
     * @param numbuffs the number of buffer slots to allocate
     */
    BasicBufferMgr(int numbuffs) {
        bufferpool = new Buffer[numbuffs];
        numAvailable = numbuffs;
        for (int i = 0; i < numbuffs; i++) {
            bufferpool[i] = new Buffer();
        }
    }

    /**
     * Flushes the dirty buffers modified by the specified transaction.
     *
     * @param txnum the transaction's id number
     */
    synchronized void flushAll(int txnum) {
        for (Buffer buff : bufferpool) {
            if (buff.isModifiedBy(txnum)) {
                buff.flush();
            }
        }
    }
    
    synchronized void flushAll() {
        for (Buffer buff : bufferpool) {
            buff.flush();
        }
    }

    /**
     * Pins a buffer to the specified block. If there is already a buffer
     * assigned to that block then that buffer is used; otherwise, an unpinned
     * buffer from the pool is chosen. Returns a null value if there are no
     * available buffers.
     *
     * @param blk a reference to a disk block
     * @return the pinned buffer
     */
    synchronized Buffer pin(Block blk) {
        Buffer buff = findExistingBuffer(blk);
        if (buff == null) {
            buff = chooseUnpinnedBuffer();
            if (buff == null) {
                return null;
            }
            buff.assignToBlock(blk);
        }
        if (!buff.isPinned()) {
            numAvailable--;
        }
        counterPin++;
        buff.pin(counterPin);
        return buff;
    }

    /**
     * Allocates a new block in the specified file, and pins a buffer to it.
     * Returns null (without allocating the block) if there are no available
     * buffers.
     *
     * @param filename the name of the file
     * @param fmtr a pageformatter object, used to format the new block
     * @return the pinned buffer
     */
    synchronized Buffer pinNew(String filename, PageFormatter fmtr) {
        Buffer buff = chooseUnpinnedBuffer();
        if (buff == null) {
            return null;
        }
        buff.assignToNew(filename, fmtr);
        numAvailable--;
        counterPin++;
        buff.pin(counterPin);
        return buff;
    }

    /**
     * Unpins the specified buffer.
     *
     * @param buff the buffer to be unpinned
     */
    synchronized void unpin(Buffer buff) {
        counterUnpin++;
        buff.unpin(counterUnpin);
        if (!buff.isPinned()) {
            numAvailable++;
        }
    }

    /**
     * Returns the number of available (i.e. unpinned) buffers.
     *
     * @return the number of available buffers
     */
    int available() {
        return numAvailable;
    }

    
    private Buffer findExistingBuffer(Block blk) {
        for (Buffer buff : bufferpool) {
            Block b = buff.block();
            if (b != null && b.equals(blk)) {
                return buff;
            }
        }
        return null;
    }

    private Buffer chooseUnpinnedBuffer() {
        switch (this.strategy) {
            case 0:
                return useNaiveStrategy();
            case 1:
                return useFIFOStrategy();
            case 2:
                return useLRUStrategy();
            case 3:
                return useClockStrategy();
            default:
                return null;
        }
    }

    /**
     * @return Allocated buffers
     */
    public Buffer[] getBuffers() {
        return this.bufferpool;
    }

    /**
     * Set buffer selection strategy
     *
     * @param s (0 - Naive, 1 - FIFO, 2 - LRU, 3 - Clock)
     */
    public void setStrategy(int s) {
        this.strategy = s;
    }

    /**
     * Naive buffer selection strategy
     *
     * @return
     */
    private Buffer useNaiveStrategy() {
        for (Buffer buff : bufferpool) {
            if (!buff.isPinned()) {
                return buff;
            }
        }
        return null;
    }

    /**
     * FIFO buffer selection strategy
     *
     * @return buffer
     */
    private Buffer useFIFOStrategy() {
        Buffer availFirst = null;
        for (Buffer buff : bufferpool) {
            if (!buff.isPinned()) {
                if (availFirst == null) {
                   availFirst = buff;
                } else {
                    /* Only set availFirst if it less than the previous one.
                    ** Since we have set it to the first available one in the
                    ** beginning on the if statement, we know that we aren't
                    ** going to pin any pinned buffers.
                    */
                    if (buff.getTimeIn() < availFirst.getTimeIn()) {
                        availFirst = buff;
                    }
                }
            }
        }
        return availFirst;
    }

    /**
     * LRU buffer selection strategy
     *
     * @return buffer
     */
    private Buffer useLRUStrategy() {
        Buffer availFirst = null;
        for (Buffer buff : bufferpool) {
            if (!buff.isPinned()) {
                if (availFirst == null) {
                   availFirst = buff;
                } else {
                    // Like FIFO, we keep setting availFirst to the lowest 
                    // timeOut
                    if (buff.getTimeOut() < availFirst.getTimeOut()) {
                        availFirst = buff;
                    }
                }
            }
        }
        return availFirst;
    }

    /**
     * Clock buffer selection strategy
     *
     * @return buffer
     */
    private Buffer useClockStrategy() {
        ArrayList<Integer> loopList = new ArrayList<Integer>();
        // Only run the following code when you haven't done this before.
        if (!clockStart) {
            int indexCount = 0;
            // Get the first available free buffer, and change the flags.
            for (Buffer buff : bufferpool) {
                indexCount++;
                if (!buff.isPinned()) {
                    clockStart = true;
                    clockNextFree = indexCount;
                    break;
                }
            }
        }
        /* Create the list that have elements of [2,3,0,1]
        ** if we start at 2. 
        */
        for (int i = clockNextFree; i < bufferpool.length; i++) {
            loopList.add(i);
        }
        for (int i = 0; i < clockNextFree; i++) {
            loopList.add(i);
        }
        /* Then we loop through this list instead of the buffers, 
        ** and then index into the bufferpool.
        */
        Buffer availFirst = null;
        for (int index : loopList) {
            Buffer buff = bufferpool[index];
            if (!buff.isPinned()) {
                // this will only be set once. The availFirst is the first
                // available unpinned buffer.
                if (availFirst == null) {
                   availFirst = buff;
                } else {
                    // We want to set the clockNextFree now, but we don't want
                    // to go over the available length.
                    availFirst = buff;
                    if (clockNextFree + 1 > bufferpool.length) {
                        clockNextFree = 0;
                    } else {
                        clockNextFree = index+1;
                    }
                    
                    return availFirst;
                }
            }
        }
        return availFirst;

    }
}
