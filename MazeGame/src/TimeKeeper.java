
public class TimeKeeper extends Time {

    public TimeKeeper() {
        super();
    }

    public void TimeKeeper(int sec, int min) {
        if (sec + seconds <= 60) {
            minutes += min;
            seconds = sec + seconds;
        } else {
            minutes += min;
            minutes += 1 * ((sec + seconds) / 60);
            seconds = (sec + seconds) % 60;
        }
    }

}
