package luthercollege;

/**
 * @author aaron
 */
public class Section {
    private final String course;
    private final String instructor;
    private final String offered;
    private final String location;
    private final int startHour;

    /**
     * @param _department
     * @param _abbreviation
     * @param _number
     * @param _title
     * @param _credit
     */
    public Section(String _course, String _instructor, String _offered, String _location, int _startHour) {
        this.course = _course;
        this.instructor = _instructor;
        this.offered = _offered;
        this.location = _location;
        this.startHour = _startHour;
    }
    /**
     * @return course
     */
    public String getCourse() {
        return this.course;
    }
    
    /**
     * @return instructor
     */
    public String getInstructor() {
        return this.instructor;
    }
    
    /**
     * @return offered
     */
    public String getOffered() {
        return this.offered;
    }
    
    /**
     * @return location
     */
    public String getLocation() {
        return this.location;
    }
    
    /**
     * @return start hour
     */
    public int getStartHour() {
        return this.startHour;
    }
        
    @Override
    public String toString() {
        return this.course + ", " + this.instructor + ", " + this.offered + ", " + this.location + ", " + this.startHour;
    }
}
