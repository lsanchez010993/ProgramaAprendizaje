public class QuieresVolver_a_Jugar {
    public static void quieresVolver_A_Jugar(int juego) {

        do {
            // Dependiendo del nivel de dificultad al que se esté jugando, mostrará un mensaje u otro.
            switch (Menu.niveldeDificultad) {
                case 'A':
                    System.out.println("¿Quieres volver a jugar? Escribe 'si' para repetir el nivel, 'no' para salir, 'B' para pasar al siguiente nivel o 'menu' para volver al menú:");
                    break;
                case 'B':
                    System.out.println("¿Quieres volver a jugar? Escribe 'si' para repetir el nivel, 'no' para salir, 'C' para pasar al siguiente nivel o 'menu' para volver al menú:");
                    break;
                case 'C':
                    System.out.println("¿Quieres volver a jugar? Escribe 'si' para repetir el nivel, 'no' para salir o 'menu' para volver al menú:");
                    break;
                default:
                    System.out.println("La opción introducida no existe.");
            }

            char opcion = Metodos.introChar(Menu.scan, Menu.opcionesVolveraJugar);
            // La función "introChar" valida la respuesta introducida por el usuario basándose en los datos que contiene el array.
            switch (opcion) {
                case 'S':
                    switch (juego) {
                        case 1:
                            Juego_Sumas.juegodeSumas(Menu.niveldeDificultad);
                            break;
                        case 2:
                            JuegoRestas.juegodeRestas(Menu.niveldeDificultad);
                            break;
                        case 3:
                            JuegoMultiplicar.juegodeMultiplicar(Menu.niveldeDificultad);
                            break;
                        case 4:
                            JuegoDividir.juegodeDividir(Menu.niveldeDificultad);
                            break;
                        case 5:
                            JuegoAdivinarNumero.juegodeAdivinar(Menu.niveldeDificultad);
                            break;
                        case 6:
                            JuegoAdivinarAlimento.adivinarAlimento(Menu.niveldeDificultad);
                            break;
                        case 7:
                            JuegoAhorcado.juegodeAhorcado();
                            break;
                        case 8:
                            FormarPalabra.formarPalabra(Menu.niveldeDificultad);
                            break;
                        default:
                            break;
                    }
                    break;
                case 'M':
                    Menu.menu();
                    break;
                case 'N':
                    System.out.println("Has salido del juego");
                    System.exit(0); // Termina el programa
                    break;
                case 'B':

                    switch (juego) {
                        case 1:

                            Menu.niveldeDificultad = 'B';
                            Juego_Sumas.juegodeSumas(Menu.niveldeDificultad);
                            break;
                        case 2:

                            Menu.niveldeDificultad = 'B';
                            JuegoRestas.juegodeRestas(Menu.niveldeDificultad);
                            break;
                        case 3:

                            Menu.niveldeDificultad = 'B';
                            JuegoMultiplicar.juegodeMultiplicar(Menu.niveldeDificultad);
                            break;
                        case 4:

                            Menu.niveldeDificultad = 'B';
                            JuegoDividir.juegodeDividir(Menu.niveldeDificultad);
                            break;
                        case 5:

                            Menu.niveldeDificultad = 'B';
                            JuegoAdivinarNumero.juegodeAdivinar(Menu.niveldeDificultad);
                            break;
                        case 6:

                            Menu.niveldeDificultad = 'B';
                            JuegoAdivinarAlimento.adivinarAlimento(Menu.niveldeDificultad);
                            break;
                        case 7:

                            Menu.niveldeDificultad = 'B';
                            JuegoAhorcado.juegodeAhorcado();
                            break;
                        default:
                            break;
                    }

                case 'C':

                    switch (juego) {
                        case 1:

                            Menu.niveldeDificultad = 'C';
                            Juego_Sumas.juegodeSumas(Menu.niveldeDificultad);
                            break;
                        case 2:

                            Menu.niveldeDificultad = 'C';
                            JuegoRestas.juegodeRestas(Menu.niveldeDificultad);
                            break;
                        case 3:

                            Menu.niveldeDificultad = 'C';
                            JuegoMultiplicar.juegodeMultiplicar(Menu.niveldeDificultad);
                            break;
                        case 4:

                            Menu.niveldeDificultad = 'C';
                            JuegoDividir.juegodeDividir(Menu.niveldeDificultad);
                            break;
                        case 5:

                            Menu.niveldeDificultad = 'C';
                            JuegoAdivinarNumero.juegodeAdivinar(Menu.niveldeDificultad);
                            break;
                        case 6:

                            Menu.niveldeDificultad = 'C';
                            JuegoAdivinarAlimento.adivinarAlimento(Menu.niveldeDificultad);
                            break;
                        case 7:

                            Menu.niveldeDificultad = 'C';
                            JuegoAhorcado.juegodeAhorcado();
                            break;

                    }

            }
        } while (true);
    }
}

