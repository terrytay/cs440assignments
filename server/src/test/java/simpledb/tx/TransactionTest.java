package simpledb.tx;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import simpledb.tx.Transaction;
import simpledb.server.SimpleDB;

/**
 *
 * @author hermaa02
 */
public class TransactionTest extends Thread {
    private static Integer lock = new Integer(0);
    
    @Before
    public void setUp() {
      SimpleDB.init("testdb");
    }
  
    @After
    public void tearDown() {
    }

    public TransactionTest() {
    }

    /**
     * Create 10 transactions in 10 different threads. 
     * Don't commit any of them right away 
     * (i.e. make all of them sleep for a little while). 
     * Then create 5 more transactions in their own threads. 
     * All 5 should get queued up on the wait list while 
     * waiting to do the checkpoint. Then the original 10 should complete, 
     * the checkpoint should occur, and the final 5 threads should complete.
     */
    @Test
    public void QCPUnitTest1() {
        
        try {
            for (int i = 0; i < 10; i++) {
                Transaction transaction = new Transaction();
                Thread thread = new Thread(transaction);
                thread.start();
                
                transaction.commit();
                thread.sleep(1000);
            }

            Thread.sleep(2000);
            
            for (int i = 0; i < 10; i++) {
                Thread.sleep(2000);
                Transaction transaction = new Transaction();
                Thread thread = new Thread(transaction);
                thread.start();
                
                transaction.commit();
                thread.sleep(1000);
            }

        } catch (Exception ex) {}
        
    }
    
    /**
     * This is a variant of QCPUnitTest1 where no 
     * other transactions wait after the initial 10. 
     * They should all wait to commit so the checkpoint
     * is delayed.Then the first 10 should complete 
     * and the checkpoint should occur.
     */
    @Test
    public void QCPUnitTest2() {
        System.out.println("Test 2");
        try {
            for (int i = 0; i < 9; i++) {
                Transaction transaction = new Transaction();
                Thread thread = new Thread(transaction);
                thread.start();
                
                thread.sleep(1000);
            }
            Thread.sleep(2000);
        
        System.out.println(Transaction.transactionList.size());
            
        for (Transaction tran : Transaction.transactionList) {
//        while (!Transaction.transactionList.isEmpty()) {
//            tran.commit();
//            Thread.sleep(2000);
            
            Thread thread = new Thread(tran);
            thread.start();

            tran.commit();
            thread.sleep(1000);
            
        }
            
            

        } catch (Exception ex) {}
        
    }
}
