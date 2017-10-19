package luthercollege;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aaron
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class WriterDriver {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        DatabaseWriter dw = new DatabaseWriter();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
    
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UNIVERSITY?" + "user=root&password=test4pass");
            // make sure it's added to Library/Build Path in project properties

            //TODO: Create database with sql file.
            System.out.println("Connecting");
            
        } catch (SQLException ex){
            System.out.println("Failed");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            //Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        /***************************
        *  Read departments from TXT 
        *  and populate the database
        ****************************/
        ArrayList<Department> deptList = dw.readDepartmentFromTxt("data/lc_departments.txt");
        try {
            dw.writeDepartmentTable(deptList);
        } catch (SQLException ex) {
            System.out.println("Fail");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);

        }
    
        /***************************
        *  Read Semester from TXT 
        *  and populate the database
        ****************************/
        ArrayList<Semester> semList = dw.readSemesterFromTxt("data/semester.txt");
        try {
            dw.writeSemesterTable(semList);
        } catch (SQLException ex) {
            System.out.println("Fail");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);

        }

        /***************************
        *  Read Semester from TXT 
        *  and populate the database
        ****************************/
        ArrayList<Course> courseList = dw.readCourseFromTxt("data/lc_courses.txt");
        try {
            dw.writeCourseTable(courseList);
        } catch (SQLException ex) {
            System.out.println("Fail");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
