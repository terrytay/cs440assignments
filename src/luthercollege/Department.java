/*
 * 
 */
package luthercollege;

/**
 *
 * @author aaron
 */
public class Department {
    private final String name;
    private final String building;

    /**
     * @param _name
     * @param _building

     */
    public Department(String _name, String _building) {
        this.name = _name;
        this.building = _building;
    }
    /**
     * @return department name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @return building
     */
    public String getBuilding() {
        return this.building;
    }
        
    @Override
    public String toString() {
        return this.name + ", " + this.building;
    }
}
