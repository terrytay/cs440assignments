package simpledb.query;

import simpledb.record.RID;

/**
 *
 * @author hermaa02
 */
public class RenameScan implements Scan {
   private Scan s;
   private Predicate pred;
   
   public RenameScan(Scan s, Predicate pred) {
      this.s = s;
      this.pred = pred;
   }
   
   // Scan methods
   
   public void beforeFirst() {
      s.beforeFirst();
   }
   
   /**
    * Move to the next record satisfying the predicate.
    * The method repeatedly calls next on the underlying scan
    * until a suitable record is found, or the underlying scan
    * contains no more records.
    * @see simpledb.query.Scan#next()
    */
   public boolean next() {
      while (s.next())
         if (pred.isSatisfied(s))
         return true;
      return false;
   }
   
   public void close() {
      s.close();
   }
   
   public Constant getVal(String fldname) {
      return s.getVal(fldname);
   }
   
   public int getInt(String fldname) {
      return s.getInt(fldname);
   }
   
   public String getString(String fldname) {
      return s.getString(fldname);
   }
   
   public boolean hasField(String fldname) {
      return s.hasField(fldname);
   }
   
   // UpdateScan methods
   
   public void setVal(String fldname, Constant val) {
      UpdateScan us = (UpdateScan) s;
      us.setVal(fldname, val);
   }
   
   public void setInt(String fldname, int val) {
      UpdateScan us = (UpdateScan) s;
      us.setInt(fldname, val);
   }
   
   public void setString(String fldname, String val) {
      UpdateScan us = (UpdateScan) s;
      us.setString(fldname, val);
   }
   
   public void delete() {
      UpdateScan us = (UpdateScan) s;
      us.delete();
   }
   
   public void insert() {
      UpdateScan us = (UpdateScan) s;
      us.insert();
   }
   
   public RID getRid() {
      UpdateScan us = (UpdateScan) s;
      return us.getRid();
   }
   
   public void moveToRid(RID rid) {
      UpdateScan us = (UpdateScan) s;
      us.moveToRid(rid);
   }
}