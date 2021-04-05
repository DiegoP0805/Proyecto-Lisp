import java.util.*;

public class Calculator{

  /** 
   * Función de tipo boolean que devuelve el valor verdadero o falso según la cantidad de paréntesis 
   * de apertura y cierre coincidan.
   * @param s
   * @return boolean
   */
  private static boolean validateParenthesis(String s)
  {
      int acu = 0;
      for(int i = 0; i < s.length(); i++ )
      {
          if(s.charAt(i) == '(')
          {
              acu++;
          }
          else if(s.charAt(i) == ')')
          {
              acu--;
          }
      }

      if(acu == 0)
      {
          return true;
      }
      else
      {
          return false;
      }
  }

    /** 
     * función que se encarga de recibir y analizar la operación matemática
     * en prefix.
     * @param String[]
     * @return String
     */
  public String calc(String[] prefix){
        
    Stack<Double> stackOne = new Stack<>();

    String signo = "";

    if(prefix.length == 1){
        stackOne.push(Double.parseDouble(prefix[0]));
    }else if(prefix.length == 3){
        stackOne.push(Double.parseDouble(prefix[1]));
    }else{
        signo = String.valueOf(prefix[1]);
        for (int i = 2; i < prefix.length; i++){ //2 representa el primer coso después del primer signo matemático
            if(prefix[i].equals("(")){

              int tempAmount = prefix.length - (i);
              String[] tempArray = new String[tempAmount];

              for(int k = 0; k < tempAmount ; k++){
                  tempArray[k] = prefix[i+k];
              }

              String finalString = String.join(" ", tempArray);
              String[] inside = adentro(finalString);
              String finalString2 = String.join(" ", inside);

              stackOne.push(Double.parseDouble(calc(inside)));

              i = i+inside.length;

            }else if(prefix[i].equals(")")){
                i++;
            }else{
              stackOne.push(Double.parseDouble(prefix[i]));
            }
            
          }
    }
    
    if(signo.equals("+")){
        stackOne.push(sumar(stackOne));
    }
    if(signo.equals("-")){
        stackOne.push(restar(stackOne));
    }
    if(signo.equals("*")){
        stackOne.push(multiplicar(stackOne));
    }
    if(signo.equals("/")){
        stackOne.push(dividir(stackOne));
    }
    
    return Double.toString(stackOne.pop());
  }

  /** 
     * Regresa el contenido de un set inicial y final de parentesis
     * @param String
     * @return String[]
     */
  private String[] adentro (String nuevaLinea) {//determina que hay dentro de cada instrucción
    //recibe la posición en la que se quedó el for de arriba sí no recorre toda la linea
      int parentesis=0; //se emplea conteo de parentesis para esto
      String acuparentesis="";
      for (int i=0;i<nuevaLinea.length();i++) {
            if(nuevaLinea.charAt(i)=='(' ) {
                  parentesis++;
              }else if(nuevaLinea.charAt(i)==')') {
                  parentesis--;			
              }
            if (parentesis>0) {
                  acuparentesis=acuparentesis+nuevaLinea.charAt(i);
             }else {
                 i=nuevaLinea.length();
             }
        }
      return acuparentesis.split(" ");
    }
    
    
    /** 
     * Se encarga de sumar
     * @param Stack<Double>
     * @return Double
     */
  public double sumar(Stack<Double> value){
      
    double answer = 0.00;
    int lenstack = value.size();
    for(int control = 0; control<lenstack;control++){
        answer += value.pop();
    }
    return answer;
  }

  /** 
     * Se encarga de multiplicar
     * @param Stack<Double>
     * @return Double
     */
  public double multiplicar(Stack<Double> value){
    double answer = value.pop();
    int lenstack = value.size();
    for(int control = 0; control<lenstack;control++){
        answer *= value.pop();
    }        
    return answer;
  }
    
  /** 
     * Se encarga de restar
     * @param Stack<Double>
     * @return Double
     */
  public double restar(Stack<Double> value){
    Stack<Double> temp_stack = revertStack(value);
    double answer = temp_stack.pop();
    int lenstack = temp_stack.size();
    
    for(int control = 0; control<lenstack;control++){
        answer -= temp_stack.pop();
    }
    return answer;
  }

  /** 
     * Se encarga de dividir
     * @param Stack<Double>
     * @return Double
     */
  public double dividir(Stack<Double> value){
    Stack<Double> temp_stack = revertStack(value);
    double answer = temp_stack.pop();
    int lenstack = temp_stack.size();
    
    for(int control = 0; control<lenstack;control++){
        answer /= temp_stack.pop();
    }
    
    return answer;
  }
    
    /** 
     * Se encarga de revertir el orden del stack
     * @param Stack<Double>
     * @return Double
     */
  public Stack<Double> revertStack(Stack<Double> value){
    Stack<Double> temp_stack = new Stack<Double>();
    while(!value.empty()){
        temp_stack.push((double)value.pop());
    }
    
    return temp_stack;
  } 




}