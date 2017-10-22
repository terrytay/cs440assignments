/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luthercollege;

import java.io.File;
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
import java.util.Map;

import java.util.Random;

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

            }
        } catch (IOException ex) {
            System.out.println("Fail in read from Department");
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
                //System.out.println(sem);
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
            
            String sql = "insert into UNIVERSITY.SEMESTER (year, season) VALUES(?, ?)"; 
                    
            PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
            
            statement_prepared.setString(1, sem.getYear());
            statement_prepared.setString(2, sem.getSeason());
            
            statement_prepared.executeUpdate();
        }

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
                
                //System.out.println(dept_id);

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
            System.out.println("Fail in Course txt");
            System.out.println("Error: " + ex.getMessage());
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
            System.out.println("Fail in read major txt");
            System.out.println("Error: " + ex.getMessage());

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

            statement_prepared.setString(1, dept_id);
            statement_prepared.setString(2, maj.getName());
            
            statement_prepared.executeUpdate();
        }
        
    }
    
    /*****************
     * Generate locations
     * @param filename
     *****************/
    public ArrayList<Location> generateLocations() {

        ArrayList<Location> locationList= new ArrayList<>();
        
        try {
            Statement statement = db_connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT distinct building FROM UNIVERSITY.DEPARTMENT;");
        
            //iterate over resultset and create the locations
            //https://stackoverflow.com/questions/15517736/iterating-over-resultset-and-adding-its-value-in-an-arraylist
            
            ArrayList<String> buildingList = new ArrayList<String>();
            while (results.next()) {
                buildingList.add(results.getString(1));                
            }

        //All buildings have 4 floors, with 25 rooms on each floor.
        //the top two floors have offices. Bottom two floors are
        //used for classrooms.
        StringBuilder roomNum;
        String purpose;
        StringBuilder theRoom;
        
        for (String building : buildingList) {
            for (int floor = 1; floor < 5; floor++) {
                for (int room = 0; room < 25; room++) {

                    if (floor == 3 || floor == 4){
                        purpose = "office";
                    } else {
                        purpose = "classroom";
                    }
                    //There has to be a better way to do this.....
                    String floorNum = Integer.toString(floor);
                    if (room >= 0 && room <= 9){
                        roomNum = new StringBuilder()
                                .append("0")
                                .append(room);
                    } else {
                        roomNum = new StringBuilder()
                                .append(room);
                    }
                    
                    theRoom = new StringBuilder()
                            .append(floorNum)
                            .append(roomNum);
                    
                    Location location = new Location(building, theRoom.toString(), purpose);
                    locationList.add(location);
                }
            }
        }
            
        } catch(SQLException ex) {
            System.out.println("Failed inside of location generation");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    
        return locationList;
    }
    
        /*******************************
     * Populate Location table
     * @throws java.sql.SQLException 
     *******************************/
    public void writeLocationTable(ArrayList<Location> locationList) throws SQLException {
        for (Location loc: locationList) {

            String sql = "insert into UNIVERSITY.LOCATION (building, room, purpose) VALUES(?, ?, ?)"; 
                    
            PreparedStatement statement_prepared = db_connection.prepareStatement(sql);
            
            statement_prepared.setString(1, loc.getBuilding());
            statement_prepared.setString(2, loc.getRoom());
            statement_prepared.setString(3, loc.getPurpose());            
            statement_prepared.executeUpdate();
        }
    }
      /*****************
     * Read Faculty from .txt
     * @param filename
     *****************/
    public ArrayList<Faculty> readFacultyFromTxt(String filename) {
        ArrayList<Faculty> facultyList = new ArrayList<>();
        String department;
        Integer departmentNum;
        String dept_id;
        String facultyName;
        String building;
        String endDate;
        
        try {
            Scanner fs = new Scanner(new File(filename));
            department = "none";
            while (fs.hasNextLine()) {
                String facultyTxt = fs.nextLine();
                
                //https://stackoverflow.com/questions/12556637/java-id-like-to-ignore-an-empty-line-when-reading-a-file
                if (facultyTxt.trim().equals("$") || facultyTxt.isEmpty() || facultyTxt.trim().equals("") || facultyTxt.trim().equals("\n")) {
                    facultyTxt = fs.nextLine();
                }
                
                //if line is department
                if (facultyTxt.toUpperCase().equals(facultyTxt)) {
                    department = facultyTxt;
                    facultyTxt = fs.nextLine();
                }
                if (!facultyTxt.toUpperCase().equals(facultyTxt)) {
                    Statement statement = db_connection.createStatement();
                    ResultSet results = statement.executeQuery("SELECT id, building FROM UNIVERSITY.DEPARTMENT WHERE NAME= '" + department + "';");
                    results.next();
                    departmentNum = results.getInt(1);
                    building = results.getString(2);
                    
                
                    dept_id = Integer.toString(departmentNum);
                    
                    String[] faculty = facultyTxt.split("\\|");
                    facultyName = faculty[0];

                
                    //generate random start date
                    //1-7 in SEMESTER table, no faculty start after current semester.
                    //Once hiring is completed, then they get added to FACULTY table
                    Random startDateNum = new Random();
                    int  _startDate = startDateNum.nextInt(7) + 1;
                    String startDate = Integer.toString(_startDate);

                    //generate random end date
                    
                    Random endDateRange = new Random();
                    int  _endDateChance = endDateRange.nextInt(100) + 1;
                    
                    if (_endDateChance <= 12) {
                        Random endDateRandom = new Random();
                        int  _endDate = endDateRandom.nextInt(12) + 1;
                        endDate = Integer.toString(_endDate);
                    } else {
                        endDate = null;
                    }

                    Statement stmt = db_connection.createStatement();
                    ResultSet locResult = stmt.executeQuery("SELECT id FROM UNIVERSITY.LOCATION WHERE BUILDING= '" + building + "' and PURPOSE='office';");
                    locResult.next();
                    String location = locResult.getString(1);
                    
                    Faculty facultyMember = new Faculty(facultyName, dept_id, startDate, endDate, location);
                    facultyList.add(facultyMember);
                }
            }
            
        } catch(IOException | SQLException ex) {
            System.out.println("Failed in faculty reading/sql");
            System.out.println(ex.getMessage());
            
        }
        
        return facultyList;
    }
    
    /*******************************
     * Populate Faculty table
     * @param facultyList 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeFacultyTable(ArrayList<Faculty> facultyList) throws SQLException {
        for (Faculty fac: facultyList) {            
            String sql = "insert into UNIVERSITY.FACULTY (name, department, startDate, endDate, office) VALUES(?, ?, ?, ?, ?)"; 
                    
            PreparedStatement statement_prepared = this.db_connection.prepareStatement(sql);

            statement_prepared.setString(1, fac.getName());
            statement_prepared.setString(2, fac.getDepartment());
            statement_prepared.setString(3, fac.getStartDate());
            statement_prepared.setString(4, fac.getEndDate());
            statement_prepared.setString(5, fac.getOffice());
            
            statement_prepared.executeUpdate();
        }
        
    }
    
    /*****************
     * Generate sections
     *****************/
    public ArrayList<Section> generateSections() {
        ArrayList<Section> sectionList = new ArrayList<>();
        int generate = 0;
        
        try {
            for(int i=1; i <= 868; i++) {
                //query to see what the course number is
                Statement stmt = db_connection.createStatement();
                ResultSet courseNumRes = stmt.executeQuery("SELECT number, department FROM UNIVERSITY.COURSE WHERE ID= '" + i + "' ;");
                courseNumRes.next();
                String courseNum = courseNumRes.getString(1);
                String departmentId = courseNumRes.getString(2);
                
                int first = Integer.parseInt(courseNum.substring(0, 1));
                switch (first) {
                    case 1:
                        generate = 4;
                        break;
                    case 2:
                        generate = 3;
                        break;
                    case 3:
                        generate = 2;
                        break;
                    case 4:
                        generate = 1;
                        break;
                    default:
                        break;
                }
                
                /* 
                get building from department 
                */
                //query to get the building from departmentId, check DEPARTMENT table
                //select building from UNIVERSITY.DEPARTMENT where id = 5;
                Statement buildingStmt = db_connection.createStatement();
                String buildingSQL = "select building from UNIVERSITY.DEPARTMENT where id = " + departmentId + ";";
                ResultSet buildingRes = buildingStmt.executeQuery(buildingSQL);
                buildingRes.next();
                String buildingName = buildingRes.getString(1);
                
                /*
                location
                */
                //SQL statement to get the id's from location where the criteria fits
                //SELECT column FROM table ORDER BY RAND() LIMIT 1
                Statement classroomStmt = db_connection.createStatement();
                String classroomIdSQL = "select id from UNIVERSITY.LOCATION where BUILDING = '" + buildingName + "' and purpose = 'classroom'"
                        + "ORDER BY RAND() LIMIT 1;";
                ResultSet classroomRes = classroomStmt.executeQuery(classroomIdSQL);
                classroomRes.next();
                //get the stuff: 
                String classroomId = classroomRes.getString(1);

                /*
                instructor
                */
                //get the instructor's id from the department id
                Statement instructorStmt = db_connection.createStatement();
                String instructorSQL = "select id from FACULTY where department = '" + departmentId + "' ORDER BY RAND() LIMIT 1;";
                ResultSet instructorRes = instructorStmt.executeQuery(instructorSQL);
                instructorRes.next();
                //get the stuff: 
                String instructorId = instructorRes.getString(1);

                int startHour = 800;
                for(int k=0; k < generate; k++){
                    // 0 - 12 for their semester id
                    Random offeredYearSeason = new Random();
                    int  _semester = offeredYearSeason.nextInt(12) + 1;
                    String semester = Integer.toString(_semester);
                    
                    Section section = new Section(courseNum, instructorId, semester, classroomId, startHour);
                    sectionList.add(section);
                    
                    startHour = startHour + 115;

                    classroomRes.next();
                }

            }
        
        
        } catch (SQLException ex) {
            System.out.println("Failed inside of section");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        //return the ArrayList of sections?
        return sectionList;
        
    }
    
    /*******************************
     * Populate SECTION table
     * @param sectionList 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeSectionTable(ArrayList<Section> sectionList) throws SQLException {
        for (Section sec: sectionList) {            
            String sql = "insert into UNIVERSITY.SECTION (course, instructor, offered, location, startHour) VALUES(?, ?, ?, ?, ?)"; 
                    
            PreparedStatement statement_prepared = this.db_connection.prepareStatement(sql);

            statement_prepared.setString(1, sec.getCourse());
            statement_prepared.setString(2, sec.getInstructor());
            statement_prepared.setString(3, sec.getOffered());
            statement_prepared.setString(4, sec.getLocation());
            statement_prepared.setInt(5, sec.getStartHour());

            statement_prepared.executeUpdate();
        }
        
    }

    
        /*****************
     * Read Student from .txt
     * @param filename
     *****************/
    public ArrayList<Student> readStudentFromTxt(String filename) {
        
        String firstName;
        String lastName;
        
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            Scanner fs = new Scanner(new File(filename));
            while (fs.hasNextLine()) {
                String name = fs.nextLine();
                
                // 0 - 12 for their gradYear id
                Random gradYearSeason = new Random();

                int  _gradId = gradYearSeason.nextInt(12) + 1;
                String gradId = Integer.toString(_gradId);
                //System.out.println(gradId);
                                
                //28 majors
                Random studentMajor = new Random();

                int  _majorId = studentMajor.nextInt(28) + 1;
                String majorId = Integer.toString(_majorId);
                
                //Adviser can be any faculty member. A student can change
                //their adviser at any time.
                
                Random adviserRandom = new Random();
                int  _adviserId = adviserRandom.nextInt(236) + 1;
                String adviserId = Integer.toString(_adviserId);
               
                Student student = new Student(name, gradId, majorId, adviserId);
                studentList.add(student);

            }
        } catch (IOException ex) {
            System.out.println("Fail in read major");
            //Logger.getLogger(DatabaseReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentList;
    }
    
    /*******************************
     * Populate Student table
     * @param studentList 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeStudentTable(ArrayList<Student> studentList) throws SQLException {
        for (Student stu: studentList) {            
            String sql = "insert into UNIVERSITY.STUDENT (name, graduationDate, major, adviser) VALUES(?, ?, ?, ?)"; 
                    
            PreparedStatement statement_prepared = this.db_connection.prepareStatement(sql);

            statement_prepared.setString(1, stu.getName());
            statement_prepared.setString(2, stu.getGradDate());
            statement_prepared.setString(3, stu.getMajor());
            statement_prepared.setString(4, stu.getAdviser());
            
            statement_prepared.executeUpdate();
        }
        
    }
    
    
    /*****************
     * Generate sections
     *****************/
    public ArrayList<Enrollment> generateEnrollment() {
        ArrayList<Enrollment> enrollList = new ArrayList<>();

        try {
        // get student ID
        Statement instructorStmt = db_connection.createStatement();
        String studentIdSQL = "select id from UNIVERSITY.STUDENT";
        
        
        ResultSet studentIDRes = instructorStmt.executeQuery(studentIdSQL);
        studentIDRes.next();
        //get the stuff: 
        
        while (studentIDRes.next()) {
            String studentId = studentIDRes.getString(1);

            for (int c = 0; c < 4; c++){
                //random course in '1245' sectioins
                Random courseNumRand = new Random();
                int _courseNum = courseNumRand.nextInt(1245) + 1;
                String section = Integer.toString(_courseNum);
                
                // generate grade - A, B, C, D, F
                ArrayList<String> grades = new ArrayList<String>()
                    {{
                        add("A");
                        add("B");
                        add("C");
                        add("D");
                        add("F");
                    }};
                Random randomizer = new Random();
                String grade = grades.get(randomizer.nextInt(grades.size()));        

                Enrollment enrollment = new Enrollment(studentId, section, grade);
                enrollList.add(enrollment);
            }   
            
        }
        
        } catch (SQLException ex) {
            System.out.println("fail in enroll");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return enrollList;
    }
    
    /*******************************
     * Populate ENROLL table
     * @param enrollList 
     * @throws java.sql.SQLException 
     *******************************/
    public void writeEnrollTable(ArrayList<Enrollment> enrollList) throws SQLException {
        for (Enrollment enroll: enrollList) {            
            String sql = "insert into UNIVERSITY.ENROLLMENT (student, section, grade) VALUES(?, ?, ?)"; 
                    
            PreparedStatement statement_prepared = this.db_connection.prepareStatement(sql);

            statement_prepared.setString(1, enroll.getStudent());
            statement_prepared.setString(2, enroll.getSection());
            statement_prepared.setString(3, enroll.getGrade());

            statement_prepared.executeUpdate();
        }
        
    }
    
    public void closeConnection() {
        try {
            db_connection.close();
            System.out.println("Closing database connection");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
}
