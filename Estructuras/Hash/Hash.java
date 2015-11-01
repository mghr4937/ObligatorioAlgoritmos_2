package Estructuras.Hash;

import java.util.Arrays;

public class Hash {

	public static void main(String[] args) {
		Hash nuevoHash = new Hash(30);
		String[] elementos ={"20","33","21","10","12","14","56","100"};
		nuevoHash.funcionHash(elementos, nuevoHash.arreglo);
		nuevoHash.mostrarHashTable();
		
		String buscado = nuevoHash.buscar("33");
		if(buscado == null){
			System.out.println("Elemento no encontrado");
		}
	}
	
	private int tamanio;
	private String[] arreglo;
	private int contador;
	
	public Hash(int tam){
		tamanio = tam;
		arreglo = new String [tamanio];
		Arrays.fill(arreglo, "-1");
	}
	
	private void funcionHash(String[] cadenaArreglo, String[]arreglo){
		int i;
		for(i = 0; i < cadenaArreglo.length; i++){
			String elemento = cadenaArreglo[i];
			int indiceArreglo = Integer.parseInt(elemento) % 7;
			System.out.println("El indice es " + indiceArreglo + ". Para el elemento valor " + elemento);
			
			//Manejo colisiones
			while(arreglo[indiceArreglo] != "-1"){
				indiceArreglo++;
				System.out.println("Ocurrio una colision en el indice " + (indiceArreglo - 1) + ". Cambiar al indice " + indiceArreglo);
				indiceArreglo %= tamanio;
			}
			
			arreglo[indiceArreglo] = elemento;
		}
	}
	
	public void mostrarHashTable(){
		int i, j, incremento = 0;
		
		for(i= 0; i < 1; i++){
			incremento += 8;
			for(j = 0; j < 71; j++){
				System.out.print("-");
			}
			System.out.println();
			for(j = incremento -8; j <incremento; j++){
				System.out.format("| %3s " + " " , j);
			}
			System.out.println("|");
			for(int n = 0; n < 71; n++){
				System.out.print("-");
			}
			System.out.println();
			
			for(j = incremento-8; j < incremento; j++){
				if(arreglo[j].equals("-1")){
					System.out.print("|     ");
				}else{
					System.out.print(String.format("| %3s " + " ", arreglo[j]));
				}
			}
			System.out.println("|");
			
			for(j = 0; j < 71; j++){
				System.out.print("-");
			}
			System.out.println();
		}
	}
	
	public String buscar(String elemento){
		int indiceArreglo = Integer.parseInt(elemento) % 7;
		int contador = 0;
		
		while(arreglo[indiceArreglo] != "-1"){
			if(arreglo[indiceArreglo] == elemento){
			System.out.println("El elemento fue encontrado en el indice " + indiceArreglo);
			return arreglo[indiceArreglo];
			}
			indiceArreglo++;
			contador++;
			indiceArreglo%= tamanio;
			if(contador > 7){
				break;
			}
		}
		return null;
	}
	
	public String[] getArreglo() {
		return arreglo;
	}

	public void setArreglo(String[] arreglo) {
		this.arreglo = arreglo;
	}

	public Integer getTamanio() {
		return tamanio;
	}
	
	public int getContador() {
		return contador;
	}
	
	public void setContador(int contador) {
		this.contador = contador;
	}
	
}
