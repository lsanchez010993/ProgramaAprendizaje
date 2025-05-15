import java.util.ArrayList;
import java.util.Random;

public class JuegoOrdenarPalabra {


public static void ordenarPalabra(char nivelDificultad){
    juegoOrdenarPalabra();

}
    private static void juegoOrdenarPalabra() {

        String palabra = seleccionarPalabra().toLowerCase();
        Menu.niveldeDificultad = 'D';


        Metodos.mostrarMensaje("Ordena la siguientes letras para formar una palabra:");
        //mostrar caracteres:
        Metodos.mostrarMensaje(palabra);
        Metodos.mostrarMensaje(palabraDesordenada(palabra));

    }

    public static char[] palabraDesordenada(String palabra) {
        char[] palabraDesordenada = palabra.toCharArray();
        int index;

        Random random = new Random();
        for (int i = palabraDesordenada.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                palabraDesordenada[index] ^= palabraDesordenada[i];
                palabraDesordenada[i] ^= palabraDesordenada[index];
                palabraDesordenada[index] ^= palabraDesordenada[i];
            }
        }
        return palabraDesordenada;
    }

    public static String seleccionarPalabra() {
        ArrayList<String> palabras;
        int numAleatorio = Metodos.randomNum(0, 2);
        switch (numAleatorio){
            case 0:
                palabras = Metodos.leerArchivo("carnes.txt");
                break;
            case 1:
                palabras = Metodos.leerArchivo("vegetales.txt");
                break;
            default:
                palabras = Metodos.leerArchivo("frutas.txt");
        }


        int numRandom = Metodos.randomNum(0, palabras.size());

        return palabras.get(numRandom);
    }
}
