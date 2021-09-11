
public class Player {

    private String playerName;
    private int minutes;
    private int seconds;
    private int level;

    public Player(String playerName, int minutes, int seconds, int level) {
        this.playerName = playerName;
        this.minutes = minutes;
        this.seconds = seconds;
        this.level = level;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public String getSeconds() {
        if(seconds >= 0 || seconds <=9){
            return "0" + seconds;
        }
        return String.valueOf(seconds);
    }

    public int getLevel() {
        return level;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        String player = "Nombre del jugador: " + playerName + " ";
        String time = "Tiempo total: " + minutes + ":" + getSeconds() + " (Minutos:Segundos) ";
        String numberLevel = "Nivel alcanzado: " + level;
        String separator = "/";
        String score = player + separator + time + separator + numberLevel;
        return score;
    }

}
