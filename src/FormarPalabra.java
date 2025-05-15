import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class FormarPalabra {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Character> letrasAleatoriasEnArrayList;

    static ArrayList<Character> palabraChar;

    static ArrayList<JuegoFP> datosJuego = new ArrayList<>();
    static ArrayList<Character> letrasAbecedario = abecedarioEnList();//abecedario guardado en arrayList
    static int repeticiones = 0;

    static boolean repetirJuego;
    static final int JUEGO = 8;

    static public void formarPalabra(char niveldeDificultad) {


        juegoFormarPalabra();


        //TODO: El siguiente metodo solo debe utilizarse una vez se añaden nuevas palabras a la lista.
        //generarArchivo();


    }

    /**
     * Inicio de juego
     * Metodo que llama a las distintas funciones que forman el juego
     */

    private static void juegoFormarPalabra() {
        String palabra;
        Menu.niveldeDificultad = 'D';

        letrasAleatoriasEnArrayList = letrasNoRepetidas(6); //Devuelve letras no repetidas
        do {
            Metodos.mostrarMensaje("Forma una palabra con las siguientes letras:");
            //mostrar caracteres:
            Metodos.mostrarMensaje(letrasAleatoriasEnArrayList);
            System.out.println();
            do {
                Metodos.mostrarMensaje("Escribe una palabra:");
                palabra = scan.nextLine().trim().toLowerCase();
                palabraChar = transformar_a_ArrayList(palabra);

                crearObjeto(letrasAleatoriasEnArrayList);
                comprobarAciertos();
                Metodos.mostrarMensaje("Tienes: " + puntuacion() + " puntos");
                hasGanado();//comprueba y actualiza la variable repetir juego

                repeticiones++;
            } while (!hasGanado());
        } while (repeticiones > 0);
    }

    /**
     * Verifica si el usuario ha ganado o perdido
     *
     * @return Basandose en la puntuacion obtenida devuelve verdadero o falso.
     */
    public static boolean hasGanado() {

        if (puntuacion() >= 200) {
            System.out.println("Has ganado");
            Menu.niveldeDificultad = 'C';
            System.out.println(datosJuego);
            QuieresVolver_a_Jugar.quieresVolver_A_Jugar(JUEGO);

            return true;


        } else return false;
    }

    /**
     * @return Devuelve un integer con la puntuacionobtenida hasta el momento.
     */
    public static int puntuacion() {
        int puntuacion = 0;
        for (int i = 0; i < datosJuego.size(); i++) {
            puntuacion += datosJuego.get(i).puntuacion;

        }
        return puntuacion;
    }

    /**
     * Dependiendo del numero de caracteres acertados realiza una opción u otra.
     */
    public static void comprobarAciertos() {
        int numAciertos = datosJuego.get(repeticiones).caracteresAcertados.size();

        switch (numAciertos) {
            case 1:
                System.out.println("Un caracter acertado: "+ datosJuego.get(repeticiones).caracteresAcertados.get(0));
                puntuacion();
                juegoFormarPalabra();
                break;

            case 2:
                repetirJuego = true;
                mostrarAcirtos();
                pausarPrograma();
//                limpiarPantalla();
                mostrarNuevosCaracteres();
                break;
            case 3:
                repetirJuego = true;
                mostrarAcirtos();
                pausarPrograma();
//                limpiarPantalla();
                mostrarCaracteresRestantes();


                break;
            case 4, 5:

                mostrarAcirtos();
                repetirJuego = false;
                break;




            default:
                repetirJuego = true;

        }
    }

    public static void pausarPrograma() {
        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(2 * 500);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Muestra por pantalla los nuevos caracteres generados
     */
    public static void mostrarNuevosCaracteres() {
        Metodos.mostrarMensaje("Nuevos caracteres: ");
        Metodos.mostrarCaracteresCambiados(sustituirAciertos(letrasAleatoriasEnArrayList, datosJuego.get(repeticiones).getPosicionAciertos()),
                datosJuego.get(repeticiones).getPosicionAciertos());
    }

    /**
     * Muestra por pantalla los caracteres restantes
     */
    public static void mostrarCaracteresRestantes() {
        Metodos.mostrarMensaje("Forma una palabra con los caracteres restantes: ");
        Metodos.mostrarCaracteres(eliminarAciertos(letrasAleatoriasEnArrayList, datosJuego.get(repeticiones).getPosicionAciertos()));
    }

    /**
     * Muestra por pantalla los nuevos caracteres acertados
     */
    public static void mostrarAcirtos() {
        Metodos.mostrarMensaje("Has acertado: ");
        Metodos.mostrarMensajeFP(datosJuego.get(repeticiones).getCaracteresAcertados().size());//muestra el numero de aciertos


        Metodos.mostrarCaracteresAcertados(letrasAleatoriasEnArrayList, datosJuego.get(repeticiones).getPosicionAciertos());//muestra en verde los caracteres acertados
    }

    /**
     * @param caracteresAleatoriosArrayListInterno contiene los caracteres aleatorios generados utilizados dentro de la funcion
     * @param posicionesAciertos                   contiene las posiciones de los caracteres acertados. Sirve para contar cuantos
     *                                             caracteres deven generarse.
     * @return devuelve caracteres aleatorios no repetidos
     */
    public static ArrayList<Character> sustituirAciertos(ArrayList<Character> caracteresAleatoriosArrayListInterno,
                                                         ArrayList<Integer> posicionesAciertos) {
// TODO: para aclarar: Por un lado estan los 'caracteresAleatorios' (que se toman a partir del abecedario) y por otro las 'letras'
        //todo: que tambien se toman a partir de ese mismo abecedario, por lo que no se repiten.
        ArrayList<Character> letras = letrasNoRepetidas(posicionesAciertos);

        for (int i = 0; i < posicionesAciertos.size(); i++) {
            int posicion = posicionesAciertos.get(i);
            char nuevoCaracter = letras.get(i);
            caracteresAleatoriosArrayListInterno.set(posicion, nuevoCaracter);
        }
        letrasAleatoriasEnArrayList = caracteresAleatoriosArrayListInterno;

        return caracteresAleatoriosArrayListInterno; // Devuelve el ArrayList actualizado
    }

    /** Elimina los caracteres acertados y devuelve el resto
     * @param caracteresAleatoriosArrayListInterno Caracteres aleatorios
     * @param posicionesAciertos contiene las posiciones donde se encuentran los aciertod
     * @return devuelve los caracteres que no han sido acertados
     */
    public static ArrayList<Character> eliminarAciertos(ArrayList<Character> caracteresAleatoriosArrayListInterno,
                                                        ArrayList<Integer> posicionesAciertos) {
        // Crear una copia de la lista de posicionesAciertos para ordenarla en orden descendente
        ArrayList<Integer> posicionesAciertosDescendente = new ArrayList<>(posicionesAciertos);
        Collections.sort(posicionesAciertosDescendente, Collections.reverseOrder());

        for (int posicion : posicionesAciertosDescendente) {
            if (posicion >= 0 && posicion < caracteresAleatoriosArrayListInterno.size()) {
                caracteresAleatoriosArrayListInterno.remove(posicion);
            }
        }

        return caracteresAleatoriosArrayListInterno; // Devuelve el ArrayList actualizado
    }


    public static boolean existenRepes(ArrayList<Character> caracteresAleatoriosArrayListInterno,
                                       ArrayList<Character> letras) {
        for (int i = 0; i < caracteresAleatoriosArrayListInterno.size(); i++) {
            for (int j = 0; j < letras.size(); j++) {
                if (caracteresAleatoriosArrayListInterno.get(i) == letras.get(j)) {
                    return true;
                }
            }
        }
        return false;
    }


    static void crearObjeto(ArrayList<Character> caracteresAleatorios) {
        ArrayList<Character> palabraLetrasNoRepetidas;
        if (existenCaracteresRepetidos(palabraChar)) {
            //si hay caracteres repetidos, los elimina:
            palabraLetrasNoRepetidas = eliminarCaracteresRepetidos(palabraChar);


            datosJuego = datosJuego(caracteresAleatorios, palabraLetrasNoRepetidas);
        } else {
            datosJuego = datosJuego(caracteresAleatorios, palabraChar);

        }
    }

    public static ArrayList<JuegoFP> datosJuego(ArrayList<Character> caracteresAleatorios, ArrayList<Character> palabraChar) {

        ArrayList<Character> caracteresAcertados = new ArrayList<>();
        ArrayList<Integer> posicionAciertos = new ArrayList<>();
        for (int i = 0; i < palabraChar.size(); i++) {
            for (int j = 0; j < caracteresAleatorios.size(); j++) {
                if (palabraChar.get(i) == caracteresAleatorios.get(j)) {
                    posicionAciertos.add(j);//indica la posicion de los aciertos.
                    caracteresAcertados.add(caracteresAleatorios.get(j));//guarda el caracter acertado

                }
            }
        }
        JuegoFP juego = new JuegoFP(caracteresAleatorios, palabraChar, caracteresAcertados, posicionAciertos);
        datosJuego.add(juego);
        return datosJuego;
    }

    /**
     * Comprueba si la palabra introducida contiene caracteres repetidos
     * @param caracteres palabra introducida por el usuario
     * @return devuelve verdadero o falso dependiendo de si encuentra o no letras repetidas
     */
    static boolean existenCaracteresRepetidos(ArrayList<Character> caracteres) {
        ArrayList<Character> caracteresVistos = new ArrayList<>();
        for (char c : caracteres) {
            if (caracteresVistos.contains(c)) {
                return true;
            }
            caracteresVistos.add(c);
        }


        return false;
    }

    private static ArrayList<Character> eliminarCaracteresRepetidos(ArrayList<Character> caracteres) {
        ArrayList<Character> sinRepetir = new ArrayList<>();

        for (char c : caracteres) {
            if (!sinRepetir.contains(c)) {
                sinRepetir.add(c);
            }
        }

        return sinRepetir;
    }

    static ArrayList<Character> transformar_a_ArrayList(String palabra) {
        ArrayList<Character> palabraChar = new ArrayList<>();
        palabra = palabra.toUpperCase();//transforma a mayuscula.
        for (int i = 0; i < palabra.length(); i++) {
            palabraChar.add(palabra.charAt(i));// guardar en ArrayList

        }
        return palabraChar;
    }

    /**
     * @param cuantos numero de letras posiciones aleatorias
     * @return letras aleatorias no repetidas
     */
    private static ArrayList<Character> letrasNoRepetidas(int cuantos) {

        ArrayList<Character> letras = new ArrayList<>();
        int longAbecedario= letrasAbecedario.size();

        int[] numerosRandom = Metodos.numRandomNoRepe(cuantos, 0, longAbecedario);
        
        for (int i = 0; i < cuantos; i++) {
            letras.add(letrasAbecedario.get(numerosRandom[i]));//Las guarda.
        }
        Arrays.sort(numerosRandom);
//        System.out.println(Arrays.toString(numerosRandom));
        for (int i = cuantos - 1; i >= 0; i--) {
            letrasAbecedario.remove(numerosRandom[i]); // Las elimina.
//            System.out.println(letrasAbecedario);
        }

        return letras;

    }

    /**
     * Funcion que comprueba y devuelve una serie de caracteres no repetidos.
     *
     * @param posicionesAciertos Contiene las posiciones de los aciertos. De este modo se obtiene cuantas letras se deven generar.
     * @return devuelve las letras no repetidas
     */

    public static ArrayList<Character> letrasNoRepetidas(ArrayList<Integer> posicionesAciertos) {
        int cuantos = posicionesAciertos.size(); //guarda el numero de caracteres acertados
        return letrasNoRepetidas(cuantos); //llama y devuelve la funcion.

    }

    /**
     * @return devuelve las letras del abecedario
     */
    public static ArrayList<Character> abecedarioEnList() {
        ArrayList<Character> letrasAbecedario = new ArrayList<>();
        String abecedario = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,X,Y,Z";//falta la w, ñ
        String[] letrasArray = abecedario.split(",");
        char[] letras = new char[letrasArray.length];

        for (int i = 0; i < letrasArray.length; i++) {
            letras[i] = letrasArray[i].charAt(0);
        }
        for (int i = 0; i < letrasArray.length; i++) {
            letrasAbecedario.add(letras[i]);
        }
        return letrasAbecedario;
    }


    private static boolean numNoRepetido(int[] numerosRandom) {
        Arrays.sort(numerosRandom);

        for (int i = 1; i < numerosRandom.length; i++) {
            if (numerosRandom[i] == numerosRandom[i - 1]) {
                return false; // Si encuentra dos números iguales, retorna false
            }
        }

        return true; // Si no se encuentran números repetidos, retorna true
    }

    public static void limpiarPantalla() {

        try {

            new java.util.Scanner(System.in).nextLine();
            String sistemaOperativo = System.getProperty("os.name");
            ArrayList<String> comando = new ArrayList<>();
            if (sistemaOperativo.contains("Windows")) {
                comando.add("cmd");
                comando.add("/C");
                comando.add("cls");

            } else {
                comando.add("clear");
            }

            ProcessBuilder pb = new ProcessBuilder(comando);
            Process iniciarProceso = pb.inheritIO().start();
            iniciarProceso.waitFor();

        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla" + e.getMessage());
        }
    }

    public static void generarArchivo() {
        ArrayList<String> palabras;
        palabras = Metodos.procesarArchivo("palabras.txt");
        palabras = Metodos.quitarAcentos(palabras);
        palabras = eliminarPalabrasDuplicadas(palabras);
        guardarDatosEnArchivo(palabras, "src/archivos" + File.separator + "palabras.txt");

    }

    public static ArrayList<String> buscarEnArchivo(char[] letras) {
        ArrayList<String> palabras = Metodos.procesarArchivo("palabras.txt");


        return palabras;
    }

    public static ArrayList<String> eliminarPalabrasDuplicadas(ArrayList<String> palabras) {
        // Ordenar el ArrayList
        Collections.sort(palabras);

        // Crear un nuevo ArrayList para almacenar las palabras sin duplicados
        ArrayList<String> palabrasSinDuplicados = new ArrayList<>();

        // Iterar sobre las palabras ordenadas
        String palabraAnterior = "";
        for (String palabraActual : palabras) {
            // Verificar si la palabra actual es diferente a la palabra anterior
            if (!palabraActual.equals(palabraAnterior)) {
                palabrasSinDuplicados.add(palabraActual); // Agregar la palabra al nuevo ArrayList
            }
            palabraAnterior = palabraActual; // Actualizar la palabra anterior con la palabra actual
        }

        return palabrasSinDuplicados;
    }

    public static void guardarDatosEnArchivo(ArrayList<String> list, String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (archivo.delete()) {
            System.out.println(nombreArchivo + " Eliminado con exito");
        } else {
            System.out.println("Error al eliminar archivo");
        }

        // Utilizamos un bloque try-catch para manejar excepciones de entrada/salida (IOException)
        try {
            // Creamos un FileWriter para escribir en el archivo, con el segundo parámetro en true para habilitar la escritura en modo "append"
            FileWriter fileWriter = new FileWriter(nombreArchivo, true);

            // Creamos un PrintWriter para escribir los datos en el archivo
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Escribimos los datos en el archivo
            for (String palabra : list) {
                printWriter.println(palabra);
            }


            // Cerramos el PrintWriter
            printWriter.close();

            System.out.println("Datos guardados correctamente en el archivo.");

        } catch (IOException e) {
            // Manejo de excepciones
            System.out.println("Error al guardar los datos en el archivo: " + e.getMessage());
        }
    }


}
