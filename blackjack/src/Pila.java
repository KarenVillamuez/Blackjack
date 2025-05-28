
public class Pila {

    private Nodo cima;

    /**
     * Constructor de la pila
     */
    public Pila() {
        this.cima = null;
    }

   
    public void agregarCarta(String jugada) {
        Carta carta = new Carta(jugada.substring(0, jugada.length() - 1),
                jugada.substring(jugada.length() - 1));
        Nodo nuevaCarta = new Nodo(carta);
        nuevaCarta.setSiguiente(cima);
        cima = nuevaCarta;
    }

    /**
     * Permite ver la última carta jugada
     * 
     * @return String representando la última carta jugada
     */
    public String verUltimaCarta() {
        if (cima != null) {
            return cima.getCarta().toString();
        }
        return "No hay jugadas registradas";
    }

    /**
     * Muestra el historial completo de jugadas
     */
    public void mostrarHistorial() {
        if (cima == null) {
            System.out.println("No hay jugadas registradas");
            return;
        }

        System.out.println("Historial de jugadas (más reciente primero):");
        Nodo actual = cima;
        while (actual != null) {
            System.out.print(actual.getCarta() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }
    
}
