import java.util.ArrayList;

public class JuegoFP {
    ArrayList<Character> caracteresAleatorios;
    ArrayList<Character> caracteresAcertados;
    ArrayList<Character> palabraChar;
    ArrayList<Integer> posicionAciertos;
    ArrayList<String> palabras;
    int puntuacion;


    public JuegoFP(ArrayList<Character> caracteresAleatorios, ArrayList<Character> palabra,
                   ArrayList<Character> caracteresAcertados, ArrayList<Integer> posicionAciertos) {
        this.caracteresAleatorios = caracteresAleatorios;
        this.palabraChar = palabra;
        this.caracteresAcertados = caracteresAcertados;
        this.posicionAciertos = posicionAciertos;
        this.puntuacion = calcularPuntuacion(caracteresAcertados);
        palabras=guardarPalabra(palabraChar);//funcion que guarda palabra  en char en ArrayList de String


    }

    private int calcularPuntuacion(ArrayList<Character> caracteresAcertados) {
        int numAciertos = caracteresAcertados.size();
        int puntuacion = 0;
        switch (numAciertos) {
            case 1: {
                puntuacion = -100;
                break;
            }
            case 2: {
                puntuacion = 100;
                break;
            }
            case 3: {
                puntuacion = 200;
                break;
            }
            case 4: {
                puntuacion = 400;
                break;
            }
            case 5: {
                puntuacion = 500;

                break;
            }

        }
        return puntuacion;
    }

    public ArrayList<Character> getCaracteresAleatorios() {
        return caracteresAleatorios;
    }

    public ArrayList<Character> getCaracteresAcertados() {
        return caracteresAcertados;
    }

    public ArrayList<Character> getPalabraChar() {
        return palabraChar;
    }

    public ArrayList<Integer> getPosicionAciertos() {
        return posicionAciertos;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    private ArrayList<String> guardarPalabra(ArrayList<Character> palabraChar) {
        ArrayList<String> palabra = new ArrayList<>();
        String s = "";
        for (char pal : palabraChar) {
            s += pal;

        }
        palabra.add(s);
        return palabra;
    }

    @Override
    public String toString() {
        String s = "";
        for (String palabra : palabras) {
            s += palabra + ", ";
        }
        return " " + s;

    }
}
