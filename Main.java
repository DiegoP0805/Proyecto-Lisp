/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
*@author Stefano Aragoni
*
********************************************************/

import java.util.Arrays;
import java.util.*;
import java.util.ArrayList;

public class Main {

  /** 
  * Metodo de clase principal
  */
  
  public static void main(String[] args) {

    Cond cond = new Cond();
    HashMap<String, String> vars = new HashMap<String, String>();
    String s = "(cond ((= 2.0 1.0) 1 )  (t (cond ((= 0 0) 0 )(t ( + fibonacci ( - 0.0 1 ) fibonacci ( - 0.0 2 ) )))))";
    String a2 = cond.findcondition(s, vars);
    System.out.println(a2);

    View vista = new View();
    boolean verifier = true;

    while(verifier){
      String location = vista.Welcome();

      //mientras el codigo ingresado sea valido
      if(location != null){

        OpenLisp opener = new OpenLisp();
        String[] codigo = opener.open(location);

        String str = "";
        for(int i = 0; i < codigo.length; i++){
          str = str + codigo[i];
        }

        if(codigo == null){
          //si el codigo no es correcto, tira error
          vista.Error();
        }else{
          vista.Operation(codigo);

          //envia el codigo correcto al interprete
          interpreterLisp interprete = new interpreterLisp(str);
          interprete.runInterprete();


        }

      }else{
        vista.Bye();
        verifier = false;

      }
    }
   
  }
}