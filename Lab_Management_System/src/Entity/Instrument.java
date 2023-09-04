package Entity;
public class Instrument{
    int id;
    String name;
    String description;
    boolean isDamaged;
    int lab_id;
    public Instrument(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public Instrument(String name, String description, int lab_id) {
        this.name = name;
        this.description = description;
        this.lab_id = lab_id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLab_id() {
        return lab_id;
    }
    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDamaged() {
        return isDamaged;
    }
    public void setDamaged(boolean isDamaged) {
        this.isDamaged = isDamaged;
    }
}