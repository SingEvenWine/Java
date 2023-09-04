package Campaign_Management_System.Service;

import Campaign_Management_System.DAO.VolunteerDao;
import Campaign_Management_System.Entity.Volunteer;

import java.util.List;

public class VolunteerServicelmpl implements VolunteerService{
    private VolunteerDao volunteerDao;

    @Override
    public void addVolunteer(Volunteer volunteer) {
        volunteerDao.addVolunteer(volunteer);
    }

    @Override
    public Volunteer getVolunteerById(int id) {
        return volunteerDao.getVolunteerById(id);
    }

    @Override
    public List<Volunteer> getAllVolunteers() {
        return null;
    }

    @Override
    public List<Volunteer> getVolunteersByEventId(int eventId) {
        return null;
    }

    @Override
    public void updateVolunteer(Volunteer volunteer) {
        volunteerDao.updateVolunteer(volunteer);
    }

    @Override
    public void deleteVolunteer(int id) {
        volunteerDao.deleteVolunteer(id);
    }
}
