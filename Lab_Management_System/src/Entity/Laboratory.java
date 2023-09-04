package Entity;
public class Laboratory {
    int id;
    String name;
    String location;
    int maxNumberOfStudents;
    
    public Laboratory(String name, String location, int maxNumberOfStudents) {
        this.name = name;
        this.location = location;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }
    public Laboratory() {
    }
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getMaxNumberOfStudents() {
        return maxNumberOfStudents;
    }
    public void setMaxNumberOfStudents(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
    }
}
