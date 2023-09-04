package Campaign_Management_System.Entity;
public class Score {
    private int id;
    private int participantId;
    private double score;
    private int rank;

    public Score(int id, int participantId, double score, int rank) {
        this.id = id;
        this.participantId = participantId;
        this.score = score;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
