package modeloenviamelo;

import java.util.ArrayList;

class Furgoneta {
    private String matricula;
    private double pesoMaximo;
    private boolean disponibilidad;
    private Almacen almacen;
   
    Furgoneta(String matricula, double pesoMaximo, Almacen almacen) {
        this.matricula = matricula;
        this.pesoMaximo = pesoMaximo;
        this.almacen = almacen;
        this.disponibilidad = true;
    }
    
    void setDisponibilidad(boolean disponibilidad){
        this.disponibilidad = disponibilidad;
    }
    
    void setLocalizacion(Almacen almacen){
        this.almacen = almacen;
    }
    
    String getMatricula(){ return this.matricula;}
    
    String getDistritoAlmacen() { 
        return this.almacen.getDistrito();
    }
    
    boolean getDisponibilidad() { 
        return this.disponibilidad;
    }
    
    ArrayList<String> obtenerDatos(){
        ArrayList<String> datos = new ArrayList();
        
        datos.add(matricula);
        datos.add(Double.toString(this.pesoMaximo));
        
        return datos;
    }
  
    
}
