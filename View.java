import java.util.Scanner; 
import java.util.*;

public class View{

  Scanner scanner = new Scanner(System.in); //Se crea el scanner para recibir ingresos del usuario 

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

  public void Bye(){
    System.out.println("\nHasta luego..."); 
  }

  public void Result(int result){

  }

  public void Operation(String[] codigo){
    System.out.println("\n--PROGRAMA LISP--\n"); 
    for(int i = 0; i < codigo.length; i++){
      System.out.println(codigo[i]);
    } 

  }

  public void Error(){
    System.out.println("\nPrograma Lisp no valido. Revise su programa o intente con uno nuevo."); 
  }



}