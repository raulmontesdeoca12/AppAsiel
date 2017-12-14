/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obed77.Ventas;

public class n2t {
	private int flag;
	public long numero;
	public String importe_parcial;
	public String num;
	public String num_letra;
	public String num_letras;
	public String num_letram;
	public String num_letradm;
	public String num_letracm;
	public String num_letramm;
	public String num_letradmm;
	
	public n2t(){
		numero = 0;
		flag=0;
	}
	public n2t(long n){
		numero = n;
		flag=0;
	}

	
	private String unidad(long numero){
		
		switch ((int)numero){
		case 9:
				num = "Nueve";
				break;
		case 8:
				num = "Ocho";
				break;
		case 7:
				num = "Siete";
				break;
		case 6:
				num = "Seis";
				break;
		case 5:
				num = "Cinco";
				break;
		case 4:
				num = "Cuatro";
				break;
		case 3:
				num = "Tres";
				break;
		case 2:
				num = "Dos";
				break;
		case 1:
				if (flag == 0)
					num = "Uno";
				else 
					num = "Un";
				break;
		case 0:
				num = "";
				break;
		}
		return num;
	}
	
	private String decena(long numero){
	
		if (numero >= 90 && numero <= 99)
		{
			num_letra = "Noventa ";
			if (numero > 90)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 90));
		}
		else if (numero >= 80 && numero <= 89)
		{
			num_letra = "Ochenta ";
			if (numero > 80)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 80));
		}
		else if (numero >= 70 && numero <= 79)
		{
			num_letra = "Setenta ";
			if (numero > 70)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 70));
		}
		else if (numero >= 60 && numero <= 69)
		{
			num_letra = "Sesenta ";
			if (numero > 60)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 60));
		}
		else if (numero >= 50 && numero <= 59)
		{
			num_letra = "Cincuenta ";
			if (numero > 50)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 50));
		}
		else if (numero >= 40 && numero <= 49)
		{
			num_letra = "Cuarenta ";
			if (numero > 40)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 40));
		}
		else if (numero >= 30 && numero <= 39)
		{
			num_letra = "Treinta ";
			if (numero > 30)
				num_letra = num_letra.concat("y ").concat(unidad(numero - 30));
		}
		else if (numero >= 20 && numero <= 29)
		{
			if (numero == 20)
				num_letra = "Veinte ";
			else
				num_letra = "Veinti".concat(unidad(numero - 20));
		}
		else if (numero >= 10 && numero <= 19)
		{
			switch ((int)numero){
			case 10:

				num_letra = "Diez ";
				break;

			case 11:

				num_letra = "Once ";
				break;

			case 12:

				num_letra = "Doce ";
				break;

			case 13:

				num_letra = "Trece ";
				break;

			case 14:

				num_letra = "Catorce ";
				break;

			case 15:
			
				num_letra = "Quince ";
				break;
			
			case 16:
			
				num_letra = "Dieciseis ";
				break;
			
			case 17:
			
				num_letra = "Diecisiete ";
				break;
			
			case 18:
			
				num_letra = "Dieciocho ";
				break;
			
			case 19:
			
				num_letra = "Diecinueve ";
				break;
			
			}	
		}
		else
			num_letra = unidad(numero);

	return num_letra;
	}	

	private String centena(long numero){
		if (numero >= 100)
		{
			if (numero >= 900 && numero <= 999)
			{
				num_letra = "Novecientos ";
				if (numero > 900)
					num_letra = num_letra.concat(decena(numero - 900));
			}
			else if (numero >= 800 && numero <= 899)
			{
				num_letra = "Ochocientos ";
				if (numero > 800)
					num_letra = num_letra.concat(decena(numero - 800));
			}
			else if (numero >= 700 && numero <= 799)
			{
				num_letra = "Setecientos ";
				if (numero > 700)
					num_letra = num_letra.concat(decena(numero - 700));
			}
			else if (numero >= 600 && numero <= 699)
			{
				num_letra = "Seiscientos ";
				if (numero > 600)
					num_letra = num_letra.concat(decena(numero - 600));
			}
			else if (numero >= 500 && numero <= 599)
			{
				num_letra = "Quinientos ";
				if (numero > 500)
					num_letra = num_letra.concat(decena(numero - 500));
			}
			else if (numero >= 400 && numero <= 499)
			{
				num_letra = "Cuatrocientos ";
				if (numero > 400)
					num_letra = num_letra.concat(decena(numero - 400));
			}
			else if (numero >= 300 && numero <= 399)
			{
				num_letra = "Trescientos ";
				if (numero > 300)
					num_letra = num_letra.concat(decena(numero - 300));
			}
			else if (numero >= 200 && numero <= 299)
			{
				num_letra = "Doscientos ";
				if (numero > 200)
					num_letra = num_letra.concat(decena(numero - 200));
			}
			else if (numero >= 100 && numero <= 199)
			{
				if (numero == 100)
					num_letra = "Cien ";
				else
					num_letra = "Ciento ".concat(decena(numero - 100));
			}
		}
		else
			num_letra = decena(numero);
		
		return num_letra;	
	}	

	private String miles(long numero){
		if (numero >= 1000 && numero <2000){
			num_letram = ("Mil ").concat(centena(numero%1000));
		}
		if (numero >= 2000 && numero <10000){
			flag=1;
			num_letram = unidad(numero/1000).concat(" Mil ").concat(centena(numero%1000));
		}
		if (numero < 1000)
			num_letram = centena(numero);
		
		return num_letram;
	}		

	private String decmiles(long numero){
		if (numero == 10000)
			num_letradm = "Diez Mil";
		if (numero > 10000 && numero <20000){
			flag=1;
			num_letradm = decena(numero/1000).concat("Mil ").concat(centena(numero%1000));		
		}
		if (numero >= 20000 && numero <100000){
			flag=1;
			num_letradm = decena(numero/1000).concat(" Mil ").concat(miles(numero%1000));		
		}
		
		
		if (numero < 10000)
			num_letradm = miles(numero);
		
		return num_letradm;
	}		

	private String cienmiles(long numero){
		if (numero == 100000)
			num_letracm = "Cien Mil";
		if (numero >= 100000 && numero <1000000){
			flag=1;
			num_letracm = centena(numero/1000).concat(" Mil ").concat(centena(numero%1000));		
		}
		if (numero < 100000)
			num_letracm = decmiles(numero);
		return num_letracm;
	}		

	private String millon(long numero){
		if (numero >= 1000000 && numero <2000000){
			flag=1;
			num_letramm = ("Un Millon ").concat(cienmiles(numero%1000000));
		}
		if (numero >= 2000000 && numero <100000000){
			flag=1;
			num_letramm = unidad(numero/1000000).concat(" Millones ").concat(cienmiles(numero%1000000));
		}
		if (numero < 1000000)
			num_letramm = cienmiles(numero);
		
		return num_letramm;
	}		
	
	private String decmillon(long numero){
		if (numero == 10000000)
			num_letradmm = "Diez Millones";
		if (numero > 10000000 && numero <20000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat("Millones ").concat(cienmiles(numero%1000000));		
		}
		if (numero >= 20000000 && numero <100000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat(" Millones ").concat(millon(numero%1000000));		
		}
		
		
		if (numero < 10000000)
			num_letradmm = millon(numero);
		
		return num_letradmm;
	}
        
        private String cienmillon(long numero){
		if (numero == 100000000)
			num_letradmm = "Cien Millones";
		if (numero > 100000000 && numero <200000000){
			flag=1;
			num_letradmm = centena(numero/100000000).concat(" Millones ").concat(cienmiles(numero%1000000));		
		}
		if (numero >= 200000000 && numero <1000000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat(" Millones ").concat(millon(numero%1000000));		
		}
		
		
		if (numero < 100000000)
			num_letradmm = decmillon(numero);
		
		return num_letradmm;
        }
        
         private String milmillon(int numero){
		if (numero == 100000000)
			num_letradmm = "Mil Millones";
		if (numero > 100000000 && numero <200000000){
			flag=1;
			num_letradmm = centena(numero/100000000).concat(" Millones ").concat(cienmiles(numero%1000000));		
		}
		if (numero >= 200000000 && numero <1000000000){
			flag=1;
			num_letradmm = decena(numero/1000000).concat(" Millones ").concat(millon(numero%1000000));		
		}
		
		
		if (numero < 100000000)
			num_letradmm = decmillon(numero);
		
		return num_letradmm;
        }
        
	
	public String convertirLetras(long numero){
		num_letras = cienmillon(numero);
		return num_letras;
	} 	
}
