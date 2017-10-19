/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luthercollege;

import java.io.ByteArrayOutputStream;
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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

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
        
//        this.db_connection.close();
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
            System.out.println("Fail");
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
        Integer deptId;
        String abbrv; 
        String number;
        String title; 
        StringBuilder stringArray = new StringBuilder();
        
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                String[] courseArray = fs.nextLine().split(" ");
                
                //TODO: need to do a query to get the ID for the department
                deptId = 1;
              
                abbrv = courseArray[0];
                number = courseArray[1];
                System.out.println(number);
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
                
                Course course = new Course(deptId.toString(), abbrv, number, title,"4");
                courseList.add(course);
//                System.out.println(course);
            }
        } catch (IOException ex) {
            System.out.println("Fail");
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
//            Statement statement = this.db_connection.createStatement();
            
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
    
    public void closeConnection() {
        try {
            db_connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
}
