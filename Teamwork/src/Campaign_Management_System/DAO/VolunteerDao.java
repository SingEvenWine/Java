package Campaign_Management_System.DAO;

import Campaign_Management_System.Entity.Volunteer;

import java.util.List;

public interface VolunteerDao {
    void addVolunteer(Volunteer volunteer);
    Volunteer getVolunteerById(int id);
    List<Volunteer> getAllVolunteers();
    List<Volunteer> getVolunteersByEventId(int eventId);
    void updateVolunteer(Volunteer volunteer);
    void deleteVolunteer(int id);
}
