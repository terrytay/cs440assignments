
package simpledb.query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import simpledb.parse.Parser;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

/**
 *
 * @author yasiro01
 */
public class RenamePlanTest {
  
  public RenamePlanTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
    SimpleDB.init("studentdb");
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

    
  @Test
  public void testRenamePlan() {
    System.out.println("Rename");
//    String qry = "select sname from student join dept on majorid = did";
//    Parser p = new Parser(qry);
    Transaction tx = new Transaction();
    Plan studentTblPlan = new TablePlan("student", tx);
    tx.commit();
//    Plan p = SimpleDB.planner().createQueryPlan(qry, tx);
    Plan renamePlan = new RenamePlan(studentTblPlan,studentTblPlan.schema().fields(), "sname", "studentName");
    Scan renameScan = renamePlan.open();
    System.out.println(renamePlan.schema().fields());
    while (renameScan.next()) {
      for (String field: renamePlan.schema().fields()) {
        System.out.printf("%10s", renameScan.getVal(field).toString());
      }
      System.out.println();
    }
  }
}