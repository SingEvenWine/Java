package Lab2.Question2;

public class Person {
    private String name;
    private int age;
    private char sex;

    public Person(String name,int age,char sex){
        setAge(age);
        setName(name);
        setSex(sex);
    }

    public String show(){
        return getName()+" "+getSex()+" "+getAge();
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setSex(char sex){
        this.sex=sex;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public char getSex(){
        return sex;
    }
}
