package modeloenviamelo;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.ArrayList;

public class RutaPlantilla {
    private int numeroRuta;
    private LocalTime horaPrevistaInicio;
    private boolean activa;
    private ArrayList<Ruta> rutas;
    private ArrayList<Parada> paradas;

    RutaPlantilla(int numeroRuta, LocalTime horaPrevistaInicio) {
        this.numeroRuta = numeroRuta;
        this.horaPrevistaInicio = horaPrevistaInicio;
        this.activa = true;
        rutas = new ArrayList();
        paradas = new ArrayList();
    }
    
    private Ruta buscarRuta(Calendar idRutaDiaria){
         for(Ruta ruta : this.rutas){
            if(ruta.getFecha()== idRutaDiaria){
                return ruta;
            }
        }
        return null;
    }
    
    void asignarRutaFurgoneta(Calendar idRutaDiaria, Furgoneta furgoneta){
        Ruta ruta = buscarRuta(idRutaDiaria);
        ruta.setFurgoneta(furgoneta);
    }
    
    void addParada(LocalTime horaPrevistaLlegada, Almacen almacen){
        Parada parada = new Parada(horaPrevistaLlegada, almacen);
        paradas.add(parada);
    }
    
    void generarRutaDiaria(Calendar fecha){
        Ruta ruta = new Ruta(fecha);
        for(Parada parada : paradas){
            ruta.addParada(parada);
        }    
        this.rutas.add(ruta);
    } 
    
    String getDistritoPrimeraParada(){
        String distrito = paradas.get(0).getDistrito();
        return distrito;
    }
    
    int getNumeroParadas(){ 
        return paradas.size();
    }
            
    int getNumeroRuta(){
        return numeroRuta;
    } 
    
    void registrarConclusionRuta(){
        Ruta ruta = buscarRuta(Calendar.getInstance());
        ruta.registrarConclusion();    
    }
    
    void registrarInicioRuta(){
        Ruta ruta = this.buscarRuta(Calendar.getInstance());
        ruta.registrarInicio();
    }
    
    ArrayList<String> registrarParadaCompleta(){
        ArrayList<String> datos;
        Ruta ruta = this.buscarRuta(Calendar.getInstance());
        datos = ruta.registrarParadaCompleta();
        
        return datos;
    }
    
    boolean rutaActiva(){
        if(this.buscarRuta(Calendar.getInstance()).equals(null))
            return false;
        else
            return true;
    }      
}
