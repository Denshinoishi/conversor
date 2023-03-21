package application;

import java.util.HashMap;

public class Pesos {

	private String gr = "Gramos";
	private String oz = "Onzas";
	private String lb = "Libras";
	private String qt = "Quintales";
	private String ar = "Arrobas";
	private String tn = "Toneladas";
	//Generamos las colecciones de las unidades de medida de peso
	private HashMap<String, Double> grm = new HashMap<String, Double>();// HashMap para Gramos
	private HashMap<String, Double> onz = new HashMap<String, Double>();// HashMap para Gramos
	private HashMap<String, Double> lbl = new HashMap<String, Double>();// HashMap para Gramos
	private HashMap<String, Double> qnt = new HashMap<String, Double>();// HashMap para Gramos
	private HashMap<String, Double> arr = new HashMap<String, Double>();// HashMap para Gramos
	private HashMap<String, Double> ton = new HashMap<String, Double>();// HashMap para Gramos
	// Generamos la colección de monedas.
	private HashMap<String, HashMap<String, Double>> pesos = new HashMap<String, HashMap<String, Double>>();
	
	
	public Pesos() {
		
		pesos.put(gr, grm);		//Colección para Gramos
		pesos.put(oz, onz);		//Colección para Gramos
		pesos.put(lb, lbl);		//Colección para Gramos
		pesos.put(qt, qnt);		//Colección para Gramos
		pesos.put(ar, arr);		//Colección para Gramos
		pesos.put(tn, ton);		//Colección para Gramos
		
		
		onz.put(gr, 28.3495);	 //Equivalencia de 1 Onza a Gramos
		onz.put(lb, 0.0625); 	 //Equivalencia de 1 Onza a Libras
		onz.put(qt, 0.000625);   //Equivalencia de 1 Onza a Quintales
		onz.put(tn, 0.00003125); //Equivalencia de 1 Onza a Toneladas
		onz.put(ar, 0.0025);     //Equivalencia de 1 Onza a Arrobas
		onz.put(oz, 1.0);	
		
		
		lbl.put(gr, 453.592);  	//Equivalencia de 1 Libra a Gramos
		lbl.put(oz, 16.0); 		//Equivalencia de 1 Libra a Onzas
		lbl.put(qt, 0.01); 		//Equivalencia de 1 Libra a Quintales
		lbl.put(tn, 0.0005);	//Equivalencia de 1 Libra a Toneladas
		lbl.put(ar, 0.04);		//Equivalencia de 1 Libra a Arrobas
		lbl.put(lb, 1.0);
		
		qnt.put(gr, 50802345.0);	//Equivalencia de 1 Quintal a Onzas
		qnt.put(oz, 1792.0);		//Equivalencia de 1 Quintal a Libras
		qnt.put(lb, 100.0);		 	//Equivalencia de 1 Quintal a Quintales
		qnt.put(tn, 0.05); 			//Equivalencia de 1 Quintal a Toneladas
		qnt.put(ar, 4.0); 			//Equivalencia de 1 Quintal a Onzas
		qnt.put(qt, 1.0);
		
		arr.put(gr, 11502.4576);  //Equivalencia de 1 Arroba a Onzas
		arr.put(oz, 407.746); //Equivalencia de 1 Arroba a Libras
		arr.put(lb, 25.0); //Equivalencia de 1 Arroba a Quintales
		arr.put(qt, 0.25); //Equivalencia de 1 Arroba a Toneladas
		arr.put(tn, 0.0125); //Equivalencia de 1 Arroba a Onzas
		arr.put(ar, 1.0);
		
		grm.put(oz, 0.0352739619);  //Equivalencia de 1 Gramo a Onzas
		grm.put(lb, 0.00220462262); //Equivalencia de 1 Gramo a Libras
		grm.put(qt, 0.00001968413); //Equivalencia de 1 Gramo a Quintales
		grm.put(tn, 0.00000110231); //Equivalencia de 1 Gramo a Toneladas
		grm.put(oz, 0.00008695652); //Equivalencia de 1 Gramo a Onzas
		grm.put(gr, 1.0);
		
			
		
	}//Fin Constructor Pesos...
	
	public void setTasa(HashMap<String, Double> peso ,String key, double valor) {
		peso.put(key, valor);
	}
	
	public double convertir(String key1, String key2, double valor) {
		
		double resultado = this.pesos.get(key1).get(key2)*valor;			
		return resultado;
		
	}

	
}
