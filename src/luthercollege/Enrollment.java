package luthercollege;

/**
 * @author aaron
 */
public class Enrollment {
    private final String student;
    private final String section;
    private final String grade;

    /**
     * @param _student
     * @param _section
     * @param _grade
     */
    public Enrollment(String _student, String _section, String _grade) {
        this.student = _student;
        this.section = _section;
        this.grade = _grade;
    }
    /**
     * @return student
     */
    public String getStudent() {
        return this.student;
    }
    
    /**
     * @return section
     */
    public String getSection() {
        return this.section;
    }
    
    /**
     * @return grade
     */
    public String getGrade() {
        return this.grade;
    }
        
    @Override
    //Integer _department, String _abbreviation, Integer _number, String _title, Integer _credits
    public String toString() {
        return this.student + ", " + this.section + ", " + this.grade;
    }
}
