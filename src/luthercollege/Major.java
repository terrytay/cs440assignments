package luthercollege;

/**
 * @author aaron
 */
public class Major {
    private final String department;
    private final String name;

    /**
     * @param _department
     * @param _name
     */
    public Major(String _department, String _name) {
        this.department = _department;
        this.name = _name;
    }
    /**
     * @return department
     */
    public String getDepartment() {
        return this.department;
    }
    
    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }
        
    @Override
    public String toString() {
        return this.department + ", " + this.name;
    }
}
