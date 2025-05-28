
public class Nodo {
 private Carta carta;
    private Nodo siguiente;

    public Nodo(Carta carta) {
        this.carta = carta;
        this.siguiente = null;
    }

    public Carta getCarta() {
        return carta;
    }

    public void setCarta(Carta carta) {
        this.carta = carta;
    }
    public Nodo getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

   

}
