package modeloenviamelo;

import java.time.LocalTime;
import java.util.ArrayList;

public class Parada {
    private LocalTime horaPrevistaLlegada;
    private Almacen almacen;

    Parada(LocalTime horaPrevistaLlegada, Almacen almacen) {
        this.horaPrevistaLlegada = horaPrevistaLlegada;
        this.almacen = almacen;
    }

    void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
    
    void setHoraPrevistaLlegada(LocalTime horaPrevistaLlegada) {
        this.horaPrevistaLlegada = horaPrevistaLlegada;
    }
    
    LocalTime getHoraPrevistaLlegada() {
        return horaPrevistaLlegada;
    }

    Almacen getAlmacen() {
        return almacen;
    }

    ArrayList<String> getDatos() {
        ArrayList<String> datos = new ArrayList();
        datos.add(this.horaPrevistaLlegada.toString());
        datos.add(this.almacen.getDistrito());
        datos.add(this.almacen.getDireccion());
        return datos;        
    }

    String getDistrito() { 
        return almacen.getDistrito();
    }
    
}
