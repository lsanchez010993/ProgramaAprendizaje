

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class JuegoAhorcado {
    static Scanner scan = new Scanner(System.in);
    public static char[] palabra;
    public static char[] guiones_Y_aciertos;
    public static char[] letrasErroneas = new char[7];// 7 es el numero  de palos que aparecen antes de que te ahorquen.
    ;


    static void juegodeAhorcado() {

        String palabraAleatoria;
        char letra;
        int intentos = 0, contador1 = 0;
        boolean hasPerdido = false, hasGanado = false;

        palabraAleatoria = seleccionarPalabra();
        prepararJuego(palabraAleatoria);
        do {
            System.out.println();
            System.out.println("Introduce una letra:");
            letra = scan.nextLine().trim().charAt(0);


            if (contieneLetra(letra) && intentos != 7) {//Si contiene la letra y el numero de intentos no es igual a 7 muestra por
                // pantalla los guines y la posicion donde se encuentra cada letra.
                if (intentos != 0) {
                    verLetrasIncorrectas();
                }

                mostrarPenjat(intentos);

                mostrar(guiones_Y_aciertos);
                System.out.println();
            } else {

                intentos++;
                letrasErroneas[contador1] = letra;
                contador1++;
            }
            if (intentos != 7 && !contieneLetra(letra)) {
                System.out.println();
                verLetrasIncorrectas();
//
                mostrarPenjat(intentos);
                mostrar(guiones_Y_aciertos);
            }

            if (!Arrays.toString(guiones_Y_aciertos).contains("_")) {//si se cumple la condicion significa que se han adivinado todas las letras
                System.out.println();
                System.out.println("Felicidades, has adivinado la palabra!");
                System.out.println();
                hasGanado = true;
                break;
            }
            if (intentos == 7) {
                mostrarPenjat(intentos);
                System.out.println("Has pedido");
                System.out.println("La palabra era: " + palabraAleatoria);
                System.out.println();
                hasPerdido = true;
                break;
            }
        } while (!hasGanado || !hasPerdido);
        Menu.niveldeDificultad = 'C';
        QuieresVolver_a_Jugar.quieresVolver_A_Jugar(7);
    }
    public static void verLetrasIncorrectas(){

        System.out.print("No contiene: ");
        Metodos.mostrarMensaje(letrasErroneas);

    }

    private static void prepararJuego(String palabraAleatoria) {
        guiones_Y_aciertos = new char[palabraAleatoria.length()];
        palabra = palabraAleatoria.toCharArray();//convierte a char la palabra almacenada en 'palabraAleatoria'.
        palabra = Metodos.quitarAcentos(String.valueOf(palabra)).toCharArray();
        Arrays.fill(guiones_Y_aciertos, '_');
        Arrays.fill(letrasErroneas, ' ');
        mostrar(guiones_Y_aciertos);
    }

    private static String seleccionarPalabra() {

        ArrayList<String> listaPalabras = new ArrayList<>();
        int seleccionarArray;
        seleccionarArray = Metodos.randomNum(1, 2);
        switch (seleccionarArray) {
            case 1:
                listaPalabras = Metodos.leerArchivo("Cosas.txt");
                System.out.println("Categoria: Cosas Aleatorias");
                break;
            case 2:
                listaPalabras = Metodos.leerArchivo("Paises.txt");
                System.out.println("Categoria: Paises");
                break;

        }
        return listaPalabras.get(Metodos.randomNum(0, listaPalabras.size()-1));
    }

    private static boolean contieneLetra(char letra) {
        boolean contieleLaLetra = false;
        for (int i = 0; i < palabra.length; i++) { //recorre el array palabra.
            if (letra == palabra[i]) {//verifica si la letra introducida por el usuario se encuentra entre las que
                // componen la palabra y, de ser el caso, activa una bandera.

                guiones_Y_aciertos[i] = letra;//En caso de que contenga la letra, la guarda en el array
                // char 'guiones_Y_aciertos'en la posición que le corresponde.
                contieleLaLetra = true;
            }

        }
        return contieleLaLetra;
    }

    static void mostrar(char[] guiones_Y_aciertos) {
        for (int i = 0; i < guiones_Y_aciertos.length; ++i) {
            System.out.print(guiones_Y_aciertos[i] + " ");
        }

    }


    static String[] mostrarMuneco = {
            "\n",
            "\n |\n",
            "\n |\n O\n",
            "\n |\n_O\n",
            "\n |\n_O_\n",
            "\n |\n_O_\n |\n",
            "\n |\n_O_\n |\n/\n",
            "\n |\n_O_\n |\n/ \\\n"
    };

    public static void mostrarPenjat(int intents) {
        System.out.print(mostrarMuneco[intents]);
    }
}