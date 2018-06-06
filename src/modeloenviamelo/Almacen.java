package modeloenviamelo;

class Almacen {
    private String distrito;
    private String direccion;
    private String telefono;
    private String responsable;
   
    Almacen(String distrito, String direccion, String telefono, String responsable) {  
        this.distrito = distrito;
        this.direccion = direccion;
        this.telefono = telefono;
        this.responsable = responsable;
    }
    
    String getDistrito() {
        return distrito;
    };
    
    String getDireccion() {
        return direccion;
    };

    
}
