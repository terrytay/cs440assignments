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
    /*****************
     * Read Department from .txt
     * @param filename
     *****************/
    public ArrayList<Department> readDepartmentFromTxt(String filename) {
        // Women and Gender Studies|Char Kunkel|kunkelch@luther.edu|Koren|https://www.luther.edu/women-gender-studies/
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
        Connection db_connection = null;
        db_connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/UNIVERSITY?" + "user=root&password=test4pass");
        
        for (Department department: deptList) {
            Statement statement = db_connection.createStatement();
            
            String sql = "insert into UNIVERSITY.DEPARTMENT (name, building) VALUES(?, ?)"; 
                    
                   
            PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
            
            statement_prepared.setString(1, department.getName());
            statement_prepared.setString(2, department.getBuilding());
            
            statement_prepared.executeUpdate();
        }
        
        db_connection.close();
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
        Connection db_connection = null;
        db_connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/UNIVERSITY?" + "user=root&password=test4pass");
        
        for (Semester sem: semList) {
            Statement statement = db_connection.createStatement();
            
            String sql = "insert into UNIVERSITY.SEMESTER (year, season) VALUES(?, ?)"; 
                    
                   
            PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
            
            statement_prepared.setString(1, sem.getYear());
            statement_prepared.setString(2, sem.getSeason());
            
            statement_prepared.executeUpdate();
        }
        
        db_connection.close();
    }
    
    /*****************
     * Read Course from .txt
     * @param filename
     *****************/
    public ArrayList<Course> readCourseFromTxt(String filename) {
        
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                String[] courseArray = fs.nextLine().split(" ");
                //TODO: need to do a query to get the ID for the department
                //ACCTG 110 Introduction to Accounting
                //TODO: String replace the first two elements, and then the whole thing is the title then.
                Course course = new Course("NULL",courseArray[0],courseArray[1],courseArray[2],"4");
                courseList.add(course);
                System.out.println(course);
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
        Connection db_connection = null;
        db_connection  = DriverManager.getConnection("jdbc:mysql://localhost:3306/UNIVERSITY?" + "user=root&password=test4pass");
        
        for (Course cor: courseList) {
            Statement statement = db_connection.createStatement();
            
            String sql = "insert into UNIVERSITY.COURSE (department, abbreviation, number, title, credits) VALUES(?, ?, ?, ?, ?)"; 
                    
            PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
            
            statement_prepared.setString(1, cor.getDepartment());
            statement_prepared.setString(2, cor.getAbbreviation());
            statement_prepared.setString(3, cor.getNumber());
            statement_prepared.setString(4, cor.getTitle());
            statement_prepared.setString(5, cor.getCredits());
            
            statement_prepared.executeUpdate();
        }
        
        db_connection.close();
    }
}
