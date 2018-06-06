package modeloenviamelo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class Enviamelo {
    private static Enviamelo instancia = new Enviamelo();
    public static Enviamelo getInstancia(){
        return instancia;
    }
    private Enviamelo(){    
        this.inicalizar();
    }
    
    private List<Almacen> almacenes =new ArrayList();
    private List<Furgoneta> furgonetas = new ArrayList();
    private List<RutaPlantilla> rutaPlantillas = new ArrayList();
    
    private void inicalizar() {
        Almacen a1 = new Almacen("18000","el florio, Granada","958345678","Antonio");
        Almacen a2 = new Almacen("29000","el potril, Málaga","952785678", "Maria");
        Almacen a3 =new Almacen("04000","el poniente, Almería","954087654", "Mario");
        Almacen a4 = new Almacen("23000", "los olivares, Jaén", "953765432", "Antonia");
        almacenes.add(a1);  
        almacenes.add(a2);
        almacenes.add(a3);
        almacenes.add(a4);
       
        furgonetas.add(new Furgoneta("3654 FFF", 2000, a1));
        furgonetas.add(new Furgoneta("3756 CDD", 3000, a1));
        furgonetas.add(new Furgoneta("9086 KDG", 1000, a2));
        furgonetas.add(new Furgoneta("9876 JJJ", 1000, a2));
        furgonetas.add(new Furgoneta("2111 HHH", 2000, a3));
        furgonetas.add(new Furgoneta("4545 HFG", 1000, a3));
        furgonetas.add(new Furgoneta("2885 JHG", 5000, a4));
        furgonetas.add(new Furgoneta("3335 DCD", 5000, a4));
    }
    
    private RutaPlantilla buscarRutaPlantilla(int idRutaPlantilla){
        for(RutaPlantilla rutaPlantilla : this.rutaPlantillas){
            if(rutaPlantilla.getNumeroRuta() == idRutaPlantilla)
                return rutaPlantilla;
        }
        return null;
    }
    
    private boolean existeRutaPlantilla(int idRutaPlantilla){
        for(RutaPlantilla rutaPlantilla : this.rutaPlantillas){
            if(rutaPlantilla.getNumeroRuta() == idRutaPlantilla)
                return true;
        }
        return false;
    }
    
    private Almacen buscarAlmacen(String idAlmacen){
        for(Almacen almacen : this.almacenes){
            if(almacen.getDistrito().equals(idAlmacen)){
                return almacen;
            }
        }
        return null;
    }
    
    private Furgoneta buscarFurgoneta(String matricula){
        for(Furgoneta furgoneta : this.furgonetas){
            if(furgoneta.getMatricula().equals(matricula)){
                return furgoneta;
            }
        }
        return null;
    }

    private ArrayList<Furgoneta> seleccionarFurgonetasDisponibles(String distrito){
        ArrayList<Furgoneta> furgonetasDisponibles = new ArrayList();
            for(Furgoneta furgoneta : this.furgonetas){
                if(furgoneta.getDistritoAlmacen().equals(distrito) && furgoneta.getDisponibilidad()){
                    furgonetasDisponibles.add(furgoneta);
            }
        }
        return furgonetasDisponibles;
    }
    
    public void addParadaRutaPlantilla(int idRutaPlantilla, LocalTime horaPrevistaLlegada, String idAlmacen){
        RutaPlantilla rutaPlantilla = this.buscarRutaPlantilla(idRutaPlantilla);
        Almacen almacen = this.buscarAlmacen(idAlmacen);
        rutaPlantilla.addParada(horaPrevistaLlegada, almacen);
    }
    
    public void asignarRutaFurgoneta(int idRutaPlantilla, Calendar fecha, String idFurgoneta){
        Furgoneta furgoneta = this.buscarFurgoneta(idFurgoneta);
        RutaPlantilla rutaPlantilla = this.buscarRutaPlantilla(idRutaPlantilla);
    
        rutaPlantilla.asignarRutaFurgoneta(fecha, furgoneta);
    
    }
 
    public ArrayList<ArrayList<String>> obtenerFurgonetasRuta(int idRutaPlantilla){
        ArrayList<ArrayList<String>> datos = new ArrayList();
        RutaPlantilla rutaPlantilla = this.buscarRutaPlantilla(idRutaPlantilla);
        String distrito = rutaPlantilla.getDistritoPrimeraParada();
        ArrayList<Furgoneta> furgonetasDisponibles = this.seleccionarFurgonetasDisponibles(distrito);
        for(Furgoneta furgoneta : furgonetasDisponibles){
            datos.add(furgoneta.obtenerDatos());
        }
        
        return datos;
    }
    
    public int crearRutaPlantilla(int numeroRuta, LocalTime horaPrevistaInicio) {
        if(this.existeRutaPlantilla(numeroRuta))
            throw new UnsupportedOperationException("Ya existe una ruta plantilla con ese número de ruta.");
        
        RutaPlantilla rutaPlantilla = new RutaPlantilla(numeroRuta, horaPrevistaInicio);
        this.rutaPlantillas.add(rutaPlantilla);
        
        return numeroRuta;
    }
    
    public void generarRutaDiaria(int idRutaPlantilla, Calendar fecha){
        if(fecha.before(Calendar.getInstance()))
            throw new UnsupportedOperationException("La fecha debe ser igual o posterior a la fecha actual.");

        RutaPlantilla rutaPlantilla = this.buscarRutaPlantilla(idRutaPlantilla);
        rutaPlantilla.generarRutaDiaria(fecha); 
    }

    public void registrarConclusionRuta(int numeroRuta) {
        RutaPlantilla rutaPlantilla = this.buscarRutaPlantilla(numeroRuta);
        rutaPlantilla.registrarConclusionRuta();    
    }
    
    public int registrarInicioRuta(int numeroRuta) {
        RutaPlantilla rutaPlantilla = this.buscarRutaPlantilla(numeroRuta);
        rutaPlantilla.registrarInicioRuta();
        return rutaPlantilla.getNumeroParadas();
    }

    public List registrarParadaCompletada(int numeroRuta) { 
        RutaPlantilla rutaPlantilla = this.buscarRutaPlantilla(numeroRuta);
        ArrayList<String> datosSiguienteParada = rutaPlantilla.registrarParadaCompleta();


        return datosSiguienteParada;

    }

    // en este método normalmente evitaría la conversión innecesaria de Integer a int pero para respetar el tipo que devuelve el objeto 
    // he tenido que añadir la conversión al final
    public int[] obtenerRutasActivas() {
        ArrayList<Integer> numerosRutaObj = new ArrayList();    
            for(RutaPlantilla rutaPlantilla : this.rutaPlantillas){
                if(rutaPlantilla.rutaActiva())
                    numerosRutaObj.add(rutaPlantilla.getNumeroRuta());
            }
            
        int[] numerosRuta = new int[numerosRutaObj.size()];
        for (int i=0; i < numerosRuta.length; i++){
            numerosRuta[i] = numerosRutaObj.get(i).intValue();
        }
        
        return numerosRuta;
    }
}
