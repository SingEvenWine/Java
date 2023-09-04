package Campaign_Management_System.DAO;

import Campaign_Management_System.Entity.Judge;

import java.util.List;

public interface JudgeDao {
    void addJudge(Judge judge);
    Judge getJudgeById(int id);
    List<Judge> getAllJudges();
    List<Judge> getJudgesByEventId(int eventId);
    void updateJudge(Judge judge);
    void deleteJudge(int id);
}
