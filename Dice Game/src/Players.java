public class Players {
    private String name;
    private int score;
    private boolean isPlaying;

    public Players(String name) {
        this.name = name;
        this.score = 0;
        this.isPlaying = true;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public void decreaseScore(int score) {
        this.score = 0;
    }

}
