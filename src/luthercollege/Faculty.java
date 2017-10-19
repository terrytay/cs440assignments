package luthercollege;

/**
 * @author aaron
 */
public class Faculty {
    private final String name;
    private final String department;
    private final String startDate;
    private final String endDate;
    private final String office;

    /**
     * @param _name
     * @param _department
     * @param _startDate
     * @param _endDate
     * @param _office
     */
    public Faculty(String _name, String _department, String _startDate, String _endDate, String _office) {
        this.name = _name;
        this.department = _department;
        this.startDate = _startDate;
        this.endDate = _endDate;
        this.office = _office;
    }
    
    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return department
     */
    public String getDepartment() {
        return this.department;
    }
    
    /**
     * @return start date
     */
    public String getStartDate() {
        return this.startDate;
    }
    
    /**
     * @return end date
     */
    public String getEndDate() {
        return this.endDate;
    }
    
    /**
     * @return office
     */
    public String getOffice() {
        return this.office;
    }
        
    @Override
    //Integer _department, String _abbreviation, Integer _number, String _title, Integer _credits
    public String toString() {
        return this.name + ", " + this.department + ", " + this.startDate + ", " + this.endDate + ", " + this.office;
    }
}
