package Main;

public class AlgInfo {
    private int id;
    private String path;
    private int totalScore;
    private int highScore;

    public AlgInfo(int id, String path, int totalScore, int highScore) {
        this.id = id;
        this.path = path;
        this.totalScore = totalScore;
        this.highScore = highScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
