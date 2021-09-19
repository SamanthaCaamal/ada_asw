package presentacion;

import negocio.*;
import java.util.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        WordsRotator rotador = new WordsRotator();
        ArrayList<String> resultado = new ArrayList<>();

        try {

            System.out.println("Introducir la cadena: ");
            Scanner entradaString = new Scanner(System.in);

            if (!(entradaString == null)) {
                String entrada = entradaString.nextLine();

                resultado = rotador.rotateWords(entrada);
                Collections.sort(resultado, String.CASE_INSENSITIVE_ORDER); // ordenar alfabeticamente
                rotador.writeText(resultado);
            }

        } catch (Exception e) {
            System.out.println("Cadena vacia o invalida");
        }
    }
}