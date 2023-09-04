package Campaign_Management_System.DAO;

import Campaign_Management_System.Entity.Score;

import java.util.List;

public interface ScoreDao {
    void addScore(Score score);
    Score getScoreById(int id);
    List<Score> getAllScores();
    List<Score> getScoresByParticipantId(int participantId);
    void updateScore(Score score);
    void deleteScore(int id);
}
