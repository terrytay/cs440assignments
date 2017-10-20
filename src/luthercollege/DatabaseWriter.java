/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luthercollege;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aaron
 */
public class DatabaseWriter {
    
    Connection db_connection = null;

    public DatabaseWriter() {
        
        /* 
        * MySQL db connection
        */
        try {
            this.db_connection = DriverManager.getConnection 
                ("jdbc:mysql://localhost:3306/UNIVERSITY?" + "user=root&password=test4pass");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
        
    /*****************
     * Read Department from .txt
     * @param filename
     *****************/
    public ArrayList<Department> readDepartmentFromTxt(String filename) {
        ArrayList<Department> deptList = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                String[] department = fs.nextLine().split("\\|");
                Department dept = new Department(department[0],department[3]);
                deptList.add(dept);
//                System.out.println(dept);
            }
        } catch (IOException ex) {
            System.out.println("Fail");
            //Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return deptList;
    }
    
    /*******************************
     * Populate Department table
     * @param db_filename 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeDepartmentTable(ArrayList<Department> deptList) throws SQLException {
        for (Department department: deptList) {
//            Statement statement = this.db_connection.createStatement();
            
            String sql = "insert into UNIVERSITY.DEPARTMENT (name, building) VALUES(?, ?)"; 
                   
            PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
            
            statement_prepared.setString(1, department.getName());
            statement_prepared.setString(2, department.getBuilding());
            
            statement_prepared.executeUpdate();
        }

    }
    
    /*****************
     * Read Semester from .txt
     * @param filename
     *****************/
    public ArrayList<Semester> readSemesterFromTxt(String filename) {
        
        ArrayList<Semester> semesterList = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                String[] semester = fs.nextLine().split(" ");
                Semester sem = new Semester(semester[1],semester[0]);
                semesterList.add(sem);
                System.out.println(sem);
            }
        } catch (IOException ex) {
            System.out.println("Fail in read semester table");
            //Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return semesterList;
    }
    
