
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameError extends RuntimeException {

    public void GameError(String mensaje) {
        JFrame frame = new JFrame("Alert");
        JOptionPane.showMessageDialog(frame, mensaje);
    }
}
