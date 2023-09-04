package Campaign_Management_System.Service;

import Campaign_Management_System.Entity.SportEvent;

import java.util.List;

public interface SportsEventService {
    void createEvent(SportEvent event);
    SportEvent getEventById(int id);
    List<SportEvent> getAllEvents();
    void updateEvent(SportEvent event);
    void deleteEvent(int id);

    Object getEventByName(String text);
}
