import java.util.ArrayList;

public class Juego_Sumas {



    static void juegodeSumas(char niveldeDificultadInterno) {

        ArrayList<NumeroJuego> juegoCompleto = new ArrayList<>();
        int contador = 0;
        final int REPETICIONES = 5;
        final int JUEGO = 1;//juego 1 corresponde al juego de las sumas
        ArrayList<Integer> respostas;
        NumeroJuego juego=null;
        do {

            switch (niveldeDificultadInterno) {
                case 'A':
                    juego = Metodos.mostrarNumerosJuego('+', 1, 10,niveldeDificultadInterno,contador,0);
                    break;
                case 'B':
                    juego = Metodos.mostrarNumerosJuego('+', 30, 50,niveldeDificultadInterno,contador,0);
                    break;
                case 'C':
                    juego = Metodos.mostrarNumerosJuego('+', 100, 200,niveldeDificultadInterno,contador,0);
                    break;
            }

            respostas = Metodos.introRespuestaJuego(juego, Menu.scan);
            System.out.println("Has acertado");
            juegoCompleto.add(new NumeroJuego(juego.getPrimerValor(), juego.getSegundoValor(), '+', respostas));
            contador++;

        } while (contador < REPETICIONES);
        Metodos.finalJuego(juegoCompleto,contador,JUEGO,niveldeDificultadInterno);

    }





}
