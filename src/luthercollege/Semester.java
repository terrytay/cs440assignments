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
public class Semester {
    private final String year;
    private final String season;

    /**
     * @param _name
     * @param _graduationDate

     */
    public Semester(String _year, String _season) {
        this.year = _year;
        this.season = _season;
    }
    /**
     * @return year
     */
    public String getYear() {
        return this.year;
    }
    
    /**
     * @return season
     */
    public String getSeason() {
        return this.season;
    }
        
    @Override
    public String toString() {
        return this.year + ", " + this.season;
    }
}
