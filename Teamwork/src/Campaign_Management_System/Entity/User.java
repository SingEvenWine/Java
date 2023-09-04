package Campaign_Management_System.Entity;

public class User {
    private int id;//自增
    private String username;
    private String password;
    private String role;//学生、老师、管理员
    private boolean isMan;
    private boolean isJudge;//0：否，1：是
    private boolean isVoluntee;//0：否，1：是

    public User(String username, String password,boolean isMan,String role, boolean is_judge, boolean is_voluntee) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.isJudge = is_judge;
        this.isVoluntee = is_voluntee;
        this.isMan = isMan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIs_judge() {
        return isJudge;
    }

    public void setIs_judge(boolean is_judge) {
        this.isJudge = is_judge;
    }

    public boolean getIsVoluntee() {
        return isVoluntee;
    }

    public void setIsVoluntee(boolean isVoluntee) {
        this.isVoluntee = isVoluntee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
