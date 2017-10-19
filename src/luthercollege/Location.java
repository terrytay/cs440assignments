package luthercollege;

/**
 * @author aaron
 */
public class Location {
    private final String building;
    private final String room;
    private final String purpose;

    /**
     * @param _building
     * @param _room
     * @param _purpose
     */
    public Location(String _building, String _room, String _purpose) {
        this.building = _building;
        this.room = _room;
        this.purpose = _purpose;
    }
    /**
     * @return department
     */
    public String getBuilding() {
        return this.building;
    }
    
    /**
     * @return room
     */
    public String getRoom() {
        return this.room;
    }
    
    /**
     * @return purpose
     */
    public String getPurpose() {
        return this.purpose;
    }
        
    @Override
    public String toString() {
        return this.building + ", " + this.room + ": " + this.purpose;
    }
}
