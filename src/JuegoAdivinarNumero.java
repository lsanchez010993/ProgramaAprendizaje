import java.util.ArrayList;

public class JuegoAdivinarNumero {
    static int resposta;
    static int numero;

    public static boolean adivinarNum=false;


    static final int JUEGO = 5;


    static void juegodeAdivinar(char niveldeDificultadInterno) {
        adivinarNum=true;
        ArrayList<NumeroJuego> juegoCompleto = new ArrayList<>();
        int contador = 0;
        final int REPETICIONES = 5;
        final int JUEGO = 1;//juego 1 corresponde al juego de las sumas
        ArrayList<Integer> respostas;
        NumeroJuego juego1=null;

        switch (niveldeDificultadInterno) {
            case 'A':
                juego1 = Metodos.mostrarNumerosJuego(null, 1, 30,niveldeDificultadInterno,contador,10);
                break;
            case 'B':
                juego1 = Metodos.mostrarNumerosJuego(null, 1, 50,niveldeDificultadInterno,contador,10);
                break;
            case 'C':
                juego1 = Metodos.mostrarNumerosJuego(null, 1, 100,niveldeDificultadInterno,contador,10);
                break;
        }
        respostas = Metodos.introRespuestaJuego(juego1, Menu.scan);
        System.out.println("Has acertado");
        juegoCompleto.add(new NumeroJuego(juego1.getPrimerValor(),juego1.getSegundoValor(), respostas));
        contador++;
    }


    public static void juego(int numIntentos) {

        do {
//
            if (sinIntentos(--numIntentos)) break;

            if (numMasGrande()) {
                Metodos.mostrarMensaje("Es un numero más grande");
            } else {
                Metodos.mostrarMensaje("Es un numero más pequeño");
            }

            if (numDiferente()) {
                Metodos.mostrarMensaje("Vuelve a intentarlo:");
                resposta = Menu.scan.nextInt();

            }
            if (numIgual()) {
                Metodos.mostrarMensaje("Felicidades, has acertado");

            }

        } while (!numIgual());


    }

    private static boolean numMasGrande() {
        return resposta < numero;
    }

    private static boolean numIgual() {
        return resposta == numero;
    }

    private static boolean numDiferente() {
        return resposta != numero;
    }

    private static boolean sinIntentos(int intentosMax) {

        if (intentosMax != 0) Metodos.mostrarMensaje("Te quedan " + (intentosMax) + " intentos");
        else hasPerdido();
        return intentosMax == 0;
    }

    public static void hasPerdido() {
        Metodos.mostrarMensaje("Has perdido");
        Metodos.mostrarMensaje("Era el numero: " + numero);


    }


}
