package empresa;

import java.awt.BufferCapabilities;
import java.io.*;

public class GestionDepartamentos {
	private BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
	private File fdepart=new File(".\\files\\departamentos.txt");
	private File ftemporal=new File(".\\files\\temporal.txt");
	public void menu() throws IOException{
		int opcion=0;
		do {
			 System.out.println("1. añadir departamento");
			 System.out.println("2. listar departamento");
			 System.out.println("3. modificar departamento");
			 System.out.println("4. borrar departamento");
			 System.out.println("5. Mostrar total salario de un departamento.");
			 System.out.println("6. Mostrar total salarios de los departamentos.");
			 System.out.println("0. salir");
			 try{
				 System.out.println("seleccione una opcion");
				 opcion=Integer.parseInt(teclado.readLine());
			 }catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero de 0 a 6");
				opcion=-1;
			 }
			 
			 switch (opcion) {
			case 1:
				addDepartamento();
				break;
			case 2:
				listarDepartamento();
				break;

			case 3:
				setDepartamento();
				break;

			case 4:
				borrarDepartamento();
				break;
			case 5:
				mostrarSalarioDepartamento();
				break;
			case 6:
				mostrarSalarios();
				break;
			case 0:
				System.out.println("has salido del menu");
				break;
			default:
				System.out.println("debe introducir un numero entre 0 y 4");
				break;
			}
		} while (opcion!=0);	

	}
	private boolean existeDepartamento(int dptonum) throws IOException{
		try {
			Departamento d=new Departamento();
			BufferedReader br=new BufferedReader(new FileReader(fdepart));
			String linea=br.readLine();
			while(linea!=null){
				d.descomponerLinea(linea);
				if(dptonum==d.getDptonum()){
					br.close();
					return true;
				}
				linea=br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return false;
	}
	private void addDepartamento() throws IOException {
		boolean seguir=true;
		int dptonum=-1;
		do{
			try{
				System.out.println("Introduzca el numero de departamento");
				dptonum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero");
			}
		}while(seguir);
		
		//buscar departamento
		if(existeDepartamento(dptonum)){
			System.out.println("Ya existe un departamento con nº "+dptonum+ " o no existen departamentos");
			return;
		}
		
		Departamento d=new Departamento();
		d.pedirDepartamento(dptonum);
		BufferedReader br;//ahorro escribir
		try{
			br=new BufferedReader(new FileReader(fdepart));//lo abro pero si no existe..
		}catch (FileNotFoundException e) {//sale este fallo
			BufferedWriter brw=new BufferedWriter(new FileWriter(fdepart));//lo creamos con writer y lo cerramos a continuacion
			brw.close();//cerramos
			br=new BufferedReader(new FileReader(fdepart));//lo volvemos a abrir ahora que se a crweado en modo lectura para trabajar con el
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(ftemporal));
		
		String linea=br.readLine();
		while(linea!=null){
			bw.write(linea);
			bw.newLine();
			linea=br.readLine();
		}
		
		//Montamos la linea
		linea=d.crearLinea();
		bw.write(linea);
		bw.flush();
		
		br.close();
		bw.close();
		
		fdepart.delete();
		ftemporal.renameTo(fdepart);
	}

	private void listarDepartamento() throws IOException {
		
		try {
			Departamento d=new Departamento();
			BufferedReader br=new BufferedReader(new FileReader(fdepart));
			String linea=br.readLine();
			while(linea!=null){
				d.descomponerLinea(linea);
				System.out.println(d);
				linea=br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("No existen departamentos");
		}
		
	}

	private void setDepartamento() {
		// TODO Apéndice de método generado automáticamente
		
	}

	private void borrarDepartamento() {
		// TODO Apéndice de método generado automáticamente
		
	}

	private void mostrarSalarioDepartamento() {
		// TODO Apéndice de método generado automáticamente
		
	}

	private void mostrarSalarios() {
		// TODO Apéndice de método generado automáticamente
		
	}
}
