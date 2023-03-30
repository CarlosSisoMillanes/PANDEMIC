import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ciudades {
	public static void escribirCiudades(String[] args) {
        try {
            File archivo = new File("ciudades.txt");
            Scanner scanner = new Scanner(archivo);
            FileWriter writer = new FileWriter("ciudadesRedactadas.txt");
            File archivo2 = new File("ciudadesRedactadas.txt");
            
            if (archivo2.length() != 0) {
            	PrintWriter escritor = new PrintWriter("ciudadesRedactadas.txt");
            	escritor.print("");
            	escritor.close();
            }
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String nombreCiudad = parts[0];
                String []coordenadas = parts[2].split(",");
                int x = Integer.parseInt(coordenadas[0]);
                int y = Integer.parseInt(coordenadas[1]);
                String[] colindantes = parts[3].split(",");
                StringBuilder sb = new StringBuilder();
                sb.append("La ciudad "+nombreCiudad+ " está en las coordenadas("+x+","+y+") y sus ciudades colindantes son: "+"\n");
                for (String ciudad : colindantes) {
                    ciudad = ciudad.trim();
                    String[] coordCiudad = obtenerCoordenadasCiudad(ciudad); // Método que obtiene las coordenadas de una ciudad dada
                    double distancia = calcularDistancia(x, y, Integer.parseInt(coordCiudad[0]), Integer.parseInt(coordCiudad[1])); // Método que calcula la distancia entre dos puntos
                    sb.append(ciudad).append(", que está a una distancia de ").append(distancia).append("\n");
                }
                sb.setLength(sb.length() - 2); // Elimina la última coma y el espacio extra
                sb.append("\n\n");
                System.out.println(sb.toString());
                writer.write(sb.toString());
            }
            writer.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error.");
            e.printStackTrace();
        }
    }

	public static String[] obtenerCoordenadasCiudad(String ciudad) throws IOException {
	    File archivo = new File("ciudades.txt");
	    Scanner teclado = new Scanner(archivo);
	    while (teclado.hasNextLine()) {
	        String line = teclado.nextLine();
	        String[] parts = line.split(";");
	        if (parts[0].equals(ciudad)) {
	            return parts[2].split(",");
	        }
	    }
	    return null;
	}
	
	public static double calcularDistancia(int x1, int y1, int x2, int y2) {
        double distancia = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distancia;
    }
}