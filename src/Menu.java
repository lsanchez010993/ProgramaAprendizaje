import java.util.Scanner;

public class Menu {
    static Scanner scan = new Scanner(System.in);


    static char[] opcionesVolveraJugar = {'S', 'N', 'B', 'C', 'M'};


    static char niveldeDificultad;


    static char[] opcionesDificultad = {'A', 'B', 'C'};


    public static void menu() {
        int juego;
        int[] juegos = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};


        System.out.println("Menu de juegos:");

        System.out.println("\t1. Juego de las sumas");
        System.out.println("\t2. Juego de las restas");
        System.out.println("\t3. Juego de las multiplicaciones");
        System.out.println("\t4. Juego de las divisiones");
        System.out.println("\t5. Juego de adivinar numero");
        System.out.println("\t6. Juego de adivinar alimento");
        System.out.println("\t7. Juego del ahorcado");
        System.out.println("\t8. Juego de formar palabras");
        System.out.println("\t9. Juego de ordenar palabras");
        System.out.println("\t0. Salir de programa");
        System.out.println("\n¿A qué quieres jugar? Introduce una opción:");


        juego = Metodos.introOperacion(scan, juegos);

        switch (juego) {
            case 1:
                mensajeInicio("de las sumnas", 1);
                Juego_Sumas.juegodeSumas(niveldeDificultad);

            case 2:
                mensajeInicio("de las restas", 2);
                JuegoRestas.juegodeRestas(niveldeDificultad);

            case 3:
                mensajeInicio("de las multiplicaciones", 3);
                JuegoMultiplicar.juegodeMultiplicar(niveldeDificultad);

            case 4:
                mensajeInicio("de las divisiones", 4);
                JuegoDividir.juegodeDividir(niveldeDificultad);

            case 5:
                mensajeInicio("de adivinar el número", 5);
                JuegoAdivinarNumero.juegodeAdivinar(niveldeDificultad);

            case 6:
                mensajeInicio("de adivinar el alimento", 6);
                JuegoAdivinarAlimento.adivinarAlimento(niveldeDificultad);

            case 7:
                mensajeInicio("del ahorcado", 7);

                JuegoAhorcado.juegodeAhorcado();

            case 8:
                mensajeInicio("de formar palabras", 8);

                FormarPalabra.formarPalabra(niveldeDificultad);
            case 9:
                mensajeInicio("de ordenar palabras", 8);

                JuegoOrdenarPalabra.ordenarPalabra(niveldeDificultad);


            case 0:
                System.exit(0);
        }

    }

    public static void mensajeInicio(String nombreJuego, int juego) {
        System.out.println("Bienvenido al juego " + nombreJuego + ".");
        if (juego != 8 && juego != 7) {
            System.out.println("Selecciona el nivel (A) facil, (B) medio, (C) dificil para comenzar a jugar: ");
            niveldeDificultad = Metodos.introChar(scan, opcionesDificultad);
        }
    }
}

