
public interface EstrategiaDealer {
     /**
     * Decide si el dealer debe pedir otra carta
     * 
     * @param puntajeActual       Puntaje actual del dealer
     * @param cartaVisibleJugador Primera carta visible del jugador
     * @return true si debe pedir carta, false si debe plantarse
     */
    boolean debePedirCarta(int puntajeActual, Carta cartaVisibleJugador);

    /**
     * Obtiene el nombre de la estrategia
     * 
     * @return nombre de la estrategia
     */
    String getNombre();
    
}
