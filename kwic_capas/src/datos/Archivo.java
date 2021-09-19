package datos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Archivo {
    public String name;

    /**
     * Constructor de la clase.
     * 
     * @param name Nombre del archivo a manipular.
     */
    public Archivo(String name) {
        this.name = name;
    }

    /**
     * Método que lee un archivo .txt y guarda sus datos en un ArrayList.
     * 
     * @return ArrayList con los datos del archivo.
     */
    public ArrayList<String> readFile() {
        ArrayList<String> temporal = new ArrayList<>();

        try {
            FileReader fr = new FileReader(name);
            BufferedReader br = new BufferedReader(fr);
            String linea = "";

            while ((linea = br.readLine()) != null) {
                temporal.add(linea);

            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + name);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temporal;
    }

    /**
     * Método que imprime los valores de un ArrayList en un archivo .txt.
     * 
     * @param list contiene ArrayList con los datos a imprimir en el archivo.
     */
    public void printList(ArrayList<String> list) {
        try {

            FileWriter fr = new FileWriter(this.name);
            PrintWriter pw = new PrintWriter(fr);

            for (int i = 0; i < list.size(); i++) {
                pw.println(list.get(i));
            }

            pw.close();

        } catch (Exception e) {
            System.out.println("Error al imprimir");
        }

    }

}