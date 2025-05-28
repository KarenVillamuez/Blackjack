
public class Cola {
 private Nodo frente;
    private Nodo finalCola;

    /**
     * Constructor de la cola
     */
    public Cola() {
        this.frente = null;
        this.finalCola = null;
    }

  
    public void agregarTurno(String jugador) {
        Carta cartaTemp = new Carta(jugador, ""); // Usamos Carta temporalmente para mantener compatibilidad
        Nodo nuevoNodo = new Nodo(cartaTemp);

        if (finalCola != null) {
            finalCola.setSiguiente(nuevoNodo);
        } else {
            frente = nuevoNodo;
        }
        finalCola = nuevoNodo;
    }

    /**
     * Obtiene el siguiente turno de la cola
     * 
     * @return El nombre del siguiente jugador o un mensaje si no hay turnos
     */
    public String obtenerSiguienteTurno() {
        if (frente == null) {
            return "No hay turnos pendientes";
        }

        String jugador = frente.getCarta().getValor(); // Obtenemos el nombre del jugador
        frente = frente.getSiguiente();

        if (frente == null) {
            finalCola = null;
        }

        return jugador;
    }

    /**
     * Verifica si la cola está vacía
     * 
     * @return true si no hay turnos pendientes
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Muestra los turnos pendientes
     */
    public void mostrarTurnos() {
        if (estaVacia()) {
            System.out.println("No hay turnos pendientes");
            return;
        }

        System.out.println("Turnos pendientes:");
        Nodo actual = frente;
        while (actual != null) {
            System.out.print(actual.getCarta().getValor() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("fin");
    }
    

    

}
