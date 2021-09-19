package negocio;

import datos.*;
import java.util.ArrayList;
import java.util.Collections;

public class EmptyWordList {

    ArrayList<String> wordList;
    Archivo file;

    /**
     * Constructor de la clase
     */
    public EmptyWordList() {
        wordList = new ArrayList<>();
        file = new Archivo("emptyWordList.txt");
    }

    /**
     * Método que obtiene las palabras vacias del .txt y lo guarda en un array.
     * 
     * @return ArrayList con las palabras vacías.
     */
    public ArrayList<String> getEmptyWordList() {
        wordList = file.readFile();
        return wordList;
    }

}
