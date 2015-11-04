package Estructuras.Hash;

import java.util.Arrays;

import Estructuras.Grafo.MatrizAdyacencia.NodoPunto;

public class Hash {
	
	private int tamanio;
	private String[] arreglo;
	private int contador;
	int nodos;
	
	public Hash(int tam){
		tamanio = tam;
		arreglo = new String [tamanio];
		Arrays.fill(arreglo, "-1");
	}
	
	private void funcionHash(String[] cadenaArreglo, String[]arreglo){
		int i;
		for(i = 0; i < cadenaArreglo.length; i++){
			String elemento = cadenaArreglo[i];
			int indiceArreglo = Integer.parseInt(elemento) % 97;
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
			incremento += 98;
			for(j = 0; j < 71; j++){
				System.out.print("-");
			}
			System.out.println();
			for(j = incremento -98; j <incremento; j++){
				System.out.format("| %3s " + " " , j);
			}
			System.out.println("|");
			for(int n = 0; n < 71; n++){
				System.out.print("-");
			}
			System.out.println();
			
			for(j = incremento-98; j < incremento; j++){
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
	
	public String buscar(int element){
		String elemento = Integer.toString(element);
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
	
	public boolean esVacia() {
	      return nodos == 0;
	}
	
    public String puntoMapa() {
        if (this.esVacia()) {
            return "";
        } else {
            return puntoMapa(0);
        }
    }

    private String puntoMapa(int i) {
        if (i == tamanio) {
            return "";
        } else {
            //return arreglo[i].getPuntos() + puntoMapa(i + 1);
        	return null;
        }
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
