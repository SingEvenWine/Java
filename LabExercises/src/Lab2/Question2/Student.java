package Lab2.Question2;

public class Student extends Person{
    private int id;


    public Student(String name,int age,char sex,int id){
        super(name, age, sex);
        setId(id);
    }

    @Override
    public String show() {
        return super.show()+" "+getId();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
