import java.util.*;
import java.util.ArrayList;
public class interpreterLisp{

	String original="";
	View v= new View();

	HashMap<String, String> vars = new HashMap<String, String>();
	HashMap<String, String[]> vars2 = new HashMap<String, String[]>();

	String inside = "";

	ArrayList<ArrayList<String>> funciones= new ArrayList<ArrayList<String>>();
	public interpreterLisp(String oficial) {
		original=oficial.toLowerCase();
		
	}
    
	public Object runInterprete() {//m�s adelante te m�todo vebe retornar el int de result()
		String resultFinalString = Result(original,""); 
        return resultFinalString;
	}
 
  public String Result(String linea, String posibledefun){//result debe retornar un numero
	String abc = "abcdefghijklmnopqrstuvwxyz";
	String acu="";//acumula las letras para formar palabras y comparar	
	int y=0;

	String resultFinal = "";
	
	while(y<linea.length()){
		
		if(linea.charAt(y)!=')' && linea.charAt(y)!='(' && linea.charAt(y)!=' '){ //siempre que no sea parentesis y espacio que continue

			if (abc.indexOf(linea.charAt(y))>-1) {
		      acu=acu+linea.charAt(y);

		      switch(acu) {//se compara con todas las posibles instrucciones
		        case "cond":
		          v.funFound("cond");
		          acu="";
		          resultFinal = Result(adentro(linea.substring(y+2)),"");
		          y=limite(linea,y);
		          break;
		        case "setq":

					v.funFound("setq");
					acu="";

					String instruccion = adentro(linea.substring(y+2));

					String[] splited = instruccion.split(" ");

					String var = splited[0];
					int len = var.length();

					String rest = (instruccion.substring(len)).trim();

					resultFinal = Result(rest,"");

					vars.put(var, resultFinal);

					y=limite(linea,y);
					break;

				case "list":

					v.funFound("list");
					acu="";

					String instruccion2 = adentro(linea.substring(y+2));

					String[] splited2 = instruccion2.split(" ");

					String var2 = splited2[0];
					if(splited2[1].equals("'") || splited2[1].equals("quote")){
						String var3 = var2  + " " + splited2[1];

						int len2 = var3.length();

						String instru = adentro((instruccion2.substring(len2)).trim());

						String[] rest2 = (instru.split(" "));
						
						vars2.put(var2, rest2);

					}else{
						int len2 = var2.length();

						String rest2 = (instruccion2.substring(len2)).trim();

						String[] rest3 = rest2.split(" ");

						for(int i = 0; i < rest3.length; i++){
							if(rest3[i].equals("(")){

							}else if(rest3[i].equals(")")){

							}else{
								rest3[i] = Result(rest3[i],"");
							}
								
						}

						vars2.put(var2, rest3);
					}

					y=limite(linea,y);
					break;

		        case "defun":
		          v.funFound("defun");
		          acu="";
		          resultFinal = Result(adentro(linea.substring(y+2)),"defun");
		          y=limite(linea,y);
		          break;
		        case "write":
		          v.funFound("write");
		          acu="";
				  inside = (adentro(linea.substring(y+2))).trim();
				  String[] a = inside.split(" ");

				  if(a[0].equals("'") || a[0].equals("quote")){
					  String finalText = "";
					  for(int k = 2; k<a.length-1;k++){
						  finalText = finalText +" "+a[k];
					  }
					  System.out.println("Resultado: " + finalText+"\n");
				  }else{
					boolean b = true;
					for(int i = 0; i < a.length ; i++){
					  if(a[i].equals("+")){
						  b=false;
						  break;
					  }else if(a[i].equals("-")){
						  b=false;
						  break;
					  }else if(a[i].equals("*")){
						  b=false;
						  break;
					  }else if(a[i].equals("/")){
						  b=false;
						  break;
					  }
					}
					if(b){
					  for(int i = 0; i < a.length; i++){
						  if(i == 0){
							  a[0] = "";
						  }if(i == a.length - 1){
							  a[i] = "";
						  }
					  }
					}
					
					String finalString2 = (String.join(" ", a)).trim();
  
					resultFinal = Result(finalString2,"");
					System.out.println("Resultado: " + resultFinal+"\n");
				  }
				  y=limite(linea,y);
		          break;
		        default:  //en caso de que se trate de una funcion nueva o aritm�tica
					String[] testFinal = acu.split(" ");
					Character testFinal2 = 'a';
					
					try {
						if(linea.length() == y+1){

						}else{
							testFinal2 = linea.charAt(y+1);
						}
						
					} catch (StringIndexOutOfBoundsException e) {
						
					}

				  if(vars.containsKey(acu) && testFinal.length < 3){
					resultFinal = vars.get(acu);
					y+=linea.length()+1;
				  }else if(vars2.containsKey(acu) && testFinal.length < 3){
					String[] final100 = vars2.get(acu);
					final100[0] = "";
					final100[final100.length-1] = "";
					String finalList = String.join(" ", final100);

					finalList = adentro(finalList);
					resultFinal = finalList;
					y+=linea.length()+1;
				  }
		          else if(testFinal2 ==')' || testFinal2 =='('|| testFinal2 ==' ')//para verificar que ahi termina la palabra
		          {
					if(vars.containsKey(acu)){
						resultFinal = vars.get(acu);
						y+=linea.length()+1;
					  }
					  else if(vars2.containsKey(acu)){
						String[] final100 = vars2.get(acu);
						final100[0] = "";
						final100[final100.length-1] = "";
						String finalList = String.join(" ", final100);

						finalList = adentro(finalList);
						resultFinal = finalList;
						y+=linea.length()+1;
					  }

					  else if(posibledefun.equals("defun")) {
		        		  funciones.add(OrganizaDefun(linea));
		        		  System.out.println(funciones);

		        		  y+=linea.length()+1;
		        		 
		        	  }else{
		        		  String acufuncion=""; //tendra la funcion pero con la variable reemplazada


		        		  for (int f=0;f<funciones.size();f++) {

		        			  if(funciones.get(f).get(0).equals(acu)){//verifica que se trate de una funcion existente
								String letter = funciones.get(f).get(2);

		        				  for(int r=4;r<funciones.get(f).size();r++) {//se comienza a generar el string nuevo
		        					  if(funciones.get(f).get(r).equals(letter)) {//si encuentra la variable la reemplaza
										String acupedacito="";//guarda lo va a dentro de la funcion que se llama
										acupedacito=adentro(linea.substring(y+2));
										acufuncion+=Result(acupedacito,"");
		        					  }else {
		        						  acufuncion+=funciones.get(f).get(r);
		        					  }
		        				  }
		        			  }
		        		  }
						    y+=linea.length()+1;
							resultFinal = Result(acufuncion,"");//se ingresa el string nuevo a la funcion de recursión
						  


		        		  
		        	  }	

				
		        	 
		          }else if(testFinal.length == 1 || testFinal.length == 3){
					resultFinal = acu;
				  }
				}
		      
		    }else{
				Calculator calc = new Calculator();
				String newCalc = "";
				int par = 0;
				for(int i = 0; i < linea.length(); i++){
					if(linea.charAt(i) == '('){
						newCalc = newCalc + linea.charAt(i);

					}else if(linea.charAt(i) == ')'){
						newCalc = newCalc + linea.charAt(i);

					}else if(linea.charAt(i) == '+'){
						newCalc = newCalc + linea.charAt(i);
					}
					else if(linea.charAt(i) == '-'){
						newCalc = newCalc + linea.charAt(i);
					}
					else if(linea.charAt(i) == '*'){
						newCalc = newCalc + linea.charAt(i);
					}
					else if(linea.charAt(i) == '/'){
						newCalc = newCalc + linea.charAt(i);
					}else if(linea.charAt(i) == ' '){
						newCalc = newCalc + linea.charAt(i);
					}else{

						try {
							String tempCalc2 = "" + linea.charAt(i);
							Double.parseDouble(tempCalc2);
							String tempCalc3 = (linea.substring(i)).trim();

							int tempCont = 0;
							for(int k = 0; k < tempCalc3.length(); k++){
								
								if(tempCalc3.charAt(k) == ' '){
									break;
								}
								tempCont ++;
							}

							
							String tempCalc4 = (linea.substring(i, i+tempCont)).trim();
							i = i + tempCont-1;
							newCalc = newCalc + tempCalc4;
							

						} catch (NumberFormatException e) {
							int parentesis=1; //se emplea conteo de parentesis para esto
							String acuparentesis="";
							String tempCalc = linea.substring(i);

							for (int j=0;j<tempCalc.length();j++) {
								if(tempCalc.charAt(j)=='(' ) {
										
										parentesis++;
								}else if(tempCalc.charAt(j)==')') {
										
										parentesis--;			
								}
								if (parentesis>=0) {
										acuparentesis=acuparentesis+tempCalc.charAt(j);
								}else {
									j=tempCalc.length();
								}
							}

							i = i + acuparentesis.length()-3;

							String temp1000 = Result(acuparentesis, "");
							newCalc = newCalc + temp1000;
						}
						
					}
					
				}
				resultFinal = calc.calc(newCalc.split(" "));
				y=linea.length();

			}
		  }	else{//si es parentesis o espacio el acumulador se resetea
			  acu="";
		}			

		y++;
		
	}
		//funciones.add("popis");

		return resultFinal;
		
		
	}

