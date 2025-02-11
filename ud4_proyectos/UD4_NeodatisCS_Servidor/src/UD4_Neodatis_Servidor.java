/* 	Crea un servidor Neodatis en el puerto 8000
* 	asignando el nombre "base1" al fichero de la BBDD
* 	Las aplicaciones clientes tendr�n que usar ese nombre y puerto
* 	para despu�s conectarse a la BBDD Neodatis.
*   Para que se pueda iniciar el servidor, el fichero de la BBDD tendr�
*   que existir en la ubicaci�n especificada y el puerto tendr� que estar libre.
*/

import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBServer;

public class UD4_Neodatis_Servidor {
	
	public static void main(String[] args) {
		
		ODBServer server = null;
		// Crea el servidor en el puerto 8000
		server = ODBFactory.openServer(8000);
		// Abrir BD
		server.addBase("base1", "D:/BBDD/neodatisServer.test"); // atender peticiones que lleguen con este nombre
		// Se inicia el servidor ejecut�ndose en segundo plano
		server.startServer(true);
		
		System.out.println("Servidor iniciado....");
	}

}
