
public class TimeCalculator extends Time {

    public TimeCalculator() {
        super();
    }

    public void TimeCalculatorForNevel(int totalDimonds, int xSize, int ySize) {

        if (xSize / ySize < 1) {
            addMinutes(xSize, ySize);
        } else {
            addMinutes(xSize, ySize);
        }

        if (totalDimonds > 6 && (totalDimonds * .10 + seconds) <= 60) {
            addMinutes(xSize, ySize);
        } else {
            minutes += 1;
        }
    }

    public void zeroTime() {
        if (minutes == 0) {
            minutes = 2;
        }
    }

    public void addMinutes(int ySize, int xSize) {
        minutes += (ySize / xSize) * 1 + 1;
    }
}
