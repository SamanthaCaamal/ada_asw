/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Nombres;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sam33
 */
public class Archivo {
    
    public ArrayList<String> readFile(String archivo) {
        ArrayList<String> temporal = new ArrayList<>();
        
        try {

            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea = "";

            while ((linea = br.readLine()) != null) {
                temporal.add(linea);
                
            }
            
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + archivo);
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temporal;
    }
    
    public void writeFile(String archivo, ArrayList<String> datos) {
        try {
            FileWriter fr = new FileWriter(archivo);
            PrintWriter pw = new PrintWriter(fr);
            for (int i = 0; i < datos.size(); i++) {
//                System.out.println(datos.get(i));
                pw.println(datos.get(i));
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error al imprimir...");
        }
    }
}
