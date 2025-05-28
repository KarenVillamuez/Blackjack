

    public class Baraja {
    private Nodo cabeza;

    /**
     * Inicializa la baraja con las 52 cartas
     */
    public void inicializarBaraja() {
        String[] palos = {"♠", "♥", "♦", "♣"};
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String palo : palos) {
            for (String valor : valores) {
                agregarCarta(new Carta(valor, palo));
            }
        }
        barajar();
    }

    /**
     * Agrega una carta a la baraja
     * @param carta La carta a agregar
     */
    public void agregarCarta(Carta carta) {
        Nodo nuevaCarta = new Nodo(carta);
        nuevaCarta.setSiguiente(cabeza);
        cabeza = nuevaCarta;
    }

    /**
     * Roba una carta de la baraja
     * @return La carta robada o null si la baraja está vacía
     */
    public Carta robarCarta() {
        if (cabeza == null) {
            return null;
        }

        Carta cartaRobada = cabeza.getCarta();
        cabeza = cabeza.getSiguiente();
        return cartaRobada;
    }

    /**
     * Baraja las cartas de forma aleatoria
     */
    public void barajar() {
        if (cabeza == null || cabeza.getSiguiente() == null) {
            return;
        }

        java.util.List<Carta> cartas = new java.util.ArrayList<>();
        while (cabeza != null) {
            cartas.add(robarCarta());
        }

        java.util.Collections.shuffle(cartas);

        for (Carta carta : cartas) {
            agregarCarta(carta);
        }
    }

    /**
     * Imprime las cartas restantes en la baraja
     */
    public void imprimirBaraja() {
        Nodo actual = cabeza;
        System.out.println("Cartas restantes en la baraja:");
        while (actual != null) {
            System.out.print(actual.getCarta() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    public static int valorCarta(String carta) {
        String valor = carta.substring(0, carta.length() - 1); // elimina el palo
        switch (valor) {
            case "A":
                return 11; // puedes ajustar luego con lógica de As = 1 o 11
            case "K":
            case "Q":
            case "J":
                return 10;
            default:
                return Integer.parseInt(valor);
        }
    }

    public static int calcularPuntaje(String[] cartas) {
        int suma = 0;
        for (String carta : cartas) {
            suma += valorCarta(carta);
        }
        return suma;

    }
    }
    
    
    

