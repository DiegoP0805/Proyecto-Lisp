
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {

    View vista = new View();
    boolean verifier = true;

    while(verifier){
      String location = vista.Welcome();

      if(location != null){

        OpenLisp opener = new OpenLisp();
        String[] codigo = opener.open(location);

        String str = "";
        for(int i = 0; i < codigo.length; i++){
          str = str + codigo[i];
        }

        if(codigo == null){
          vista.Error();
        }else{
          vista.Operation(codigo);

          interpreterLisp interprete = new interpreterLisp(str);
          interprete.runInterprete();
          //interprete.Result(str,"");

          Calculator calc = new Calculator();
          String[] a = {"+","1","2","3"};

          System.out.println(calc.calc(a));
        }

      }else{
        vista.Bye();
        verifier = false;

      }
    }
   
  }
}