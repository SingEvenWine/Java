package Campaign_Management_System.Service;

import Campaign_Management_System.DAO.SportsEventDao;
import Campaign_Management_System.Entity.SportEvent;

import java.util.List;

public class SportsEventServicelmpl implements SportsEventService{
    private SportsEventDao sportsEventDao;

    @Override
    public void createEvent(SportEvent event) {
        sportsEventDao.addEvent(event);
    }

    @Override
    public SportEvent getEventById(int id) {
        return sportsEventDao.getEventById(id);
    }

    @Override
    public List<SportEvent> getAllEvents() {
        return null;
    }

    @Override
    public void updateEvent(SportEvent event) {

    }

    @Override
    public void deleteEvent(int id) {

    }

    @Override
    public SportEvent getEventByName(String text) {
        return null;
    }
}
