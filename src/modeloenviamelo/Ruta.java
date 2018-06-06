
package modeloenviamelo;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.ArrayList;

public class Ruta {
    private LocalTime horaInicio;
    private Calendar fechaRealizacion;
    private Furgoneta furgoneta;
    private ArrayList<ParadaEnRuta> paradasEnRuta;
    private Parada paradaActual;

    Ruta(Calendar fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
        this.paradasEnRuta = new ArrayList();
        this.paradaActual = null;
    }
    
    private ParadaEnRuta getParadaEnRuta(Parada paradaActual) {
        for(ParadaEnRuta paradaEnRuta : this.paradasEnRuta){
            if(paradaEnRuta.getParada().equals(this.paradaActual))
                return paradaEnRuta;
        }
        return null;
    }

    private Parada siguienteParada(){
        boolean siguiente = false;
        if(paradaActual.equals(null))
            return this.paradasEnRuta.get(0).getParada();
        else{
            for(ParadaEnRuta paradaEnRuta : this.paradasEnRuta){
                if(siguiente)
                    return paradaEnRuta.getParada();
                if(paradaEnRuta.getParada().equals(this.paradaActual))
                    siguiente = true;
                }
            }
    
        return null;
    }
    
    void setFechaRealizacion(Calendar fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    void setFurgoneta(Furgoneta furgoneta) {
        this.furgoneta = furgoneta;
        furgoneta.setDisponibilidad(false);
    }
    
    private void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    private void setParadaActual(Parada parada) {
        this.paradaActual = parada;
    }
    
    Calendar getFecha(){ return this.fechaRealizacion;}

    void addParada(Parada parada) {
        ParadaEnRuta paradaEnRuta = new ParadaEnRuta(parada);
        this.paradasEnRuta.add(paradaEnRuta);    
    }

    void registrarConclusion() {
        Almacen almacen = paradaActual.getAlmacen();
        furgoneta.setLocalizacion(almacen);
        furgoneta.setDisponibilidad(true);
    }

    void registrarInicio() {
        Parada primeraParada = this.siguienteParada();
        setParadaActual(primeraParada);
        setHoraInicio(LocalTime.now());
    }

    ArrayList<String> registrarParadaCompleta() {
        ArrayList<String> datos;
        ParadaEnRuta paradaEnRuta = this.getParadaEnRuta(this.paradaActual);
        paradaEnRuta.setHoraLlegada(LocalTime.now());
        Parada parada = siguienteParada();
        this.setParadaActual(parada);
        datos = parada.getDatos();
        return datos;
    }

    void sinParadaActual() {}




    
    
    
}
