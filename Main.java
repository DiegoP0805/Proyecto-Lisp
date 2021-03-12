import java.util.Arrays;

class Main {

  public static void main(String[] args) {

    View vista = new View();
    boolean verifier = true;

    while(verifier){
      String location = vista.Welcome();

      if(location != null){

        openLisp opener = new openLisp();
        String[] codigo = opener.open(location);

        String str = "";
        for(int i = 0; i < codigo.length; i++){
          str = str + codigo[i];
        }

        if(codigo == null){
          vista.Error();
        }else{
          vista.Operation(codigo);

          //interpreterLisp interprete = new interpreterLisp();

          //interprete.Result(str);

          Calculator calc = new Calculator();
          String[] a = {"(","/","10","(","*","10","5",")",")"};

          Double b = calc.Convertir(a);
          System.out.println(b);
        }

      }else{
        vista.Bye();
        verifier = false;

      }
    }
   
  }
}