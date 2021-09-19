package test;

import negocio.*;
import java.util.*;

public class test {

    public static void main(String[] args) {

        String cadena = "La casa AzUL es muy GRANDE";
        WordsRotator rotador = new WordsRotator();
        ArrayList<String> resultado = new ArrayList<>();

        String result1 = cadena.toLowerCase();

        String result = rotador.deleteEmptyWords(result1);

        System.out.println("Resultado: " + result);
    }
}