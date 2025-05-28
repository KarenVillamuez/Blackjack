
public class Jugador {
     private final String nombre;
    private java.util.List<Carta> mano;
    private int puntaje;
    private boolean plantado;
    private EstrategiaDealer estrategia;

    /**
     * Constructor del jugador
     * 
     * @param nombre Nombre del jugador
     */
    public Jugador(String nombre) {
        this(nombre, null);
    }

    /**
     * Constructor del jugador con estrategia
     * 
     * @param nombre     Nombre del jugador
     * @param estrategia Estrategia a usar (solo para el dealer)
     */
    public Jugador(String nombre, EstrategiaDealer estrategia) {
        this.nombre = nombre;
        this.mano = new java.util.ArrayList<>();
        this.puntaje = 0;
        this.plantado = false;
        this.estrategia = estrategia;
    }

    /**
     * Agrega una carta a la mano del jugador
     * 
     * @param carta La carta a agregar
     */
    public void recibirCarta(Carta carta) {
        mano.add(carta);
        calcularPuntaje();
    }

    /**
     * Calcula el puntaje actual de la mano del jugador
     */
    private void calcularPuntaje() {
        puntaje = 0;
        int numAses = 0;

        // Primero sumar todas las cartas que no son Ases
        for (Carta carta : mano) {
            if (carta.getValor().equals("A")) {
                numAses++;
            } else {
                puntaje += carta.getValorNumerico();
            }
        }

        // Agregar los Ases al final, decidiendo si valen 1 u 11
        for (int i = 0; i < numAses; i++) {
            if (puntaje + 11 <= 21) {
                puntaje += 11;
            } else {
                puntaje += 1;
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public java.util.List<Carta> getMano() {
        return mano;
    }

    public void plantarse() {
        this.plantado = true;
    }

    public boolean estaPlatado() {
        return plantado;
    }

    /**
     * Verifica si el jugador se ha pasado de 21
     * 
     * @return true si el jugador se ha pasado de 21
     */
    public boolean sePaso() {
        return puntaje > 21;
    }

    /**
     * Muestra las cartas del jugador
     */
    public void mostrarMano() {
        System.out.println(nombre + " tiene las siguientes cartas:");
        for (Carta carta : mano) {
            System.out.print(carta + " ");
        }
        System.out.println("\nPuntaje total: " + puntaje);
    }

    /**
     * Decide si debe pedir carta según la estrategia
     * 
     * @param cartaVisibleJugador Carta visible del jugador
     * @return true si debe pedir carta según la estrategia
     */
    public boolean debePedirCarta(Carta cartaVisibleJugador) {
        return estrategia != null && estrategia.debePedirCarta(puntaje, cartaVisibleJugador);
    }

    /**
     * Obtiene la estrategia actual del dealer
     * 
     * @return la estrategia actual o null si no es el dealer
     */
    public EstrategiaDealer getEstrategia() {
        return estrategia;
    }
    
}
