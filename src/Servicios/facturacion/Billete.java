package Servicios.facturacion;

public class Billete {
	private static final String[] V1 = { "UN ", "DOS ", "TRES ",
        "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE "};
	
	private static final String[] V2 = { "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS ",
	    "DIECISIETE ", "DIECIOCHO ", "DIECINUEVE "};

	private static final String[] V3 = {"DIEZ ","VEINTI","TREINTA Y ","CUARENTA Y ","CINCUENTA Y ",
		"SESENTA Y ","SETENTA Y ","OCHENTA Y ","NOVENTA Y " };

	private static final String[] V4 = { "CIEN", "DOSCIENTOS ",
        "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
        "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };
	
	private static final String[] V5 = { "DIEZ ","VEINTE ","TREINTA ","CUARENTA ","CINCUENTA ",
				"SESENTA ","SETENTA ","OCHENTA ","NOVENTA "};
	
	
	public static String BilleteX(Double impnet_w,String money){
		String letras_w="";

		//letras_w=""
		if(impnet_w==0)
			letras_w="CERO ";
		
		if(impnet_w<0)
			letras_w="MENOS ";
		
		String strnet_w= String.format("%10s",String.format("%.2f",(Math.abs(impnet_w))));
		//System.out.println(strnet_w+"|");
		String cadena_w="   "+strnet_w.substring(0, 7);
		//System.out.println(cadena_w+"|");
		String fracci_w=strnet_w.substring(8,10);
		//System.out.println(fracci_w+"|");
		int indice_w=-1;
		String digito_w="";
		
		while( indice_w<9 ){
			indice_w=indice_w+1;
			digito_w=cadena_w.substring(indice_w, indice_w+1);
			if(digito_w.equals(" ")){
				continue;
			}
			
			int numero_w=Integer.parseInt(digito_w);//val(digito_w);
			String before_w=" ";
			if(indice_w>0)
				before_w=cadena_w.substring((indice_w-1),indice_w);
			
			String after_w=" ";
			int valor_w=0;
			
			if(indice_w<9){
				after_w=cadena_w.substring((indice_w+1),indice_w+2);
				valor_w=Integer.parseInt(after_w);
			}
			
			//UNIDADES
			if(indice_w==0 || indice_w==3 || indice_w==6 || indice_w==9){
				int d = 0;
				if(Character.isDigit(digito_w.charAt(0)))
					d=Integer.parseInt(digito_w);
				
				//21,22..28,29,31..88,89,91..99
				if(d > 0 && !before_w.equals("1")){
					
					if((digito_w.equals("1") && indice_w==6 && !before_w.equals(" ") || digito_w.equals("1") &&
					indice_w!=6) || !digito_w.equals("1"))
						letras_w+=V1[numero_w-1];
				}
					
				if(indice_w==0)
					letras_w+="MIL ";
		
				if(indice_w==6){
					int a = 0,b = 0, c = 0;
					if(Character.isDigit(before_w.charAt(0)))
						a=Integer.parseInt(before_w);
					if(Character.isDigit(cadena_w.substring((indice_w-2),indice_w-1).charAt(0)))
						b=Integer.parseInt(cadena_w.substring((indice_w-2),(indice_w-1)));
					if(Character.isDigit(digito_w.charAt(0)))
						c=Integer.parseInt(digito_w);
					
					if ((a>0 || b>0) || c>0) // 001...999
						letras_w+="MIL ";
					
				}
				
				if(indice_w==3){
					if(Math.abs(impnet_w)<2000000)
						letras_w+="MILLON ";
					else
						letras_w+="MILLONES ";
					
				}
			}
			
			//CENTENAS
			if(indice_w==1 || indice_w==4 || indice_w==7){
				if(Character.isDigit(digito_w.charAt(0)))
					if(Integer.parseInt(digito_w) > 0)
						letras_w+=V4[numero_w-1];
				
				if(digito_w.equals("1")) // 100,101..198,199
					if(after_w.equals("0") && cadena_w.substring((indice_w+2),(indice_w+3)).equals("0")) // 100
						letras_w+=" ";
					else	// 101..199
						letras_w+="TO ";
				
			}
			
			//DECENAS
			if(indice_w==2 || indice_w==5 || indice_w==8){ 
				if(Character.isDigit(digito_w.charAt(0))){
					if(Integer.parseInt(digito_w)>0){ 
						// digito_w = 1..9
						if(digito_w.equals("1")){ 
							// digito = 1
							if(after_w.equals("0")) // 10
								letras_w+=V3[0];
							else					// 11,12..18,19
								letras_w+=V2[valor_w-1];
						} else { 
							// valdig = 2..9
							int valdig_w=Integer.parseInt(digito_w);
							if(after_w.equals("0"))  //20,30..80,90
								letras_w+=V5[valdig_w-1];
							else					 //21,22..28,29,31..88,89,91..99
								letras_w+=V3[valdig_w-1];
						}
					}
				}
			}
		}
		
		if(digito_w.equals("1"))
			letras_w=letras_w.trim()+" ";

//		letras_w=letras_w+"Y "+fracci_w+"/100 "+money;
                letras_w=letras_w+"CON "+fracci_w+"/100 "+money;
		return letras_w;
	}
	
        public static String BilleteX(Double impnet_w){
		String letras_w="";

		//letras_w=""
		if(impnet_w==0)
			letras_w="CERO ";
		
		if(impnet_w<0)
			letras_w="MENOS ";
		
		String strnet_w= String.format("%10s",String.format("%.2f",(Math.abs(impnet_w))));
		//System.out.println(strnet_w+"|");
		String cadena_w="   "+strnet_w.substring(0, 7);
		//System.out.println(cadena_w+"|");
		String fracci_w=strnet_w.substring(8,10);
		//System.out.println(fracci_w+"|");
		int indice_w=-1;
		String digito_w="";
		
		while( indice_w<9 ){
			indice_w=indice_w+1;
			digito_w=cadena_w.substring(indice_w, indice_w+1);
			if(digito_w.equals(" ")){
				continue;
			}
			
			int numero_w=Integer.parseInt(digito_w);//val(digito_w);
			String before_w=" ";
			if(indice_w>0)
				before_w=cadena_w.substring((indice_w-1),indice_w);
			
			String after_w=" ";
			int valor_w=0;
			
			if(indice_w<9){
				after_w=cadena_w.substring((indice_w+1),indice_w+2);
				valor_w=Integer.parseInt(after_w);
			}
			
			//UNIDADES
			if(indice_w==0 || indice_w==3 || indice_w==6 || indice_w==9){
				int d = 0;
				if(Character.isDigit(digito_w.charAt(0)))
					d=Integer.parseInt(digito_w);
				
				//21,22..28,29,31..88,89,91..99
				if(d > 0 && !before_w.equals("1")){
					
					if((digito_w.equals("1") && indice_w==6 && !before_w.equals(" ") || digito_w.equals("1") &&
					indice_w!=6) || !digito_w.equals("1"))
						letras_w+=V1[numero_w-1];
				}
					
				if(indice_w==0)
					letras_w+="MIL ";
		
				if(indice_w==6){
					int a = 0,b = 0, c = 0;
					if(Character.isDigit(before_w.charAt(0)))
						a=Integer.parseInt(before_w);
					if(Character.isDigit(cadena_w.substring((indice_w-2),indice_w-1).charAt(0)))
						b=Integer.parseInt(cadena_w.substring((indice_w-2),(indice_w-1)));
					if(Character.isDigit(digito_w.charAt(0)))
						c=Integer.parseInt(digito_w);
					
					if ((a>0 || b>0) || c>0) // 001...999
						letras_w+="MIL ";
					
				}
				
				if(indice_w==3){
					if(Math.abs(impnet_w)<2000000)
						letras_w+="MILLON ";
					else
						letras_w+="MILLONES ";
					
				}
			}
			
			//CENTENAS
			if(indice_w==1 || indice_w==4 || indice_w==7){
				if(Character.isDigit(digito_w.charAt(0)))
					if(Integer.parseInt(digito_w) > 0)
						letras_w+=V4[numero_w-1];
				
				if(digito_w.equals("1")) // 100,101..198,199
					if(after_w.equals("0") && cadena_w.substring((indice_w+2),(indice_w+3)).equals("0")) // 100
						letras_w+=" ";
					else	// 101..199
						letras_w+="TO ";
				
			}
			
			//DECENAS
			if(indice_w==2 || indice_w==5 || indice_w==8){ 
				if(Character.isDigit(digito_w.charAt(0))){
					if(Integer.parseInt(digito_w)>0){ 
						// digito_w = 1..9
						if(digito_w.equals("1")){ 
							// digito = 1
							if(after_w.equals("0")) // 10
								letras_w+=V3[0];
							else					// 11,12..18,19
								letras_w+=V2[valor_w-1];
						} else { 
							// valdig = 2..9
							int valdig_w=Integer.parseInt(digito_w);
							if(after_w.equals("0"))  //20,30..80,90
								letras_w+=V5[valdig_w-1];
							else					 //21,22..28,29,31..88,89,91..99
								letras_w+=V3[valdig_w-1];
						}
					}
				}
			}
		}
		
		if(digito_w.equals("1"))
			letras_w=letras_w.trim(); //+"  ";

//		letras_w=letras_w+"Y "+fracci_w+"/100 "+money;
                //letras_w=letras_w+"CON "+fracci_w+"/100 "+money;
                letras_w=letras_w.trim();
		return letras_w;
	}
}
