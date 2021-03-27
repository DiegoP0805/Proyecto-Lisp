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
    
	public Object runInterprete() {//m·s adelante te mÈtodo vebe retornar el int de result()
		Result(original,""); 
        return 1;
	}
 
  public void Result(String linea, String posibledefun){//result debe retornar un numero
     ArrayList<ArrayList<String>> funciones= new ArrayList<ArrayList<String>>();
	String abc = "abcdefghijklmnopqrstuvwxyz";
	String acu="";//acumula las letras para formar palabras y comparar	
	//System.out.println(linea);
	
	for (int y=0;y<linea.length();y++) {
		if(linea.charAt(y)!=')' && linea.charAt(y)!='(' && linea.charAt(y)!=' '){ //siempre que no sea parentesis y espacio que continue
			if (abc.indexOf(linea.charAt(y))>-1) {
		      acu=acu+linea.charAt(y);
		      ////System.out.println(acu);
		      System.out.println("00:"+ y);
		      switch(acu) {//se compara con todas las posibles instrucciones
		        case "cond":
		          v.funFound("cond");
		          acu="";
		          	
		          Result(adentro(linea.substring(y+2)),"");
		          y+=linea.substring(y).indexOf(')');
		          break;
		        case "quote":
		          v.funFound("quote");
		          acu="";
		          y+=linea.substring(y).indexOf(')');
		          //Result(adentro(linea.substring(y+2, limite(linea))),"");
		          break;
		        case "setq":
		          v.funFound("setq");
		          acu="";
		          Result(adentro(linea.substring(y+2)),"");
		          y+=linea.substring(y).indexOf(')');
		          break;
		        case "defun":
		          v.funFound("defun");
		          acu="";
		          Result(adentro(linea.substring(y+2)),"defun");
		          y+=linea.substring(y).indexOf(')');
		          break;
		        case "write":
		          v.funFound("write");
		          acu="";
		          System.out.println(adentro(linea.substring(y+2)));
		          Result(adentro(linea.substring(y+2)),"");
		          y+=linea.substring(y).indexOf(')');
		          break;
		        default:  //en caso de que se trate de una funcion nueva o aritmÈtica
		          if(linea.charAt(y+1)==')' || linea.charAt(y+1)=='('|| linea.charAt(y+1)==' ' )
		          {
		        	  if(posibledefun.equals("defun")) {
		        		  funciones.add(OrganizaDefun(linea));
		        		  System.out.println(funciones);
		        		  System.out.println("priemra y"+y);
		        		  System.out.println(linea.length());
		        		  y+=linea.length()+1;
		        		 
		        		  System.out.println("y:®"+String.valueOf(y));
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

  private String adentro (String nuevaLinea) {//determina que hay dentro de cada instrucci√≥n
  //recibe la posici√≥n en la que se qued√≥ el for de arriba s√≠ no recorre toda la linea
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
  
  private int limite( String n)
  {//recibe la linea que se quiere analizar
	  int par = 0;
	  int i = 0;
	  
	  
	  while (i < n.length())//si par no recibe parentesis pares entonces retorna donde lo encontrÛ
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
	  //recibe el string que es detectado como una nueva funciÛn
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

