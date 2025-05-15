

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Metodos {


    public static void nivelDificultad(char letraNivel) {
        if (letraNivel == 'A')
            System.out.println("Nivel de dificultad: sencillo");
        else if (letraNivel == 'B')
            System.out.println("Nivel de dificultad: Medio");
        else if (letraNivel == 'C')
            System.out.println("Nivel de dificultad: Dificil");
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }


    public static void mostrarMensaje(ArrayList<Character> caracteres) {
        for (int i = 0; i < caracteres.size(); i++) {
            System.out.print(caracteres.get(i) + " ");
        }
        System.out.println();
    }

    public static void mostrarCaracteresAcertados(ArrayList<Character> caracteresAleatoriosArrayListInterno, ArrayList<Integer> posicionesAciertos) {


        for (int i = 0; i < caracteresAleatoriosArrayListInterno.size(); i++) {
            char caracter = caracteresAleatoriosArrayListInterno.get(i);
            if (posicionesAciertos.contains(i)) {
                // Si la posición está en la lista de aciertos, imprime el carácter en rojo
                System.out.print("\u001B[32m" + caracter + " \u001B[0m");
            } else {
                System.out.print(caracter + " ");
            }
        }
        System.out.println();
    }

    public static void mostrarCaracteresCambiados(ArrayList<Character> caracteresAleatoriosArrayListInterno, ArrayList<Integer> posicionesAciertos) {


        for (int i = 0; i < caracteresAleatoriosArrayListInterno.size(); i++) {
            char caracter = caracteresAleatoriosArrayListInterno.get(i);
            if (posicionesAciertos.contains(i)) {
                // Si la posición está en la lista de aciertos, imprime el carácter en rojo
                System.out.print("\u001B[31m" + caracter + " \u001B[0m");
            } else {
                System.out.print(caracter + " ");
            }
        }
        System.out.println(); // Agregar un salto de línea al final
    }

    public static void mostrarCaracteres(ArrayList<Character> caracteresAleatoriosArrayListInterno) {


        for (int i = 0; i < caracteresAleatoriosArrayListInterno.size(); i++) {
            char caracter = caracteresAleatoriosArrayListInterno.get(i);

            System.out.print(caracter + " ");

        }
        System.out.println(); // Agregar un salto de línea al final
    }

    public static <T> void mostrarMensaje(T mensaje, boolean saltoLinea) {
        if (saltoLinea) System.out.println(mensaje);
        else System.out.print(mensaje);

    }


    public static void mostrarMensajeFP(int numero) {
        if (numero == 1)
            System.out.println(numero + " caracter");
        else if (numero == 0 || numero > 1) System.out.println(numero + " caracteres");
    }


    public static void mostrarMensaje(char[] caracteresAleatorios) {
        for (char ch : caracteresAleatorios) {
            System.out.print(ch + " ");
        }
        System.out.println();
    }


    static char introChar(Scanner scan, char[] array) {
        do {

            char c = Character.toUpperCase(scan.nextLine().trim().charAt(0));
            for (char i : array) {
                if (i == c) {
                    return c;
                }
            }
            System.out.println("El caràcter introduït no està en la llista.");
        } while (true);

    }

    static ArrayList<Integer> introRespuestaJuego(NumeroJuego juego, Scanner scan) {
        int resultado =0;
        if (JuegoAdivinarNumero.adivinarNum){
             resultado = juego.getNumero_a_Adivinar();
        }else{
             resultado = juego.getResultado();
        }

        ArrayList<Integer> respuestas = new ArrayList<>();
        int c = -1;
        boolean bandera = false;
        do {
            // Verificar si el usuario ha ingresado un número entero
            if (scan.hasNextInt()) {
                c = scan.nextInt();
                scan.nextLine();
                respuestas.add(c);
                // Verificar si el número ingresado es igual al total
                if (resultado == c) {
                    bandera = true;

                } else {
                    System.out.println("La respuesta no es correcta. Vuelve a intentarlo");

                    Metodos.mostrarMensaje("¿Cuanto es " + juego.getPrimerValor() + "+ " + juego.getSegundoValor() + "?", true);
                }
            } else {
                System.out.println("Por favor, ingresa un número entero.");
                scan.nextLine();
            }
        } while (!bandera);
        return respuestas;
    }

    static int introOperacion(Scanner scan, int[] array) {
        int c = -1;
        boolean encontrada = false;
        do {
            // Verificar si el usuario ha ingresado un número entero
            if (scan.hasNextInt()) {
                c = scan.nextInt();
                scan.nextLine();
                // Verificar si el número ingresado está en el array
                for (int i = 0; i < array.length; ++i) {
                    if (array[i] == c) {
                        encontrada = true;
                        break;
                    }
                }
                if (!encontrada) {
                    System.out.println("El número introducido no está en la lista.");
                }
            } else {
                // Si el usuario no ha ingresado un número entero, mostrar un mensaje de error
                System.out.println("Tienes que introducir un numero.");
                scan.nextLine();
            }
        } while (!encontrada);
        return c;
    }

    public static NumeroJuego mostrarNumerosJuego(Character operacion, int min, int max, char nivelDificultad, int contador, int numeroIntentos) {
        int primerNum = Metodos.randomNum(max, min);
        int segundoNum = Metodos.randomNum(max, min);
        NumeroJuego juego = null;
        if (contador == 0) Metodos.nivelDificultad(nivelDificultad);
        int[] numsOrdenados;
        if (operacion != null) {
            switch (operacion) {
                case '-':
                    numsOrdenados = ordenarNumeros(primerNum, segundoNum);
                    juego = new NumeroJuego(numsOrdenados[0], numsOrdenados[1], operacion, null);
                    break;
                case '+', '*':
                    juego = new NumeroJuego(primerNum, segundoNum, operacion, null);
                    break;
                case '/':
                    numsOrdenados = JuegoDividir.randomDivision(primerNum, nivelDificultad);
                    juego = new NumeroJuego(numsOrdenados[0], numsOrdenados[1], operacion, null);


                    break;
                default:
                    System.out.println("Error");
            }
        }else{
            return mostrarNumerosJuego(min,max,nivelDificultad,contador,numeroIntentos);


        }

            Metodos.mostrarMensaje("¿Cuanto es " + juego.getPrimerValor() + juego.getOperador() + juego.getSegundoValor() + "?", true);
            Metodos.mostrarMensaje(juego.getResultado(), true);
            return juego;
        }
    public static NumeroJuego mostrarNumerosJuego(int min, int max, char nivelDificultad, int contador, int numeroIntentos) {
        NumeroJuego juego = null;
        int numeroAdivinar= Metodos.randomNum(min,max);
        juego = new NumeroJuego(min, max,numeroAdivinar);
        Metodos.mostrarMensaje("Tienes " + numeroIntentos + " intentos para superar este nivel");

        System.out.println(juego.getNumero_a_Adivinar());
        Metodos.mostrarMensaje("¿En qué numero estoy pensando? (entre el " + juego.getPrimerValor() + " y el " + juego.getSegundoValor()+")");
        return juego;
    }

        private static int[] ordenarNumeros ( int primerNum, int segundoNum){
            int[] numsOrdenados = new int[2];
            if (primerNum > segundoNum) {
                numsOrdenados[0] = primerNum;
                numsOrdenados[1] = segundoNum;
            } else {
                numsOrdenados[0] = segundoNum;
                numsOrdenados[1] = primerNum;
            }
            return numsOrdenados;
        }


        public static void mostrarOperacionesRealizadas (ArrayList < NumeroJuego > juegoCompleto,int contador){
            contador--;
            if (contador > 0) {
                System.out.println("\nOperaciones realizadas:");
                for (NumeroJuego n : juegoCompleto) {
                    if (n != null)
                        System.out.println("\t" + n);
                }
                System.out.println();
            }
        }

        static int randomNum ( int min, int max){
            return (int) (Math.random() * (max - min + 1) + min);
        }


        static int[] randomNum ( int cuantos, int min, int max){
            int[] numGenerados = new int[cuantos];
            for (int i = 0; i < cuantos; i++) {
                numGenerados[i] = randomNum(min, max);
            }
            return numGenerados;
        }

        static int[] numRandomNoRepe ( int cuantos, int min, int max){
            int[] numGenerados = new int[cuantos];
            Random random = new Random();

            for (int i = 0; i < cuantos; i++) {
                int numeroGenerado;

                do {
                    numeroGenerado = random.nextInt(max - min + 1) + min;
                } while (contieneNumero(numGenerados, i, numeroGenerado));

                numGenerados[i] = numeroGenerado;
            }

            return numGenerados;
        }

        static boolean contieneNumero ( int[] array, int limite, int numero){
            for (int i = 0; i < limite; i++) {
                if (array[i] == numero) {
                    return true;
                }
            }
            return false;
        }


        static void printArrayInt ( int[] array, int numeros_A_Mostrar, boolean sameLine, String separator, String
        finalCadena){
            int l;
            if (numeros_A_Mostrar == 0) {
                l = array.length - 1;
            } else {
                l = numeros_A_Mostrar - 1;
            }

            for (int i = 0; i < l; ++i) {
                if (sameLine) {
                    if (separator.length() == 0) separator = " ";
                    System.out.print(array[i] + separator);


                } else System.out.println(array[i]);
            }

            System.out.println(array[l] + finalCadena);
        }
        public static void finalJuego (ArrayList < NumeroJuego > juegoCompleto,int contador, int JUEGO,
        char niveldeDificultadInterno){
            Metodos.mostrarMensaje("Felicidades, has superado el nivel");
            Metodos.mostrarOperacionesRealizadas(juegoCompleto, contador);
            Menu.niveldeDificultad = niveldeDificultadInterno;
            juegoCompleto.clear();
            QuieresVolver_a_Jugar.quieresVolver_A_Jugar(JUEGO);
        }
        public static ArrayList<String> leerArchivo (String nombreArchivo){
            ArrayList<String> lista = new ArrayList<>();

            try {
                File f = new File("src/archivos" + File.separator + nombreArchivo);
                Scanner reader = new Scanner(f);

                while (reader.hasNextLine()) {
                    lista.add(reader.nextLine());


                }

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return lista;
        }

        public static ArrayList<String> procesarArchivo (String nombreArchivo){
            ArrayList<String> lista = new ArrayList<>();

            try {
                File f = new File("src/archivos" + File.separator + nombreArchivo);
                Scanner reader = new Scanner(f);

                while (reader.hasNextLine()) {
                    String linea = reader.nextLine();
                    String[] palabras = linea.split(" ");
                    for (String palabra : palabras) {
                        if (palabra.isEmpty()) {
                            continue;
                        }
                        if (!Character.isLowerCase(palabra.charAt(0))) {
                            continue;
                        }
                        if (!Character.isLetter(palabra.charAt(palabra.length() - 1))) {
                            continue;
                        }
                        if (palabra.length() < 5) {
                            continue;
                        }
                        lista.add(palabra);
                    }

                }

                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return lista;
        }


        public static String quitarAcentos (String palabra){

            palabra = palabra.replace("á", "a");


            palabra = palabra.replace("é", "e");


            palabra = palabra.replace("í", "i");


            palabra = palabra.replace("ó", "o");


            palabra = palabra.replace("ú", "u");

            return palabra;
        }

        public static ArrayList<String> quitarAcentos (ArrayList < String > palabra) {
            ArrayList<String> palabraArray = new ArrayList<>();
            for (String s : palabra) {
                s = s.toLowerCase();//cambia a minusculas
                s = s.replace("á", "a");
                s = s.replace("é", "e");
                s = s.replace("í", "i");
                s = s.replace("ó", "o");
                s = s.replace("ú", "u");
                palabraArray.add(s);
            }


            return palabraArray;
        }


    }




