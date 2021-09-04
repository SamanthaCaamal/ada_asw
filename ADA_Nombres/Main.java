/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Nombres;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author sam33
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        Archivo archivo = new Archivo();
        ArrayList<String> lista = new ArrayList<>();
        ArrayList<String> listaFormato = new ArrayList<>();
        lista = archivo.readFile("./ADA_Nombres/input.txt");
        String nombre;

        System.out.println("DESORDENADA: ");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
            nombre = lista.get(i).toLowerCase();
            listaFormato.add(upperCaseFirst(nombre));
        }

        Collections.sort(listaFormato);

        System.out.println("\n\nLISTA: ");
        for (int i = 0; i < listaFormato.size(); i++) {
            System.out.println(listaFormato.get(i));

        }
        
        archivo.writeFile("./ADA_Nombres/output.txt", listaFormato);
    }

    public static String upperCaseFirst(String val) {
        char[] caracteres = val.toCharArray();
        caracteres[0] = Character.toUpperCase(caracteres[0]);
        // el -2 es para evitar una excepción al caernos del arreglo
        for (int i = 0; i < val.length() - 2; i++) { // Es 'palabra'{
            if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',') { // Reemplazamos
                caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
            }
        }
        return new String(caracteres);
    }

}
