package Entity;

public class Teacher_lab {
    int id;
    int teacher_id;
    int lab_id;
    String begin_time;
    String end_time;

    public Teacher_lab(int teacher_id, int lab_id, String begin_time, String end_time, boolean is_agree) {
        this.teacher_id = teacher_id;
        this.lab_id = lab_id;
        this.begin_time = begin_time;
        this.end_time = end_time;
        this.is_agree = is_agree;
    }
    boolean is_agree;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTeacher_id() {
        return teacher_id;
    }
    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }
    public int getLab_id() {
        return lab_id;
    }
    public void setLab_id(int lab_id) {
        this.lab_id = lab_id;
    }
    public String getBegin_time() {
        return begin_time;
    }
    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }
    public String getEnd_time() {
        return end_time;
    }
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
    public boolean isIs_agree() {
        return is_agree;
    }
    public void setIs_agree(boolean is_agree) {
        this.is_agree = is_agree;
    }
    
}
