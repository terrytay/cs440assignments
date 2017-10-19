package luthercollege;

/**
 * @author aaron
 */
public class Course {
    private final String department;
    private final String abbreviation;
    private final String number;
    private final String title;
    private final String credits;

    /**
     * @param _department
     * @param _abbreviation
     * @param _number
     * @param _title
     * @param _credit
     */
    public Course(String _department, String _abbreviation, String _number, String _title, String _credits) {
        this.department = _department;
        this.abbreviation = _abbreviation;
        this.number = _number;
        this.title = _title;
        this.credits = _credits;
    }
    /**
     * @return department
     */
    public String getDepartment() {
        return this.department.toString();
    }
    
    /**
     * @return abbreviation
     */
    public String getAbbreviation() {
        return this.abbreviation;
    }
    
    /**
     * @return number
     */
    public String getNumber() {
        return this.number.toString();
    }
    
    /**
     * @return title
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * @return credits
     */
    public String getCredits() {
        return this.credits.toString();
    }
        
    @Override
    //Integer _department, String _abbreviation, Integer _number, String _title, Integer _credits
    public String toString() {
        return this.department + ", " + this.abbreviation + ", " + this.number + ", " + this.title + ", " + this.credits;
    }
}
