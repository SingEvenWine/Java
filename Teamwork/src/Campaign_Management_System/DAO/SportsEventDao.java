package Campaign_Management_System.DAO;

import Campaign_Management_System.Entity.SportEvent;

import java.util.List;

public interface SportsEventDao {
    void addEvent(SportEvent event);
    SportEvent getEventById(int id);
    List<SportEvent> getAllEvents();
    void updateEvent(SportEvent event);
    void deleteEvent(int id);
}
