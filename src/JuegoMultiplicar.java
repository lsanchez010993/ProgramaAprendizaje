import java.util.ArrayList;

public class JuegoMultiplicar {



    static void juegodeMultiplicar(char niveldeDificultadInterno) {
        int JUEGO = 3;
        ArrayList<NumeroJuego> juegoCompleto = new ArrayList<>();
        NumeroJuego juego = null;
       final int REPETICIONES=5;
        int contador = 0;

        ArrayList<Integer> respuestas;

        do {
            if (niveldeDificultadInterno == 'A') {
                juego = Metodos.mostrarNumerosJuego('*', 1, 5,niveldeDificultadInterno,contador,0);
            } else if (niveldeDificultadInterno == 'B') {

                juego = Metodos.mostrarNumerosJuego('*', 3, 8,niveldeDificultadInterno,contador,0);
            } else if (niveldeDificultadInterno == 'C') {

                juego = Metodos.mostrarNumerosJuego('*', 4, 15,niveldeDificultadInterno,contador,0);
            }
            respuestas = Metodos.introRespuestaJuego(juego, Menu.scan);
            //TODO: Añadir marcador al while para que el juego se repita un numero determinado de veces:
            System.out.println("Has acertado");
            juegoCompleto.add(new NumeroJuego(juego.getPrimerValor(), juego.getSegundoValor(), '*', respuestas));
            contador++;
        } while (contador < REPETICIONES);
        Metodos.finalJuego(juegoCompleto,contador,JUEGO,niveldeDificultadInterno);
    }

}

