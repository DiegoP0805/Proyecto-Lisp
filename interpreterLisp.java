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
		v.ingreso(oficial);//se imprime el original
		v.proceso(original);//se imprime en minusculas
		
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
		        /*case "cond":
		          v.funFound("cond");
		          acu="";
		          resultFinal = Result(adentro(linea.substring(y+2)),"");
		          y=limite(linea,y);
		          break;
		        *//*case "quote":
		          v.funFound("quote");
		          acu="";
		          y=limite(linea,y);
		          //Result(adentro(linea.substring(y+2, limite(linea))),"");
		          break;
		        */case "setq":

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
				/*case "list":

					Result(adentro(linea.substring(y+2)),"");

					y=limite(linea,y);
					break;

		        */case "defun":
		          v.funFound("defun");
		          acu="";
		          resultFinal = Result(adentro(linea.substring(y+2)),"defun");
		          y=limite(linea,y);
		          break;
		        case "write":
		          v.funFound("write");
		          acu="";
				  inside = adentro(linea.substring(y+2));
		          resultFinal = Result(inside,"");
				  System.out.println("\nResultado:" + resultFinal);
				  y=limite(linea,y);
		          break;
		        default:  //en caso de que se trate de una funcion nueva o aritm�tica
				  if(vars.containsKey(acu)){
					resultFinal = vars.get(acu);
					y+=linea.length()+1;
				  }
		          else if(linea.charAt(y+1)==')' || linea.charAt(y+1)=='('|| linea.charAt(y+1)==' ' )//para verificar que ahi termina la palabra
		          {
					  if(posibledefun.equals("defun")) {
		        		  funciones.add(OrganizaDefun(linea));
		        		  System.out.println(funciones);

		        		  y+=linea.length()+1;
		        		 
		        	  }else {
		        		  String acufuncion=""; //tendra la funcion pero con la variable reemplazada


		        		  for (int f=0;f<funciones.size();f++) {

		        			  if(funciones.get(f).get(0).equals(acu)){//verifica que se trate de una funcion existente
		        				  for(int r=4;r<funciones.get(f).size();r++) {//se comienza a generar el string nuevo
		        					  if(funciones.get(f).get(r).equals(funciones.get(f).get(2))) {//si encuentra la variable la reemplaza
										String acupedacito="";//guarda lo va a dentro de la funcion que se llama
										acupedacito=adentro(linea.substring(y+2));
										acufuncion+=Result(acupedacito,"");
		        					  }else {
		        						  acufuncion+=funciones.get(f).get(r);
		        					  }
		        				  }
		        			  }
		        		  }


		        		  resultFinal = Result(acufuncion,"");//se ingresa el string nuevo a la funcion de recursión
		        	  }	
				
		        	 
		          }
				}
		      
		    }else{
				Calculator calc = new Calculator();
				String[] lineatemp = linea.split(" ");
				resultFinal = calc.calc(lineatemp);
				//y=linea.length();

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




