import java.util.*;

import jdk.internal.jshell.tool.StopDetectingInputStream;
public class Calculator{

  /** 
   * Método que recibe un arreglo de expresiones infix que devuelve luego un arreglo de expresiones 
   * postfix que son el equivalente de las originales.
   * @param s
   * @return String
   */
  public Double Convertir(String[] s)
  {
      Character[] signs = {'+','-','*','/'};
      ArrayList<Character> sg = new ArrayList<>(Arrays.asList(signs));        
      ArrayList<String> Postfix = new ArrayList<>();
      String operacion = ""; 
      
      for(String qwerty: s)
      {
          operacion = "";
          if(validateParenthesis(qwerty))
          {
              Stack<Character> pila = new Stack<>();
              for(int i = 0; i < qwerty.length(); i++)
              {
                  char temp = qwerty.charAt(i);
                  if(Character.isDigit(temp))
                  {
                      operacion += String.valueOf(temp);
                  }
                  else if(temp == '(')
                  {
                      pila.push(temp);
                  }
                  else if(temp == ')')
                  {
                      while(!pila.empty() && pila.peek() != '(')
                      {
                          operacion += pila.pop();
                      }
                      pila.pop();
                  }
                  else if(sg.contains(temp))
                  {
                      while(!pila.empty() && jerarquia(temp) <= jerarquia(pila.peek()))
                      {
                          operacion += pila.pop();
                      }
                      pila.push(temp);
                  }
                  
              }
              while(!pila.empty())
              {
                  operacion += pila.pop();
              }

              Postfix.add(operacion);
          } 
      }
      String[] expresiones = new String[Postfix.size()];
      for(int i = 0; i < Postfix.size(); i++)
      {
          expresiones[i] = Postfix.get(i);
      }

      String c = "";
          
      for(int i = 0; i < expresiones.length; i++){
        c = c+" "+expresiones[i];
      }

      System.out.println(c);

     
    return calc(expresiones);
  }
  
  /** 
   * Función que devuelve el valor de un signo de operación que representa un valor asignado según
   * la jerarquía de importancia de la operación
   * @param c
   * @return int
   */
  private static int jerarquia(char c)
  {
      switch(c)
      {
          case '+': 
          case '-': 
              return 1; 
          case '*': 
          case '/': 
              return 2; 
          case '^': 
              return 3; 
      } 
      return -1; 
  }



  
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

  public Double calc(String[] prefix){
        
    Stack<Double> stackOne = new Stack<>();

    String signo = String.valueOf(prefix[0]);
    
    for (int i = 1; i < prefix.length; i++){
      if(prefix[i].equals("+") || prefix[i].equals("-") || prefix[i].equals("*") || prefix[i].equals("-")){
        int tempAmount = prefix.length - (i);
        String[] tempArray = new String[tempAmount];

        for(int j = 0; j < tempAmount; j++){
          tempArray[j] = prefix[i+j];
        }
        stackOne.push((calc(tempArray)));
        break;
      }else{
        stackOne.push(Double.parseDouble(prefix[i]));
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
    
    return stackOne.pop();
  }
    

  public double sumar(Stack<Double> value){
      
    double answer = 0.00;
    int lenstack = value.size();
    for(int control = 0; control<lenstack;control++){
        answer += value.pop();
    }
    return answer;
  }

  public double multiplicar(Stack<Double> value){
    double answer = value.pop();
    int lenstack = value.size();
    for(int control = 0; control<lenstack;control++){
        answer *= value.pop();
    }        
    return answer;
  }
    
  public double restar(Stack<Double> value){
    Stack<Double> temp_stack = revertStack(value);
    double answer = temp_stack.pop();
    int lenstack = temp_stack.size();
    
    for(int control = 0; control<lenstack;control++){
        answer -= temp_stack.pop();
    }
    return answer;
  }

  public double dividir(Stack<Double> value){
    Stack<Double> temp_stack = revertStack(value);
    double answer = temp_stack.pop();
    int lenstack = temp_stack.size();
    
    for(int control = 0; control<lenstack;control++){
        answer /= temp_stack.pop();
    }
    
    return answer;
  }
    
  public Stack<Double> revertStack(Stack<Double> value){
    Stack<Double> temp_stack = new Stack<Double>();
    while(!value.empty()){
        temp_stack.push((double)value.pop());
    }
    
    return temp_stack;
  } 




}