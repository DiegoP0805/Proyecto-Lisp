class Main {

  public static void main(String[] args) {

    View vista = new View();
    boolean verifier = true;

    while(verifier){
      String location = vista.Welcome();

      if(location != null){

        openLisp opener = new openLisp();
        String[] codigo = opener.open(location);

        if(codigo == null){
          vista.Error();
        }else{
          vista.Operation(codigo);
        }

      }else{
        vista.Bye();
        verifier = false;

      }
    }
   
  }
}