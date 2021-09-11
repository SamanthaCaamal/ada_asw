
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class File {

    ArrayList<String> list = new ArrayList<String>();

    public ArrayList<String> readFile(String name) {
        try {

            FileReader fr = new FileReader(name);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            fr.close();
        } catch (IOException ex) {
            JFrame frame = new JFrame("Alert");
            JOptionPane.showMessageDialog(frame, "Archivo no encontrado" + ex.getMessage());
        }

        return list;
    }

    public void writeFile(ArrayList<String> datos) {
        try {
            FileOutputStream fr = new FileOutputStream("scores.txt");
            PrintWriter pw = new PrintWriter(fr);
            for (int i = 0; i < datos.size(); i++) {
                pw.println(datos.get(i));
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error al imprimir...");
        }
    }
}
