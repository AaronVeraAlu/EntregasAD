import java.util.*;	
import java.io.*;

class ej1{

	private static RandomAccessFile raf;
	private static final int TAM = 40;
	private static int indiceFinal;
	private static String nombre;
	private static int codigo;
	private static double peso;

	public static void lista() throws IOException {

		raf.seek(0);
		while(raf.getFilePointer() < raf.length()){

			codigo = raf.readInt();
			nombre="";
			for (int i = 0; i < 14; i++){
		    	nombre += raf.readChar();	
			}
			peso = raf.readDouble();

			if (codigo != 0){
				System.out.println(codigo + " .");
				System.out.println("\tNombre: " + nombre);
				System.out.println("\tCódigo de referencia: " + codigo);
				System.out.println("\tPeso: " + peso);
				System.out.println();
			}
		}
	}

	public static void insertar(String nombre, int codRef, double peso) throws IOException {

		raf.seek((TAM)*(codRef-1)); 

		raf.writeInt(codRef);
		StringBuffer sb = new StringBuffer(nombre);
		sb.setLength(14);			
		raf.writeChars(sb.toString()); 	
		raf.writeDouble(peso); 		
											
	}


	public static void insertarFinal(String nombre, double peso) throws IOException{

		raf.seek(0);

		while(raf.getFilePointer() < raf.length()){
			if(raf.readInt() >= 90)
				indiceFinal++;
			raf.skipBytes(36);
		}

		raf.seek((90+indiceFinal)*TAM);
		raf.writeInt(90+indiceFinal);
		StringBuffer sb = new StringBuffer(nombre);
		sb.setLength(14);	
		raf.writeChars(sb.toString()); 	
		raf.writeDouble(peso); 	

	}

	public static void buscar(int codRef) throws IOException {

		raf.seek((codRef-1)*TAM);
		nombre="";
		
		System.out.println("\tCódigo de referencia: " + raf.readInt());
		for (int i = 0; i < 14; i++){
	    	nombre += raf.readChar();	
		}
		System.out.println("\tNombre: " + nombre);
		System.out.println("\tPeso: " + raf.readDouble());
		System.out.println();
	}

	public static boolean existe(int codRef) throws IOException {
		
		raf.seek((codRef-1)*TAM);
		return raf.readInt() != codRef;
	}

	public static void main(String[] args){


		Scanner sc = new Scanner(System.in);
		int opt=0 ;
		
		try {
			raf = new RandomAccessFile("atropodos.dat", "rw");
			raf.setLength(4000);

		} catch(Exception e){
			e.printStackTrace();
		} 

		while(opt!=4){

			System.out.print("----- ARTROPODOS ------ \n\t1.NUEVO ARTROPODO \n\t2.BUSCAR ARTROPODO \n\t3.LISTA DE ARTROPODOS \n\t4.SALIR\n");
			
			opt = sc.nextInt();
			sc.nextLine();

			if(opt>0 && opt<5){
				switch (opt) {

					case 1:
						
						System.out.print("Introduzca un nuevo artropdo.\n\tCódigo de referencia: ");
						codigo = sc.nextInt();
						sc.nextLine();
						System.out.print("\tNombre:");
						nombre = sc.nextLine();
						System.out.print("\tPeso: ");
						peso = sc.nextDouble();

						try{

							if(existe(codigo))
								insertar(nombre,codigo,peso);
							else 
								insertarFinal(nombre, peso);
						}
						catch (IOException e) {e.printStackTrace();}

					break;
					case 2:
					
						System.out.println("Introduzca el código de artropdo.\n\tCódigo de referencia: ");
						codigo = sc.nextInt();
						sc.nextLine();
					
						try{buscar(codigo);}
						catch(IOException e){e.printStackTrace();}
						
					break;
					case 3:

						System.out.println("\t LISTA DE ARTROPODOS");
						try{lista();}
						catch(IOException e){e.printStackTrace();}

					break;
					case 4:
						System.out.println("\tCerrando...");
					break;
				}
			} else{System.out.println("Introduzca una opción valida.");}
		}
	}
}