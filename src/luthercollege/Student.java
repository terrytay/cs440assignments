/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luthercollege;

/**
 *
 * @author aaron
 */
public class Student {
    private final String name;
    private final String graduationDate;
    private final String major;
    private final String adviser;

    /**
     * @param _name
     * @param _graduationDate
     * @param _major
     * @param _adviser

     */
    public Student(String _name, String _graduationDate, String _major, String _adviser) {
        this.name = _name;
        this.graduationDate = _graduationDate;
        this.major = _major;
        this.adviser = _adviser;
    }
    /**
     * @return student name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return graduation date
     */
    public String getGradDate() {
        return this.graduationDate;
    }
        
    /**
     * @return major id
     */
    public String getMajor() {
        return this.major;
    }
        
    /**
     * @return adviser id
     */
    public String getAdviser() {
        return this.adviser;
    }
    
    @Override
    public String toString() {
        return this.name + "\n" + this.graduationDate + "\n" + this.major + "\n" + this.adviser;
    }
}
