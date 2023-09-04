package Campaign_Management_System.Service;

import Campaign_Management_System.Entity.Participant;

import java.util.List;

public interface ParticipantService {
    void addParticipant(Participant participant);
    Participant getParticipantById(int id);
    List<Participant> getAllParticipants();
    List<Participant> getParticipantsByEventId(int eventId);
    void updateParticipant(Participant participant);
    void deleteParticipant(int id);
}
