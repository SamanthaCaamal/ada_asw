package negocio;

import datos.*;
import java.util.*;
import java.util.StringJoiner;

public class WordsRotator {
    ArrayList<String> wordList;
    Archivo file;
    EmptyWordList emptyWordList;

    /**
     * Construtor de la clase
     */
    public WordsRotator() {
        wordList = new ArrayList<>();
        file = new Archivo("Output.txt");
        emptyWordList = new EmptyWordList();
    }

    /**
     * Método que se encarga de rotar la primera palabra al final de la cadena y
     * obtener todas las combinaciones.
     * 
     * @param cadena contiene el string introduido por el usuario.
     * @return ArrayList de String con las combinaciones obtenidas.
     */
    public ArrayList<String> rotateWords(String cadena) {
        String mini = cadena.toLowerCase(); // convertir en minuscula
        String aux = deleteEmptyWords(mini); // eliminar palabras vacias
        String aux2 = aux;

        String[] isTrue = aux.split(" ", 2);

        if (isTrue.length > 1) { // Si la cadena tiene menos de 1 palabra, retorna solo la palabra
            do {
                String[] partes = aux.split(" ", 2);
                String parte1 = partes[0];
                String parte2 = partes[1];

                aux = parte2.concat(" " + parte1);
                wordList.add(aux);
            } while (!aux.equals(aux2));
        } else {
            wordList.add(aux);
        }

        return wordList;
    }

    /**
     * Metodo que se encarga de imprimir el resultado en un .txt
     * 
     * @param listWords contiene las combinaciones obtenidas en la rotación.
     */
    public void writeText(ArrayList<String> listWords) {
        ArrayList<String> list = listWords;
        file.printList(list);
    }

    /**
     * Método encargado de eliminar todas las palabras vacias que contenga la cadena
     * introducida por el usuario.
     * 
     * @param cadena contiene la cadena con todas las letras en minuscula.
     * @return cadena sin palabras vacías.
     */
    public String deleteEmptyWords(String cadena) {
        String resultString = "";
        String[] arrayString = cadena.split(" ");
        ArrayList<String> arrayEmpty = emptyWordList.getEmptyWordList();
        StringJoiner sj = new StringJoiner(" ");

        for (int j = 0; j < arrayString.length; j++) {
            if (!(arrayEmpty.contains(arrayString[j]))) {
                sj.add(arrayString[j]);
            }
        }

        resultString = sj.toString();
        return resultString;

    }

}
