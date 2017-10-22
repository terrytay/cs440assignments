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
        
        try {
            
            //used a little regex and find/replace to change
            //my .sql file into the following code
            Statement statement = conn.createStatement();

            statement.executeUpdate("DROP DATABASE IF EXISTS UNIVERSITY;");
            statement.executeUpdate("CREATE DATABASE UNIVERSITY;");

            statement.executeUpdate("create table UNIVERSITY.DEPARTMENT ("
                + "id int NOT NULL auto_increment,"
                + "name text,"
                + "building text,"

                + "PRIMARY KEY (id)"
                +");");

            statement.executeUpdate("create table UNIVERSITY.SEMESTER ("
                + "id int NOT NULL auto_increment,"
                + "year int,"
                + "season text,"

                + "PRIMARY KEY (id)"
                +");");

            statement.executeUpdate("create table UNIVERSITY.COURSE ("
                + "id int NOT NULL auto_increment,"
                + "department int,"
                + "abbreviation text,"
                + "number int,"
                + "title text,"
                + "credits int,"

                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (department) REFERENCES DEPARTMENT(id)"
                +");");

            statement.executeUpdate("create table UNIVERSITY.LOCATION ("
                + "id int NOT NULL auto_increment,"
                + "building text,"
                + "room int,"
                + "purpose text,"

                + "PRIMARY KEY (id)"
                +");");

            statement.executeUpdate("create table UNIVERSITY.MAJOR ("
                + "id int NOT NULL auto_increment,"
                + "department int,"
                + "name text,"

                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (department) REFERENCES DEPARTMENT(id)"
                +");");

            statement.executeUpdate("create table UNIVERSITY.FACULTY ("
                + "id int NOT NULL auto_increment,"
                + "name text,"
                + "department int,"
                + "startDate int,"
                + "endDate int null,"
                + "office int,"

                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (department) REFERENCES DEPARTMENT(id),"
                + "FOREIGN KEY (startDate) REFERENCES SEMESTER(id),"
                + "FOREIGN KEY (endDate) REFERENCES SEMESTER(id),"
                + "FOREIGN KEY (office) REFERENCES LOCATION(id)"
                +");");


            statement.executeUpdate("create table UNIVERSITY.SECTION ("
                + "id int NOT NULL auto_increment,"
                + "course int,"
                + "instructor int,"
                + "offered int,"
                + "location int,"
                + "startHour int,"

                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (course) REFERENCES COURSE(id),"
                + "FOREIGN KEY (instructor) REFERENCES FACULTY(id),"
                + "FOREIGN KEY (offered) REFERENCES SEMESTER(id),"
                + "FOREIGN KEY (location) REFERENCES LOCATION(id)"
                +");");


            statement.executeUpdate("create table UNIVERSITY.STUDENT ("
                + "id int NOT NULL auto_increment,"
                + "name text,"
                + "graduationDate int,"
                + "major int NULL,"
                + "adviser int,"

                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (graduationDate) REFERENCES SEMESTER(id),"
                + "FOREIGN KEY (major) REFERENCES MAJOR(id),"
                + "FOREIGN KEY (adviser) REFERENCES FACULTY(id)"
                +");");

            statement.executeUpdate("create table UNIVERSITY.ENROLLMENT ("
                + "id int NOT NULL auto_increment,"
                + "student int,"
                + "section int,"
                + "grade text,"

                + "PRIMARY KEY (id),"
                + "FOREIGN KEY (student) REFERENCES STUDENT(id),"
                + "FOREIGN KEY (section) REFERENCES SECTION(id)"
                +");");

            
        } catch (SQLException ex) {
            System.out.println("Failed in creating the tables");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        

        /***************************
        *  Read departments from TXT 
        *  and populate the database
        ****************************/
        ArrayList<Department> deptList = dw.readDepartmentFromTxt("data/lc_departments.txt");
        try {
            System.out.println("adding departments");
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
            System.out.println("adding semester");
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
            System.out.println("adding courses");
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
            System.out.println("adding majors");
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
            System.out.println("adding locations");
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
            System.out.println("adding faculty");
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
            System.out.println("adding students");
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
            System.out.println("adding sections");
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
            System.out.println("adding enrollment");
            dw.writeEnrollTable(enrollList);
        } catch (SQLException ex) { 
            System.out.println("Fail in driver: major");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            // Logger.getLogger(DatabaseWriterDriver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
           
        
            
            //used a little regex and find/replace to change
            //my .sql file into the following code
            System.out.println("creating the views");
            Statement statement = conn.createStatement();


            statement.executeUpdate("CREATE VIEW UNIVERSITY.GRAD_FALL AS "
                + "select st.name "
                + "from UNIVERSITY.STUDENT st, UNIVERSITY.SEMESTER se "
                + "where st.graduationDate = se.id and se.year = 2018 "
                + "and se.season = 'FALL';");


            statement.executeUpdate("CREATE VIEW UNIVERSITY.CURRENT_COURSES AS "
                + "SELECT c.abbreviation, c.number, c.title "
                + "FROM UNIVERSITY.SECTION sec, UNIVERSITY.SEMESTER sem, UNIVERSITY.COURSE c "
                + "WHERE sec.offered = sem.id "
                    + "and c.id = sec.course "
                    + "and sem.year = 2017 " 
                    + "and sem.season = 'FALL';");

            statement.executeUpdate("CREATE VIEW UNIVERSITY.FAILED_CURRENT_SPRING AS "
                + "select distinct stu.name "
                + "from UNIVERSITY.STUDENT stu, UNIVERSITY.ENROLLMENT en, UNIVERSITY.SEMESTER sem, UNIVERSITY.SECTION sec "
                + "WHERE stu.id = en.student "
                    + "and sec.id = en.section "
                    + "and en.grade = 'F' "
                    + "and sem.id = sec.offered "
                    + "and sem.year = 2017 "
                    + "and sem.season = 'SPRING';");


            statement.executeUpdate("CREATE VIEW UNIVERSITY.CLASS_LOCATIONS AS "
                + "SELECT c.abbreviation, c.number, loc.building, loc.room, loc.purpose, sec.startHour "
                + "FROM UNIVERSITY.SECTION sec, UNIVERSITY.LOCATION loc, UNIVERSITY.COURSE c "
                + "where sec.location = loc.id "
                    + "and c.id = sec.course;");

            statement.executeUpdate("CREATE VIEW UNIVERSITY.SEMESTER_GRADES AS "
                + "SELECT stu.name, c.title, en.grade "
                + "FROM UNIVERSITY.SECTION sec, UNIVERSITY.SEMESTER sem, UNIVERSITY.COURSE c, "
                    + "UNIVERSITY.STUDENT stu, UNIVERSITY.ENROLLMENT en "
                + "WHERE sec.offered = sem.id "
                    + "and c.id = sec.course "
                    + "and stu.id = en.student "
                    + "and sec.id = en.section "
                    + "and sem.year = 2017 "
                    + "and sem.season = 'FALL' "
                + "ORDER BY sec.id;");
            
        } catch(SQLException ex) {
            System.out.println("failed in views");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
