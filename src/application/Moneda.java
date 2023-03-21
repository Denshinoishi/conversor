package application;

import java.util.HashMap;

public class Moneda {
	//Definimos las llaves como Strings
	private String cp = "COP";
	private String ud = "USD";
	private String jy = "JPY";
	private String kw = "KRW";
	private String er = "EUR";
	private String gp = "GBP";
	
	//Generamos las colecciones de cada moneda
	private HashMap<String, Double> cop = new HashMap<String, Double>(); //HashMap de Pesos Colombianos
	private HashMap<String, Double> usd = new HashMap<String, Double>(); //HashMap de Dolares Estadounidenses
	private HashMap<String, Double> jpy = new HashMap<String, Double>(); //HashMap de Yenes Japoneses
	private HashMap<String, Double> krw = new HashMap<String, Double>(); //HashMap de Won Surcoreano
	private HashMap<String, Double> eur = new HashMap<String, Double>(); //HashMap de Euros
	private HashMap<String, Double> gbp = new HashMap<String, Double>(); //HashMap de Libra Esterlina
	//Generamos la colección de monedas.
	private HashMap<String, HashMap<String, Double>> monedas = new HashMap<String, HashMap<String, Double>>(); //HashMap de Libra Esterlina
	
	
	
	public Moneda() {
		
		monedas.put(cp, cop);		//Colección para COP
		monedas.put(ud, usd);		//Colección para USD
		monedas.put(jy, jpy);		//Colección para JPY
		monedas.put(kw, krw);		//Colección para KRW
		monedas.put(er, eur);		//Colección para EUR
		monedas.put(gp, gbp);		//Colección para GBP
		
		
		
		
		
		
		cop.put(cp, 1.0);			//Valor de 1 COP a COP
		cop.put(ud , 0.00021);		//Valor de 1 COP a USD
		cop.put(jy, 0.027 );		//Valor de 1 COP a JPY
		cop.put(gp, 0.00017);		//Valor de 1 COP a GBP
		cop.put(kw, 0.27);			//Valor de 1 COP a KRW
		cop.put(er, 0.00019);		//Valor de 1 COP a EUR
		
		usd.put(cp, 4845.5);		//Valor de 1 USD a COP
		usd.put(ud , 1.0);			//Valor de 1 USD a USD
		usd.put(jy, 131.79 );		//Valor de 1 USD a JPY
		usd.put(gp, 0.82);			//Valor de 1 USD a GBP
		usd.put(kw, 1308.37);		//Valor de 1 USD a KRW
		usd.put(er, 0.94);			//Valor de 1 USD a EUR
		
		jpy.put(cp, 36.75);			//Valor de 1 JPY a COP
		jpy.put(ud , 0.075);		//Valor de 1 JPY a USD
		jpy.put(jy, 1.0 );			//Valor de 1 JPY a JPY
		jpy.put(gp, 0.0062);		//Valor de 1 JPY a GBP
		jpy.put(kw, 9.92);			//Valor de 1 JPY a KRW
		jpy.put(er, 0.71);			//Valor de 1 JPY a EUR
		
		krw.put(cp, 3.7);			//Valor de 1 KRW a COP
		krw.put(ud , 0.0076);		//Valor de 1 KRW a USD
		krw.put(jy, 0.1 );			//Valor de 1 KRW a JPY
		krw.put(gp, 0.0063);		//Valor de 1 KRW a GBP
		krw.put(kw, 1.0);			//Valor de 1 KRW a KRW
		krw.put(er, 0.00071);		//Valor de 1 KRW a EUR
		
		eur.put(cp, 5168.0);		//Valor de 1 EUR a COP
		eur.put(ud , 1.07);			//Valor de 1 EUR a USD
		eur.put(jy, 140.57);		//Valor de 1 EUR a JPY
		eur.put(gp, 0.88);			//Valor de 1 EUR a GBP
		eur.put(kw, 1395.51);		//Valor de 1 EUR a KRW
		eur.put(er, 1.0);			//Valor de 1 EUR a EUR
		

		gbp.put(cp, 5899.0);		//Valor de 1 EUR a COP
		gbp.put(ud , 1.22);			//Valor de 1 EUR a USD
		gbp.put(jy, 160.45);		//Valor de 1 EUR a JPY
		gbp.put(gp, 1.0);			//Valor de 1 EUR a GBP
		gbp.put(kw, 1592.94);		//Valor de 1 EUR a KRW
		gbp.put(er, 1.14);			//Valor de 1 EUR a EUR
		
		
	}
	
	public void setTasa(HashMap<String, Double> moneda ,String key, double valor) {
		moneda.put(key, valor);
	}
	
	public double convertir(String key1, String key2, double valor) {
		
		double resultado = this.monedas.get(key1).get(key2)*valor;			
		return resultado;
		
	}

}
