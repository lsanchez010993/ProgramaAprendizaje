import java.util.ArrayList;

public class JuegoRestas {



    static void juegodeRestas(char niveldeDificultadInterno) {//TODO: Atencion, jamás hay que quitar "char niveldeDificultadInterno", aun cuando el IDE marque que no se utiliza.
         ArrayList<NumeroJuego> juegoCompleto = new ArrayList<>();
        int contador = 0;
        final int REPETICIONES = 3;
        final int JUEGO = 2;//juego 1 corresponde al juego de las sumas
        ArrayList<Integer> respostas;
        NumeroJuego juego;

        do {
            if (niveldeDificultadInterno == 'A') {

                juego = Metodos.mostrarNumerosJuego('-', 1, 10,niveldeDificultadInterno,contador,0);
            } else if (niveldeDificultadInterno == 'B') {

                juego = Metodos.mostrarNumerosJuego('-', 10, 50,niveldeDificultadInterno,contador,0);
            } else {

                juego = Metodos.mostrarNumerosJuego('-', 30, 100,niveldeDificultadInterno,contador,0);
            }

            respostas = Metodos.introRespuestaJuego(juego, Menu.scan);
            System.out.println("Has acertado");
            juegoCompleto.add(new NumeroJuego(juego.getPrimerValor(), juego.getSegundoValor(), '-', respostas));
            contador++;
            //TODO: Añadir marcador al while para que el juego se repita un numero determinado de veces:
        } while (REPETICIONES != contador);
        Metodos.finalJuego(juegoCompleto,contador,JUEGO,niveldeDificultadInterno);

    }






}


