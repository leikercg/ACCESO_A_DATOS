

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.ODBServer;

public class UD4_Neodatis_ServidoryCliente {	
	 
  public static void main(String[] args) {
	 ODB odb = null;
     ODBServer server = null;
     try {
      // Crea el servidor en el Puerto 80
      server = ODBFactory.openServer(8000);
      // BD a usar y el nombre que se usará para refererirse a ella
      server.addBase("base", "D:/UNI4/Server/neodatisServer.test");
      // Se ejecuta el servidor 
      server.startServer(true);	
	 
      // Se abre la BD 
      odb = server.openClient("base");

      // Se llama al método que visualize los datos 
	VisualizaDatos(odb);
	 	 
	} finally {
        if (odb != null) {
          // Primero se cierra el cliente
          odb.close();        
        }
        if (server != null) {
          // Y luego el servidor
          server.close();
        }
	}	
  }// fin main
//  
  static void VisualizaDatos(ODB odb){
      //recuperamos todos los objetos
      Objects<Jugadores> objects = odb.getObjects(Jugadores.class);
      System.out.printf("%d Jugadores: %n", objects.size());
        
      int i = 1;
      // visualizar los objetos		
      while(objects.hasNext()){
	   Jugadores jug = objects.next();
       System.out.printf("%2d.  %s, %s, %s, %d %n",
        		 (i++), jug.getNombre(), jug.getDeporte(),
        		 jug.getCiudad(), jug.getEdad());
      }  
   }
}// fin clase
