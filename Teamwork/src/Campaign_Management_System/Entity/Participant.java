package Campaign_Management_System.Entity;
public class Participant {
    private int id;
    private User user;
    private SportEvent sportEvent;

    public Participant(User user, SportEvent sportEvent) {
        this.user = user;
        this.sportEvent = sportEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