        /*******************************
     * Populate Department table
     * @param db_filename 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeSemesterTable(ArrayList<Semester> semList) throws SQLException {
        for (Semester sem: semList) {
//            Statement statement = this.db_connection.createStatement();
            
            String sql = "insert into UNIVERSITY.SEMESTER (year, season) VALUES(?, ?)"; 
                    
                   
            PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
            
            statement_prepared.setString(1, sem.getYear());
            statement_prepared.setString(2, sem.getSeason());
            
            statement_prepared.executeUpdate();
        }
        
//        this.db_connection.close();
    }
    
    /*****************
     * Read Course from .txt
     * @param filename
     *****************/
    public ArrayList<Course> readCourseFromTxt(String filename) {
        String abbrv; 
        String number;
        String title; 
        StringBuilder stringArray = new StringBuilder();
        
            Map<String, String> dictionary = new HashMap<String, String>()
                {{
                    put("ACCTG", "Economics, Accounting, and Management");
                    put("AFRS", "Africana Studies");
                    put("AS", "Modern Languages, Literatures and Linguistics");
                    put("ANTH", "Sociology, Anthropology, Social Work");
                    put("ATHTR", "Health and Physical Education");
                    put("ART", "Visual and Performing Arts");
                    put("ARTH", "Visual and Performing Arts");
                    put("BIO", "Biology");
                    put("CHEM", "Chemistry");
                    put("CHIN", "Modern Languages, Literatures and Linguistics");
                    put("CLAS", "Classics");
                    put("COMS", "Communication Studies");
                    put("CS", "Computer Science");
                    put("DAN", "Visual and Performing Arts");
                    put("DS", "Computer Science");
                    put("ECON", "Economics, Accounting, and Management");
                    put("EDUC", "Education");
                    put("ENG", "English");
                    put("ENVS", "Environmental Studies");
                    put("FCUL", "Modern Languages, Literatures and Linguistics");
                    put("FREN", "Modern Languages, Literatures and Linguistics");
                    put("GER", "Modern Languages, Literatures and Linguistics");
                    put("GRK", "Classics");
                    put("GS", "Paideia");
                    put("HEB", "Classics");
                    put("HIST", "History");
                    put("HLTH", "Health and Physical Education");
                    put("INTS", "Economics, Accounting, and Management");
                    put("IS", "International Studies");
                    put("JOUR", "English");
                    put("LAT", "Classics");
                    put("LING", "Modern Languages, Literatures and Linguistics");
                    put("MATH", "Mathematics");
                    put("MGT", "Economics, Accounting, and Management");
                    put("MUS", "Music");
                    put("MUST", "History");
                    put("NEUR", "Psychology");
                    put("NURS", "Nursing");
                    put("PAID", "Paideia");
                    put("PE", "Health and Physical Education");
                    put("PHIL", "Philosophy");
                    put("PHYS", "Health and Physical Education");
                    put("POLS", "Political Science");
                    put("PSYC", "Psychology");
                    put("REL", "Religion");
                    put("RUS", "Modern Languages, Literatures and Linguistics");
                    put("SCI", "Chemistry");
                    put("SCST", "Modern Languages, Literatures and Linguistics");
                    put("SOC", "Sociology, Anthropology, Social Work");
                    put("SPAN", "Modern Languages, Literatures and Linguistics");
                    put("SW", "Sociology, Anthropology, Social Work");
                    put("THE", "Visual and Performing Arts");
                    put("WGST", "Women and Gender Studies");
                }};
                        
        //TODO: don't do queries all the time to get the correct department name, make another Map?
        
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                String[] courseArray = fs.nextLine().split(" ");
                
                
                abbrv = courseArray[0];     

                // query to get the department id

                String fullDepartmentName = dictionary.get(abbrv);
                
                Statement statement = db_connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT id FROM UNIVERSITY.DEPARTMENT WHERE NAME= '" + fullDepartmentName + "';");
                results.next();
                int departmentNum = results.getInt(1);
                
                String dept_id = Integer.toString(departmentNum);
                
                System.out.println(dept_id);
                //end query
                
                number = courseArray[1];
                // Ignore the last two elements, create a string from the 
                // remaining elements
                for(int i=2; i < courseArray.length; i++) {
                    stringArray.append(courseArray[i]);
                    stringArray.append(" ");
                }
                //remove last space
                stringArray.setLength(stringArray.length() -1);
                title = stringArray.toString();
                
                // Clear the stringArray
                stringArray.setLength(0);
                
                Course course = new Course(dept_id, abbrv, number, title,"4");
                courseList.add(course);
            }
        } catch (IOException | SQLException ex) {
            System.out.println("Fail in Course");
            System.out.println("Error: " + ex.getMessage());
            //Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return courseList;
    }
    
    /*******************************
     * Populate Course table
     * @param courseList 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeCourseTable(ArrayList<Course> courseList) throws SQLException {
        for (Course cor: courseList) {
            
            String sql = "insert into UNIVERSITY.COURSE (department, abbreviation, number, title, credits) VALUES(?, ?, ?, ?, ?)"; 
                    
            PreparedStatement statement_prepared = this.db_connection.prepareStatement(sql);
            
            statement_prepared.setString(1, cor.getDepartment());
            statement_prepared.setString(2, cor.getAbbreviation());
            statement_prepared.setString(3, cor.getNumber());
            statement_prepared.setString(4, cor.getTitle());
            statement_prepared.setString(5, cor.getCredits());
            
            statement_prepared.executeUpdate();
        }
        
    }
    

    /*****************
     * Read Major from .txt
     * @param filename
     *****************/
    public ArrayList<Major> readMajorFromTxt(String filename) {
        String department; 
        String name;
        
        ArrayList<Major> majorList = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                String[] majors = fs.nextLine().split("\\|");
                
                Major majorElem = new Major(majors[1], majors[0]);
                majorList.add(majorElem);

            }
        } catch (IOException ex) {
            System.out.println("Fail in read major");
            //Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return majorList;
    }
    
    /*******************************
     * Populate Major table
     * @param majorList 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeMajorTable(ArrayList<Major> majorList) throws SQLException {
        for (Major maj: majorList) {            
            String sql = "insert into UNIVERSITY.MAJOR (department, name) VALUES(?, ?)"; 
                    
            PreparedStatement statement_prepared = this.db_connection.prepareStatement(sql);
            
            //query to get the departmentId
            Statement statement = db_connection.createStatement();
                ResultSet results = statement.executeQuery("SELECT id FROM UNIVERSITY.DEPARTMENT WHERE NAME= '" + maj.getDepartment() + "';");
                results.next();
                int departmentNum = results.getInt(1);
                
                String dept_id = Integer.toString(departmentNum);
                
                System.out.println(dept_id);

            statement_prepared.setString(1, dept_id);
            statement_prepared.setString(2, maj.getName());
            
            statement_prepared.executeUpdate();
        }
        
    }
    
    
    /*****************
     * Read Student from .txt
     * @param filename
     *****************/
    public ArrayList<Student> readStudentFromTxt(String filename) {
        
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                String[] students = fs.nextLine().split("\\|");
                
                
                
                
                
//                Student student = new Major(majors[1], majors[0]);
//                studentList.add(majorElem);

            }
        } catch (IOException ex) {
            System.out.println("Fail in read major");
            //Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentList;
    }
    
    /*******************************
     * Populate Major table
     * @param majorList 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeStudentTable(ArrayList<Student> studentList) throws SQLException {
        for (Student stu: studentList) {            
            String sql = "insert into UNIVERSITY.MAJOR (department, name) VALUES(?, ?)"; 
                    
            PreparedStatement statement_prepared = this.db_connection.prepareStatement(sql);
            
            

            statement_prepared.setString(1, "1");
            statement_prepared.setString(2, stu.getName());
            
            statement_prepared.executeUpdate();
        }
        
    }
    
    
    
    
    
    public void closeConnection() {
        try {
            db_connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
