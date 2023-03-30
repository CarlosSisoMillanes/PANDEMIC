import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Enfermedades {
	public static void escribirEnfermedades(String[] args) {
		String ficheroCiudades = "ciudades.txt";
        String ficheroEnfermedades = "ciudad-enfermedades.bin";
        File archivo = new File("CCP.bin");
		try {
			FileInputStream file_input = new FileInputStream("CCP.bin");
			DataInputStream data_input = new DataInputStream(file_input);
			FileWriter writer1 = new FileWriter("ciudades-enfermedades.bin");
			int cont = 0;

			// Leer la explicación de la estructura del archivo
			String explicacion = data_input.readUTF();
			System.out.println(explicacion);
			System.out.println("\n");

			// Crear el archivo ciudades-enfermedades.bin
			FileOutputStream fos = new FileOutputStream("ciudades-enfermedades.bin");
			DataOutputStream dos = new DataOutputStream(fos);

			// Leer las ciudades y las enfermedades y escribirlas en el nuevo archivo
			while (data_input.available() > 0) {
				StringBuilder sb1 = new StringBuilder();
			//	while (cont < 4) {
					int cod = data_input.readInt(); 
					String ciudad = data_input.readUTF(); 
					String color = data_input.readUTF();
					int cod2 = data_input.readInt();
					String ciudad2 = data_input.readUTF();
					String color2 = data_input.readUTF();
					int cod3 = data_input.readInt();
					String ciudad3 = data_input.readUTF();
					String color3 = data_input.readUTF();
					int cod4 = data_input.readInt();
					String ciudad4 = data_input.readUTF();
					String color4 = data_input.readUTF();
					
					int coord = data_input.readInt();
					int coord2 = data_input.readInt();

					System.out.println(cod + " " + ciudad + ": " + color);
					System.out.println("\n");
					System.out.println(cod2 + " " + ciudad2 + ": " + color2);
					System.out.println("\n");
					System.out.println(cod3 + " " + ciudad3 + ": " + color3);
					System.out.println("\n");
					System.out.println(cod4 + " " + ciudad4 + ": " + color4);
					System.out.println("\n");
					System.out.println("La coordenada es " + coord + " " + coord2);
					System.out.println("\n");
					cont++;
			}
			try {
	            File archivo_ciudades = new File("ciudades.txt");
	            Scanner scanner = new Scanner(archivo_ciudades);
	            FileWriter writer = new FileWriter("ciudades-enfermedades.bin");
	            File archivo2 = new File("ciudades-enfermedades.bin");
	            
	            while (scanner.hasNextLine()) {
	            	StringBuilder sb = new StringBuilder();
	                String line = scanner.nextLine();
	                String[] parts = line.split(";");
	                String nombreCiudad = parts[0];
	                int numEnfermedades = Integer.parseInt(parts[1]);
	                String nombreEnfermedad = obtenerNombre(numEnfermedades);
	                sb.append(nombreCiudad+ ": "+ nombreEnfermedad+"\n");
	                //System.out.println(sb.toString());
	                writer.write(sb.toString());
	            }
	            writer.close();
	            scanner.close();
	        } catch (IOException e) {
	            System.out.println("Ha ocurrido un error.");
	            e.printStackTrace();
	        }
			
			data_input.close();
			file_input.close();
			dos.close();
			fos.close();

		} catch (EOFException e) {
			System.out.println("Fin del archivo");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 Nombre de la función: obtenerNombre
	 Explicación del que hace la función: Recibe un numero y dependiendo del numero devuelve un nombre u otro.
	 Parámetros de entrada: int numEnfermedades
	 Parámetros de salida: string nombreEnfermedad
	*/
	
	public static String obtenerNombre(int numEnfermedades) {
        switch(numEnfermedades) {
            case 0:
                return "Alfa";
            case 1:
                return "Beta";
            case 2:
                return "Gama";
            case 3:
                return "Delta";
            default:
                return "Fallo";
        }
    }
}
