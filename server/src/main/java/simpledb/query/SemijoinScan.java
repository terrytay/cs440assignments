/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledb.query;

/**
 *
 * @author hermaa02
 */
public class SemijoinScan implements Scan {
   private Scan product; 
   private Predicate pred;
    
   public SemijoinScan(Scan s1, Scan s2, Predicate pred) {
      this.product = new ProductScan(s1, s2); 
      this.pred = pred; 
   }

    @Override
    public void beforeFirst() {
        product.beforeFirst(); 
    }

    @Override
    public boolean next() {
      while (product.next())
         if (pred.isSatisfied(product))
         return true;
      return false;
    }

    @Override
    public void close() {
        product.close(); 
    }

    @Override
    public Constant getVal(String fldname) {
      return product.getVal(fldname); 
    }

    @Override
    public int getInt(String fldname) {
      return product.getInt(fldname); 
    }

    @Override
    public String getString(String fldname) {
      return product.getString(fldname); 
    }

    @Override
    public boolean hasField(String fldname) {
        return product.hasField(fldname);
    }  
}