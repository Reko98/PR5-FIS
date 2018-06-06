//Terminar de implementar el menú con las opciones que faltan
        // Para mejorar:
        // Podési hacer vuestros propios diseños de interfaz, esta es la interfaz mínima que tenéis que entregar
        // Podeís usar la estructura de Vista/Controlador textual que os dejamos en PDOO
               

package interfaztextual;

import java.time.LocalTime;
import java.util.Scanner;
import modeloenviamelo.Enviamelo;

/**
 *
 * @author aanaya
 */
public class IUEnviamelo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Enviamelo muyRapido = Enviamelo.getInstancia();
        
        final Scanner in = new Scanner(System.in);
        int opcion = 0; 
        int numeroRuta;
               
                 
        System.out.println("\n\n********************** OPCIONES ********************\n");
        System.out.println("\t1. Conductor \n" +
                           "\t2. Oficinista \n" );
        // Lectura de un int, para darle valor a opcion.
        opcion =Integer.parseInt(in.nextLine());
        if (opcion == 1){// opciones del conductor
                      
            System.out.println(muyRapido.obtenerRutasActivas()); // Este método de obtener rutas activas no está diseñado
            numeroRuta = Integer.parseInt(in.nextLine());
                    
            int numeroParadas = muyRapido.registrarInicioRuta(numeroRuta);
            for (int i = 1; i<=numeroParadas;i++){
                System.out.println("pulsa cualquier tecla para indicar parada completada");
                in.nextLine();
                System.out.println(muyRapido.registrarParadaCompletada(numeroRuta));  
                System.out.println("Vamos a la siguiente parada");
            }                    
            System.out.println("la ruta: " + numeroRuta+ " ha finalizado , pulsa cualquier tecla para indicar fin de ruta");
            in.nextLine();
            muyRapido.registrarConclusionRuta(numeroRuta);
                   
        }else{// opciones del oficinista
            do{
                try{ // tratamiento de las excepciones. Bloque try en el que se puede producir una excepción y la capturamos
		  
                    System.out.println("===== CESTIÓN DE RUTAS===== \n" +
                                    "\t1. Nueva ruta plantilla \n" +
                                    "\t2. Generar ruta diaria \n" +
                                    "\t3. Asignar furgoneta a ruta\n");            
                  
                    System.out.println("*******************************************************");                       
                    System.out.println("\t0. TERMINAR");
                    System.out.println("*******************************************************");
                 
                // Lectura de un int, para darle valor a opcion.
                    opcion =Integer.parseInt(in.nextLine()); 
                
                // Estructura switch con todas las opciones de menú.  
                    switch(opcion){
                    case 1: 
                       System.out.print("Numero de ruta: ");
                       numeroRuta=Integer.parseInt(in.nextLine());                                       
                       System.out.print("hora inicio: ");
                       int horaInicio = Integer.parseInt(in.nextLine());
                       System.out.print("minutos: ");
                       int minutosInicio = Integer.parseInt(in.nextLine());
                       LocalTime horaPrevistaInicio = LocalTime.of(horaInicio, minutosInicio);
                       muyRapido.crearRutaPlantilla(numeroRuta,horaPrevistaInicio);
                          
  
                       // Mientras haya paradas que incluir a la ruta
                           //    Mostrar lista de almacenes
                           //    Seleccionar id almacen de la parada
                           //    incluir nueva parada a la ruta          
                       
                    break;  
                    
                    case 2:/*Generar ruta diaria de una ruta plantilla */
                       // mostar lista de números rutas platilla activas
                       // seleccionar numero de ruta platilla
                       // generar ruta diaria para la ruta plantilla seleccionadaa
                    break;
                    
                    case 3:/* Asignar Furgoneta a ruta */
                        // mostrar rutas activas  
                        // Elegir una ruta
                        // mostrar furgonetas disponible en el almacen en el que empieza la ruta
                        // Elegir una furgoneta
                        // asignar furgoneta a ruta                                                                
                    break;                   
                 

                    case 0: /* terminar */
                        break;                        
                                    
                        default:
                             System.out.println("OPCION NO VÁLIDA");
                        break;
                    }
                }catch(Exception ex){ // captura de la excepción
                    System.err.println("se ha producido la siguiente excepcion: "+ ex.getMessage());
                }
            }while(opcion !=0); 
           
        }
        System.exit(0);
    }  
        
}
    

