import java.util.ArrayList;

public class JuegoDividir {



    static void juegodeDividir(char niveldeDificultadInterno) {
        ArrayList<NumeroJuego> juegoCompleto = new ArrayList<>();
        int contador = 0;
        final int REPETICIONES = 5;
        final int JUEGO = 4;//juego 1 corresponde al juego de las sumas
        ArrayList<Integer> respostas;
        NumeroJuego juego=null;

        do {

            switch (niveldeDificultadInterno){
                case 'A':
                    juego = Metodos.mostrarNumerosJuego('/', 1, 10,niveldeDificultadInterno,contador,0);
                    break;
                case 'B':
                    juego = Metodos.mostrarNumerosJuego('/', 5, 12,niveldeDificultadInterno,contador,0);
                    break;
                case 'C':
                    juego = Metodos.mostrarNumerosJuego('/', 10, 20,niveldeDificultadInterno,contador,0);
                    break;
            }

            respostas = Metodos.introRespuestaJuego(juego, Menu.scan);
            System.out.println("Has acertado");
            juegoCompleto.add(new NumeroJuego(juego.getPrimerValor(), juego.getSegundoValor(), '+', respostas));
            contador++;
        } while (contador!=REPETICIONES);
        Metodos.finalJuego(juegoCompleto,contador,JUEGO,niveldeDificultadInterno);
    }




    static int[] randomDivision(int numeroDivision, char nivelDificultad) {
        int[] arraynumDivisores = numerosDivisibles(numeroDivision, nivelDificultad);
        int[] numeroAleatorioDivision = new int[2];
        int valor, primerdivisor = 0;
        //Dependiendo del nivel de dificultad toma unos valores aleatorios u otros.
        switch (nivelDificultad){
            case 'A':
                valor = Metodos.randomNum(1, arraynumDivisores.length - 1);
                primerdivisor = arraynumDivisores[valor];
                break;
            case 'B':
                valor = Metodos.randomNum(5, arraynumDivisores.length - 1);
                primerdivisor = arraynumDivisores[valor];
                break;
            case 'C':
                valor = Metodos.randomNum(10, arraynumDivisores.length - 1);
                primerdivisor = arraynumDivisores[valor];
                break;
        }
        numeroAleatorioDivision[0] = primerdivisor;
        numeroAleatorioDivision[1] = numeroDivision;
        return numeroAleatorioDivision;
    }

    public static int[] numerosDivisibles(int numDivision, char nivelDificultad) {
        int contador = 0;
        int tamanoArray = 0;
        switch (nivelDificultad){
            case 'A':
                tamanoArray = 8;
                break;
            case 'B':
                tamanoArray = 30;
                break;
            case 'C':
                tamanoArray = Metodos.randomNum(30, 50);//Hace que 'tamanoArray' sea aleaorio
                break;
        }

        int[] arraynumDivisores = new int[tamanoArray];

        for (int i = 1; i <= 1000; i++) {

            if (i % numDivision == 0 && i != 0) {
                arraynumDivisores[contador] = i;
                contador++;
            }
            if (contador == tamanoArray)
                break;

        }
        return arraynumDivisores;
    }


}


