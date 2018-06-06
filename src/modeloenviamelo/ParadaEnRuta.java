package modeloenviamelo;

import java.time.LocalTime;

public class ParadaEnRuta {
    private LocalTime horaLlegada;
    private float pesoParada;
    private Parada parada;

    ParadaEnRuta(Parada parada) {
        this.parada = parada;
    }

    void setParada(Parada parada) {
        this.parada = parada;
    }
    
    Parada getParada() {
        return this.parada;
    }
    
    void setHoraLlegada(LocalTime hora) {
        this.horaLlegada = hora;
    }   
}
