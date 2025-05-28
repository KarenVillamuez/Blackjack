
import java.util.Scanner;

public class MainBlackjack {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Baraja baraja = new Baraja();
    private static final Pila historial = new Pila();

    public static void main(String[] args) {
        System.out.println("=== Bienvenido a Blackjack ===");

        // Inicializar la baraja
        baraja.inicializarBaraja();

        // Crear jugadores
        System.out.println("Por favor, ingresa tu nombre:");
        Jugador jugadorHumano = new Jugador(scanner.nextLine());

        // Seleccionar estrategia del dealer
        System.out.println("\nSelecciona la estrategia del dealer:");
        System.out.println("1. Conservadora");
        System.out.println("2. Agresiva");
        System.out.println("3. Adaptativa");
        System.out.println("4. Aleatoria");

        EstrategiaDealer estrategiaDealer = null;
        while (estrategiaDealer == null) {
            System.out.print("\nElige una opción (1-4): ");
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    estrategiaDealer = new EstrategiasDealer.EstrategiaConservadora();
                    break;
                case "2":
                    estrategiaDealer = new EstrategiasDealer.EstrategiaAgresiva();
                    break;
                case "3":
                    estrategiaDealer = new EstrategiasDealer.EstrategiaAdaptativa();
                    break;
                case "4":
                    estrategiaDealer = new EstrategiasDealer.EstrategiaAleatoria();
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        Jugador dealer = new Jugador("Dealer", estrategiaDealer);
        System.out.println("\nEl dealer usará la estrategia: " + estrategiaDealer.getNombre());

        // Repartir cartas iniciales
        repartirCartasIniciales(jugadorHumano, dealer);

        // Turno del jugador
        turnoJugador(jugadorHumano);

        // Turno del dealer (si el jugador no se pasó)
        if (!jugadorHumano.sePaso()) {
            turnoDealer(dealer, jugadorHumano.getMano().get(0));
        }

        // Mostrar resultado final
        determinarGanador(jugadorHumano, dealer);

        scanner.close();
    }

    /**
     * Reparte las cartas iniciales a ambos jugadores
     */
    private static void repartirCartasIniciales(Jugador jugador, Jugador dealer) {
        // Dar dos cartas a cada jugador
        for (int i = 0; i < 2; i++) {
            darCarta(jugador);
            darCarta(dealer);
        }

        // Mostrar las cartas iniciales
        jugador.mostrarMano();
        System.out.println("\nCarta visible del Dealer: " + dealer.getMano().get(0));
    }

    /**
     * Maneja el turno del jugador humano
     */
    private static void turnoJugador(Jugador jugador) {
        System.out.println("\n=== Tu turno ===");

        while (!jugador.estaPlatado() && !jugador.sePaso()) {
            System.out.println("\n¿Deseas pedir otra carta? (si/no):");
            String respuesta = scanner.nextLine().toLowerCase();

            if (respuesta.equals("si")) {
                darCarta(jugador);
                jugador.mostrarMano();

                if (jugador.sePaso()) {
                    System.out.println("¡Te has pasado de 21! Has perdido.");
                }
            } else if (respuesta.equals("no")) {
                jugador.plantarse();
                System.out.println("Te has plantado con " + jugador.getPuntaje() + " puntos.");
            }
        }
    }

    /**
     * Maneja el turno del dealer
     */
    private static void turnoDealer(Jugador dealer, Carta cartaVisibleJugador) {
        System.out.println("\n=== Turno del Dealer ===");
        dealer.mostrarMano();

        System.out.println("Usando estrategia: " + dealer.getEstrategia().getNombre());

        while (!dealer.sePaso() && dealer.debePedirCarta(cartaVisibleJugador)) {
            System.out.println("\nEl Dealer pide una carta...");
            darCarta(dealer);
            dealer.mostrarMano();

            if (dealer.sePaso()) {
                System.out.println("¡El Dealer se ha pasado de 21!");
                break;
            }
        }

        if (!dealer.sePaso()) {
            dealer.plantarse();
            System.out.println("El Dealer se planta con " + dealer.getPuntaje() + " puntos.");
        }
    }

    /**
     * Da una carta a un jugador y la registra en el historial
     */
    private static void darCarta(Jugador jugador) {
        Carta carta = baraja.robarCarta();
        jugador.recibirCarta(carta);
        historial.agregarCarta(carta.toString());
    }

    /**
     * Determina y muestra el ganador de la partida
     */
    private static void determinarGanador(Jugador jugador, Jugador dealer) {
        System.out.println("\n=== Resultado Final ===");

        if (jugador.sePaso()) {
            System.out.println("Has perdido por pasarte de 21.");
        } else if (dealer.sePaso()) {
            System.out.println("¡Has ganado! El Dealer se pasó de 21.");
        } else {
            int puntajeJugador = jugador.getPuntaje();
            int puntajeDealer = dealer.getPuntaje();

            System.out.println(jugador.getNombre() + ": " + puntajeJugador + " puntos");
            System.out.println("Dealer: " + puntajeDealer + " puntos");

            if (puntajeJugador > puntajeDealer) {
                System.out.println("¡Has ganado!");
            } else if (puntajeJugador < puntajeDealer) {
                System.out.println("El Dealer gana.");
            } else {
                System.out.println("Empate.");
            }
        }

        // Mostrar historial de cartas jugadas
        System.out.println("\nÚltima carta jugada: " + historial.verUltimaCarta());

    }
}
