package peval2acda2223;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 
 * @author Javier Tienda González
 * @date 24/10/2022
 * @version 1.0
 * 
 */

public class AppMain {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		DbConnection db = new DbConnection();
		byte option = 0;
		String city = "", player = "", query = "";
		
		while(option != 5) {
			GMethods.println("Menu de eleccion:\n 1: Actualizar tabla jugadores\n 2: Insertar un partido\n "
						+ "3: Mostrar informacion jugadores por ciudad \n 4: Número de partidos jugados por un jugador en cada temporada "
						+ "\n 5: Actualizar la posición PIVOT por PIVOTE" + "\n 6: Eliminar equipo" + "\n 7: Salir");
			GMethods.printDiv();
			try {
				option = GMethods.keyBByte("Introduce la opcion");
				if(option < 1 || option > 7) {
					GMethods.printError("Elige una opcion valida");
				}
				GMethods.printDiv();
				switch(option) {
					case 1:
						break;
					case 2:
						break;
					case 3:
						//TODO hacer bucle para preguntar
						try {
						city = GMethods.keyBString("Introduce la ciudad que quieres ver:");
						query = "SELECT j.nombre, j.altura, j.peso, j.posicion, j.Nombre_equipo "
								+ "FROM jugadores j, equipos e WHERE e.Nombre = j.Nombre_equipo and e.Ciudad like '" + city + "'";
						}
						catch(Exception e) {
							GMethods.printError("Error al introducir la ciudad");
						}
						//TODO revisar el metodo
						db.select(query);
						GMethods.printDiv();
						break;
					case 4:
						//TODO hacer bucle para preguntar
						try {
						player = GMethods.keyBString("Introduce el jugador que quieres ver:");
						
						//Como local
						query = "SELECT count(p.codigo), p.temporada"
								+ "    FROM equipos e, partidos p"
								+ "    WHERE e.Nombre = p.equipo_visitante"
								+ "    and p.equipo_local like("
								+ "        SELECT e.Nombre"
								+ "        from equipos e, jugadores j"
								+ "        where e.Nombre = j.Nombre_equipo"
								+ "        and j.Nombre LIKE '" + player + "')"
								+ "    GROUP BY p.temporada;";
						}
						catch(Exception e) {
							GMethods.printError("Error al introducir el jugador");
						}
						//TODO revisar el metodo
						GMethods.println("Partidos como local");
						db.select(query);
						
						//Como visitante
						query = "SELECT count(p.codigo), p.temporada"
								+ "    FROM equipos e, partidos p"
								+ "    WHERE e.Nombre = p.equipo_visitante"
								+ "    and p.equipo_visitante like("
								+ "        SELECT e.Nombre"
								+ "        from equipos e, jugadores j"
								+ "        where e.Nombre = j.Nombre_equipo"
								+ "        and j.Nombre LIKE '" + player + "')"
								+ "    GROUP BY p.temporada;";
						//TODO revisar el metodo
						GMethods.println("Partidos como visitante");
						db.select(query);
						break;
					case 5:
						break;
					case 6:
						break;
					case 7:
						System.exit(0);
						break;
				}
			}
			//Catch all the possible exceptions
			catch(Exception e) {
				GMethods.printError("Error durante la ejecucion del programa");
				e.printStackTrace();
			}
		}

	}

}
