package Lab2.Question2;

public class Teacher extends Person{
    private String course;

    public Teacher(String name,int age,char sex,String course){
        super(name, age, sex);
        setCourse(course);
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String show() {
        return super.show()+" "+getCourse();
    }
}
