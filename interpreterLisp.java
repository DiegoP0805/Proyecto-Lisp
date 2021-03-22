package cosas2;
import java.util.ArrayList;
public class interpreterLisp{

	String original="";
	View v= new View();
	public interpreterLisp(String oficial) {
		original=oficial.toLowerCase();
		v.ingreso(oficial);//se imprime el original
		v.proceso(original);//se imprime en minusculas
		
	}
    
	public int runInterprete() {//más adelante te método vebe retornar el int de result()
		Result(original,""); 
        return 1;
	}
 
  public void Result(String linea, String posibledefun){//result debe retornar un numero
    String abc = "abcdefghijklmnopqrstuvwxyz";
	String acu="";//acumula las letras para formar palabras y comparar	
	ArrayList<ArrayList<String>> funciones= new ArrayList<ArrayList<String>>();
	for (int y=0;y<linea.length();y++) {
		if(linea.charAt(y)!=')' && linea.charAt(y)!='(' && linea.charAt(y)!=' '){ //siempre que no sea parentesis y espacio que continue
			if (abc.indexOf(linea.charAt(y))>-1) {
		      acu=acu+linea.charAt(y);
		      switch(acu) {//se compara con todas las posibles instrucciones
		        case "cond":
		          v.funFound("cond");
		          acu="";
		          Result(adentro(linea.substring(y+2)),"");
		          y=limite(linea);
		          break;
		        case "quote":
		          v.funFound("quote");
		          acu="";
		          y=limite(linea);
		          //Result(adentro(linea.substring(y+2, limite(linea))),"");
		          break;
		        case "setq":
		          v.funFound("setq");
		          acu="";
		          Result(adentro(linea.substring(y+2)),"");
		          y=limite(linea);
		          break;
		        case "defun":
		          v.funFound("defun");
		          acu="";
		          Result(adentro(linea.substring(y+2)),"defun");
		          y=limite(linea);
		          break;
		        case "write":
		          v.funFound("write");
		          acu="";
		          Result(adentro(linea.substring(y+2)),"");
		          y=limite(linea);
		          break;
		        default:  //en caso de que se trate de una funcion nueva o aritmética
		          if(linea.charAt(y+1)==')' || linea.charAt(y+1)=='('|| linea.charAt(y+1)==' ' )
		          {
		        	  if(posibledefun.equals("defun")) {
		        		  funciones.add(OrganizaDefun(linea));
		        		  System.out.println(funciones);
		        		  //Result(adentro(linea.substring(y+2)),"");
		        	  }else {
		        		  
		        	  }	
		        	 
		          }
		          
		      }
		    }
		  }	else{//si es parentesis o espacio el acumulador se resetea
			  acu="";
		  }			
		
		}
		//funciones.add("popis");
		
		
	}

  private String adentro (String nuevaLinea) {//determina que hay dentro de cada instrucciÃ³n
  //recibe la posiciÃ³n en la que se quedÃ³ el for de arriba sÃ­ no recorre toda la linea
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
  
  private int limite( String nuevaLinea){
	//recibe la linea que se quiere analizar
	//hace lo mismo que el método adentro() pero debuelve cuan largo es el string acuparentesis
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
		int b=acuparentesis.length();
	    return b;
  }
   

  private ArrayList<String> OrganizaDefun(String nuevafun){
	  //mete todos los elementos de una funcion adentro de un array
	  //recibe el string que es detectado como una nueva función
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
//C:\Users\anard\Downloads\proyectoLisp\factorial.txt
