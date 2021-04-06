/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
*@author Stefano Aragoni y Ana Ramirez
*
********************************************************/

import java.util.Scanner; 
import java.util.*;

public class View{

  Scanner scanner = new Scanner(System.in); //Se crea el scanner para recibir ingresos del usuario 

  public View(){
    
  }

  
  /** 
   * Imprime el texto de bienvenida del usuario. le pregunta qué quiere hacer.
   * @return String
   */
  public String Welcome(){
    boolean testBool = true;
    String location = null;

    System.out.println("\n--Interprete de Lisp--\n\nMenu:\n1- Interpretar programa Lisp.\n2- Salir."); 

    while(testBool){
      int opt1 = scanner.nextInt(); 
        
      if(opt1 == 1){
        System.out.println("\nPor favor ingresar la direccion del archivo lisp..."); 
        location = scanner.next();
        testBool = false;
      }else if(opt1 == 2){
        testBool = false;
      }else{
        System.out.println("\nPor favor ingrese una opcion valida"); 
      }
    }

    return location;
  
  }

  //imprime mensaje de despedida
  public void Bye(){
    System.out.println("\nHasta luego..."); 
  }
  
  /** 
   * Imprime el codigo lisp del .txt
   * @param codigo
   */
  public void Operation(String[] codigo){
    System.out.println("\n--PROGRAMA LISP--\n"); 
    for(int i = 0; i < codigo.length; i++){
      System.out.println(codigo[i]);
    } 
    System.out.print("\n\n");
  }

  
  /** 
   * Imrpime funciones encontradas
   * @param funcion
   */
  public void funFound(String funcion) {
		System.out.println("Se ha encontrado la funcion: "+funcion);
	}
	
	
  /** 
   * Imprime la parte de codigo que se encuntra dentro de otra. Demuestra la recursividad
   * @param actions
   */
  public void insideFun(String actions) {
		System.out.println("Dicha funcion contiene: "+actions);
	}
	
	
  /** 
   * Imprime el codigo original
   * @param input
   */
  public void ingreso(String input) {
		System.out.println("Se ha ingresado "+input);
	}
	
  /** 
   * Imprime el codigo original en minusculas
   * @param processed
   */
  public void proceso(String processed) {
		System.out.println("Se ha modificado a "+processed);
	}

  //Imprime si hubo algun error
  public void Error(){
    System.out.println("\nPrograma Lisp no valido. Revise su programa o intente con uno nuevo."); 
  }



}