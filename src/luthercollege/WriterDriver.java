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
            System.out.println("Failed in creating connection: driver");
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
            System.out.println("Fail in driver: department");
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
            System.out.println("Fail in driver: semester");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);

        }

        /***************************
        *  Read Course from TXT 
        *  and populate the database
        ****************************/
        ArrayList<Course> courseList = dw.readCourseFromTxt("data/lc_courses.txt");
        try {
            dw.writeCourseTable(courseList);
        } catch (SQLException ex) {
            System.out.println("Fail in driver: course");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);

        }
        


        /***************************
        *  Read Major from TXT 
        *  and populate the database
        ****************************/
        ArrayList<Major> majorList = dw.readMajorFromTxt("data/lc_majors.txt");
        try {
            dw.writeMajorTable(majorList);
        } catch (SQLException ex) {
            System.out.println("Fail in driver: major");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);

        }
        
        /***************************
        *  Generate location names and
        *  and populate the database
        ****************************/
        ArrayList<Location> locationList = dw.generateLocations();
        try {
            dw.writeLocationTable(locationList);
        } catch (SQLException ex) { 
            System.out.println("Fail in driver: major");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }

        /***************************
        *  Read Faculty names from TXT 
        *  and populate the database
        ****************************/
        ArrayList<Faculty> facultyList = dw.readFacultyFromTxt("data/lc_faculty.txt");
        try {
            dw.writeFacultyTable(facultyList);
        } catch (SQLException ex) { 
            System.out.println("Fail in driver: major");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        /***************************
        *  Read Student names from TXT 
        *  and populate the database
        ****************************/
        ArrayList<Student> studentList = dw.readStudentFromTxt("data/names.txt");
        try {
            dw.writeStudentTable(studentList);
        } catch (SQLException ex) { 
            System.out.println("Fail in driver: major");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /***************************
        *   Generate sections
        *  and populate the database
        ****************************/
        ArrayList<Section> sectionList = dw.generateSections();
        try {
            dw.writeSectionTable(sectionList);
        } catch (SQLException ex) { 
            System.out.println("Fail in driver: major");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        
        /***************************
        *   Generate enrollment
        *  and populate the database
        ****************************/
        ArrayList<Enrollment> enrollList = dw.generateEnrollment();
        try {
            dw.writeEnrollTable(enrollList);
        } catch (SQLException ex) { 
            System.out.println("Fail in driver: major");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
