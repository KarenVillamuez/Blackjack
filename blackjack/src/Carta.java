
public class Carta {
      private final String valor;
    private final String palo;

    /**
     * Constructor de la carta
     * 
     * @param valor Valor de la carta (A, 2-10, J, Q, K)
     * @param palo  Palo de la carta (♠, ♥, ♦, ♣)
     */
    public Carta(String valor, String palo) {
        this.valor = valor;
        this.palo = palo;
    }

    /**
     * Obtiene el valor numérico de la carta para el juego de Blackjack
     * 
     * @return valor numérico de la carta
     */
    public int getValorNumerico() {
        switch (valor) {
            case "A":
                return 11; // El As puede valer 1 u 11
            case "K":
            case "Q":
            case "J":
                return 10;
            default:
                return Integer.parseInt(valor);
        }
    }

    @Override
    public String toString() {
        return valor + palo;
    }

    public String getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }
    
}
