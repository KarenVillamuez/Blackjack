
public class EstrategiasDealer {
    public static class EstrategiaConservadora implements EstrategiaDealer {

        @Override
        public boolean debePedirCarta(int puntajeActual, Carta cartaVisibleJugador) {
            return puntajeActual <= 16;
        }

        @Override
        public String getNombre() {
            return "Conservadora";
        }
    }

    public static class EstrategiaAgresiva implements EstrategiaDealer {

        @Override
        public boolean debePedirCarta(int puntajeActual, Carta cartaVisibleJugador) {
            return puntajeActual <= 17 || (puntajeActual <= 18 && cartaVisibleJugador.getValorNumerico() >= 7);
        }

        @Override
        public String getNombre() {
            return "Agresiva";
        }
    }

    public static class EstrategiaAdaptativa implements EstrategiaDealer {

        @Override
        public boolean debePedirCarta(int puntajeActual, Carta cartaVisibleJugador) {
            int valorJugador = cartaVisibleJugador.getValorNumerico();

            // Si el jugador tiene una carta alta, el dealer toma más riesgos
            if (valorJugador >= 10) {
                return puntajeActual <= 17;
            } // Si el jugador tiene una carta baja, el dealer es más conservador
            else if (valorJugador <= 6) {
                return puntajeActual <= 15;
            }
            // Para cartas intermedias, comportamiento estándar
            return puntajeActual <= 16;
        }

        @Override
        public String getNombre() {
            return "Adaptativa";
        }
    }

    public static class EstrategiaAleatoria implements EstrategiaDealer {

        private final java.util.Random random = new java.util.Random();
        private final EstrategiaDealer[] estrategias;
        private EstrategiaDealer estrategiaActual;

        public EstrategiaAleatoria() {
            estrategias = new EstrategiaDealer[]{
                new EstrategiaConservadora(),
                new EstrategiaAgresiva(),
                new EstrategiaAdaptativa()
            };
            cambiarEstrategia();
        }

        private void cambiarEstrategia() {
            estrategiaActual = estrategias[random.nextInt(estrategias.length)];
        }

        @Override
        public boolean debePedirCarta(int puntajeActual, Carta cartaVisibleJugador) {
            if (random.nextInt(100) < 20) { // 20% de probabilidad de cambiar de estrategia
                cambiarEstrategia();
            }
            return estrategiaActual.debePedirCarta(puntajeActual, cartaVisibleJugador);
        }

        @Override
        public String getNombre() {
            return "Aleatoria (usando " + estrategiaActual.getNombre() + ")";
        }
    
}
}
