

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class JuegoAdivinarAlimento {
    static int numAnterior = 10;

    static int rangoMin, rangoMax;
    static ArrayList<String> listaCondicionada = new ArrayList<>();
    static char[] palabra_A_Adivinar;
    static char[] palabraEnCaracteres;
    static boolean noMasPistas = false;
    static int numGuiones_A_Comparar;

    static final int JUEGO = 6;
    static int numGuiones;

    public static void adivinarAlimento(char niveldeDificultadInterno) {
        String palabra;
        int numCategoria;
        boolean salir = false;


        //TODO:Funcion recursiva para seleccionar una categoria distinta cada vez.
        numCategoria = seleccionarNumero();
        mostrarCategoria(numCategoria);
        ArrayList<String> lista = listaPalabras(numCategoria);
        //Dependiendo del nivel de dificultad escogido, genera una lista de palabras:
        listaCondicionada(lista, niveldeDificultadInterno);
        //guada la lista condicionada en variable estatica "listaCondicionada".


        do {
            noMasPistas = false;
            palabra = palabraEscogida();
            int longitudPalabra = palabra.length();

//            System.out.println(palabra);
            palabraEnCaracteres = palabra.toCharArray();
            if (palabra.equalsIgnoreCase("ERROR")) {
                System.out.println("FIN DE NIVEL");
                QuieresVolver_a_Jugar.quieresVolver_A_Jugar(6);
            }

            palabra_A_Adivinar = new char[longitudPalabra];
            Arrays.fill(palabra_A_Adivinar, '_');

            palabra = Metodos.quitarAcentos(palabra);

            System.out.println("Adivina la palabra. Si quieres una pista, escribe 'pista':");
            palabra_A_Adivinar[0] = (palabraEnCaracteres[0]);
            palabra_A_Adivinar[longitudPalabra - 1] = (palabraEnCaracteres[longitudPalabra - 1]);
            mostrar(palabra_A_Adivinar);


            do {

                String respuesta = Menu.scan.next();
                Menu.scan.nextLine();
                if (!noMasPistas) {
                    if (respuesta.equalsIgnoreCase("pista")) {
                        ayuda();
                        System.out.println("Prueba de nuevo:");

                    } else if (respuesta.equalsIgnoreCase(palabra)) {
                        System.out.println("Has acertado");
                        salir = true;
                    } else {
                        System.out.println("Palabra incorrecta.\nVuelve a intentarlo:");

                    }
                } else if (respuesta.equalsIgnoreCase("pista")) {
                    System.out.println("Has sido eliminado");
                    System.out.println("La palabra era: " + palabra);
                    salir = true;
                } else if (!respuesta.equalsIgnoreCase(palabra)) {
                    System.out.println("Palabra incorrecta.\nVuelve a intentarlo:");

                } else {
                    System.out.println("Has acertado");
                    salir = true;
                }
            } while (!salir);
            QuieresVolver_a_Jugar.quieresVolver_A_Jugar(JUEGO);
        } while (true);
    }


    public static void ayuda() {

        ArrayList<Integer> posicionGuiones = posicionesGuiones();//aparte de su funcion, tambien cuenta el numero de guines que contiene la palabra
        int posicionAleatoria, numAleatorio;
        Random random = new Random();
        numAleatorio = random.nextInt(posicionGuiones.size());
        posicionAleatoria = posicionGuiones.get(numAleatorio);
//      sustituir un guion aleatorio por un caracter:
        palabra_A_Adivinar[posicionAleatoria] = palabraEnCaracteres[posicionAleatoria];
        mostrar(palabra_A_Adivinar);
        numGuiones--;//aqui resto el guion, ya que he sustituido un guion por un caracter


        if (numGuiones == numGuiones_A_Comparar) {
            System.out.println("\u001B[31m" + "Atencion!!" + "\nSi vuelves a pedir una pista habrás perdido\u001B[0m");

            noMasPistas = true;
        }
    }

    public static ArrayList<Integer> posicionesGuiones() {
        numGuiones = 0;
        ArrayList<Integer> posicionGuiones = new ArrayList<>();
        for (int i = 1; i < palabra_A_Adivinar.length; i++) {
            if (palabra_A_Adivinar[i] == '_') {
                posicionGuiones.add(i);
                numGuiones++;
            }
        }
        return posicionGuiones;
    }

    public static int seleccionarNumero() {
        int numSeleccionado;
        numSeleccionado = Metodos.randomNum(1, 3);
        if (numAnterior != numSeleccionado) {
            numAnterior = numSeleccionado;
            return numSeleccionado;
        } else return seleccionarNumero();
    }


    public static String palabraEscogida() {

        if (listaCondicionada.isEmpty()) {
            return "ERROR";
        }

        Random random = new Random();
        int numAleatorio = random.nextInt(listaCondicionada.size());
        String palabra = listaCondicionada.get(numAleatorio);
        listaCondicionada.remove(numAleatorio);
        return palabra;
    }

    public static void mostrarCategoria(int categoria) {
        String nombre = seleccionarCategoria(categoria);


        System.out.println("Categoria: " + nombre);

    }

    private static String seleccionarCategoria(int categoria) {
        String nombreArchivo;

        switch (categoria) {
            case 1:
                nombreArchivo = "vegetales";

                break;
            case 2:
                nombreArchivo = "frutas";

                break;
            default:
                nombreArchivo = "carnes";

                break;
        }
        return nombreArchivo;
    }

    private static ArrayList<String> listaPalabras(int categoria) {
        String nombreArchivo = seleccionarCategoria(categoria);
        try {
            File f = new File("src/archivos" + File.separator + nombreArchivo + ".txt");
            Scanner reader = new Scanner(f);
            String s;
            ArrayList<String> lista = new ArrayList<>();
            while (reader.hasNext()) {
                s = reader.next();
                lista.add(s);
            }
            reader.close();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Devuelve una lista vacía si ocurre un error
    }

    private static void listaCondicionada(ArrayList<String> lista, char nivelDificultad) {
        comprobarNivel(nivelDificultad);

        for (String palabraCondicionada : lista) {
            if (palabraCondicionada.length() > rangoMin && palabraCondicionada.length() <= rangoMax) {
                listaCondicionada.add(palabraCondicionada.toLowerCase());


            }
        }
    }

    private static void comprobarNivel(char dificultad) {
        //dependiendo del nivel de dificultad, la longitud de las palabras varian.
        //tambien el numero de pistas varia dependiendo del nivel.
        dificultad = Character.toLowerCase(dificultad);


        if (dificultad == 'a') {
            rangoMin = 3;
            rangoMax = 5;
            numGuiones_A_Comparar = 1;
        } else if (dificultad == 'b') {
            rangoMin = 6;
            rangoMax = 8;
            numGuiones_A_Comparar = 2;
        } else {
            rangoMin = 6;
            rangoMax = 15;
            numGuiones_A_Comparar = 3;
        }
    }

    static void mostrar(char[] guiones_Y_aciertos) {
        for (char guionesYAcierto : guiones_Y_aciertos) {
            System.out.print(guionesYAcierto + " ");
        }
        System.out.println();
    }
}












