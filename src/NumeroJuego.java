import java.util.ArrayList;

public class NumeroJuego {
    private int primerValor;
    private int segundoValor;
    private int resultado;
    private char operador;
    private int numero_a_Adivinar;
    private ArrayList<Integer> respuesta;


    public NumeroJuego(int primerValor, int segundoValor, char operacion, ArrayList<Integer> respuesta) {
        this.primerValor = primerValor;
        this.segundoValor = segundoValor;
        this.resultado = obtenerResultado(operacion);
        this.operador = operacion;
        this.respuesta = respuesta;
    }
    public NumeroJuego(int primerValor, int segundoValor, int numero_a_Adivinar) {
        this.primerValor = primerValor;
        this.segundoValor = segundoValor;
        this.numero_a_Adivinar=numero_a_Adivinar;

    }
    public NumeroJuego(int primerValor, int segundoValor, ArrayList<Integer> respuesta) {
        this.primerValor = primerValor;
        this.segundoValor = segundoValor;
        this.respuesta = respuesta;

    }

    public int getNumero_a_Adivinar() {
        return numero_a_Adivinar;
    }

    public ArrayList<Integer> getRespuesta() {
        return respuesta;
    }

    public int getPrimerValor() {
        return primerValor;
    }

    public int getSegundoValor() {
        return segundoValor;
    }

    public int getResultado() {
        return resultado;
    }

    public char getOperador() {
        return operador;
    }


    @Override
    public String toString() {
        return infoJuego();
    }

    private int obtenerResultado(char operacion) {

        switch (operacion) {
            case '+':
                return getPrimerValor() + getSegundoValor();

            case '-':
                return getPrimerValor() - getSegundoValor();

            case '*':
                return getPrimerValor() * getSegundoValor();

            case '/':
                return getPrimerValor() / getSegundoValor();

            default:
                return -1;

        }
    }

    private String infoJuego() {
        StringBuilder result = new StringBuilder();
        result.append(getPrimerValor()).append(getOperador()).append(getSegundoValor()).append("=").append(getResultado());
        int contador = 1;

        if (getRespuesta().size() > 1) {
            if (getRespuesta().size() == 2) {
                result.append(" | Respuesta incorrecta: ");
            } else {
                result.append(" | Respuestas incorrectas: ");
            }
            for (int inten : getRespuesta()) {
                if (contador == getRespuesta().size() - 1) {
                    result.append(inten);
                    break;
                }

                result.append(inten).append(", ");
                contador++;
            }

        } else result.append(" | OK");

        return result.toString();
    }

}
