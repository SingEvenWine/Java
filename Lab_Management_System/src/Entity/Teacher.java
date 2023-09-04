package Entity;
public class Teacher {
    int id;
    String name;
    String teacherId;
    public Teacher(String name, String teacherId, String password) {
        this.name = name;
        this.teacherId = teacherId;
        this.password = password;
    }
    public Teacher() {
    }
    String password;
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
    public String getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}