  private String adentro (String nuevaLinea) {//determina que hay dentro de cada instrucción
  //recibe la posición en la que se quedó el for de arriba sí no recorre toda la linea
	int parentesis=0; //se emplea conteo de parentesis para esto
	String acuparentesis="";
	for (int i=0;i<nuevaLinea.length();i++) {
		  if(nuevaLinea.charAt(i)=='(' ) {
				parentesis++;
			}else if(nuevaLinea.charAt(i)==')') {
				parentesis--;			
			}
		  if (parentesis>=0) {
				acuparentesis=acuparentesis+nuevaLinea.charAt(i);
		   }else {
			   i=nuevaLinea.length();
		   }
	  }
	v.insideFun(acuparentesis);
    return acuparentesis;
	}
  
  private int limite(String n, int a)
  {//recibe la linea que se quiere analizar
	  int par = 1;
	  int i = a;
	  
	  
	  
	  while (i < n.length())//si par no recibe parentesis pares entonces retorna donde lo encontr�
	  {
		 if (n.charAt(i) == ')') par--;
		 if (n.charAt(i) == '(') par++;
		 if (par == 0) {/*System.out.println(String.valueOf(i));*/ return i;}
		i++;  
	  }
	  //if (par != 0) throw new Exception("RIP");
	  
	  return 0;
  }
   

  private ArrayList<String> OrganizaDefun(String nuevafun){
	  //mete todos los elementos de una funcion adentro de un array
	  //recibe el string que es detectado como una nueva funci�n
	  String acu="";
	  ArrayList<String> organizador=new ArrayList<String>();
	  for(int t=0; t<nuevafun.length();t++) {
		  //acu=acu+nuevafun.charAt(t);
		  if(nuevafun.charAt(t)!=')' && nuevafun.charAt(t)!='(' && nuevafun.charAt(t)!=' ') {
			  if(nuevafun.charAt(t+1)==')' || nuevafun.charAt(t+1)=='(' || nuevafun.charAt(t+1)==' ') {
				  acu=acu+nuevafun.charAt(t);
				  organizador.add(acu);
				  acu="";
			  }else {
				  acu=acu+nuevafun.charAt(t);
			  }
		  }else {
			  organizador.add(String.valueOf( nuevafun.charAt(t)));
		  }
	  }
	  //System.out.println(organizador);
	  return organizador;
  }

}

//C:\Users\anard\Downloads\Proyecto-Lisp-main\Proyecto-Lisp-main\factorial.txt




