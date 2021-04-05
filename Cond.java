/**
 * @author Diego Perdomo
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Cond 
{
    
    /**
     * Constructor
     */
    public Cond()
    {
    }


    /** 
     * Dado un conjunto de código dentro de una función cond (puede o no puede incluir cond), devuelve el código 
     * correspondiente si se cumple o no el condicional de la función
     * @param s
     */
    public String findcondition(String s, HashMap hm)
    {
        String acu = "";
        int p1 = 0;
        int p2 = 0;
        boolean flag1 = true;
        boolean flag2 = true;        
        String condicional = "";
        for(int i = 0; i < s.length();i++)
        {
            if(flag1)
            {
                if (s.charAt(i) == '<' || s.charAt(i) == '=' ||s.charAt(i) == '>')
                {
                    p1 = i;
                    flag1 = false;
                }
            }
            else
            {
                if(flag2)
                {
                    if(s.charAt(i) == ')')
                    {
                        p2 = i;
                        flag2 = false;
                    }
                }
            }
        }
        condicional = s.substring(p1, p2);


        String si = "";
        String sino = "";
        int pos = 0;
        boolean f = true;
        for(int i = 2; i < s.length(); i++)
        {
            String t = s.substring(i-2, i+1);
            if (t.equals("(t ") || t.equals("(t("))
            {
                if(f)
                {
                    pos = i;
                    f = false;
                }
            }
        }
        if(pos != 0)
        {
            si = s.substring(p2+1, pos-2);
            si = cleanup(si);
            sino = s.substring(pos, s.length());
            sino = cleanup(sino);
            String[] condicionArray = detectSpaces(condicional);
            acu += ("\ncondicional : "+ condicional + "\n");
            if(Atom(condicionArray[1]) && Atom(condicionArray[2]))//chequeo de los valores del condicional si son numéricos 
            {
                if(compare(condicionArray[0], condicionArray[1], condicionArray[2]))
                {
                    //System.out.println("\nif: " + si);
                    //findcondition(si, hm);
                    acu += (si + " \n " +findcondition(si, hm));
                }
                else
                {
                    //System.out.println("\nelse: "+ sino+"\n");
                    acu += (sino + " \n " + findcondition(sino, hm));
                }
            }
            else
            {
                if((!Atom(condicionArray[1]) && !Atom(condicionArray[2])) && (!hm.containsKey(condicionArray[1])&& !hm.containsKey(condicionArray[2])))
                {
                    if(compare(condicionArray[0], condicionArray[1], condicionArray[2]))
                    {
                        /*System.out.println("\nif: " + si);
                        findcondition(si, hm);*/
                        acu += (si + " \n " +findcondition(si, hm));
                    }
                    else
                    {
                        /*System.out.println("\nelse: "+ sino+"\n");
                        findcondition(sino, hm);*/
                        acu += (sino + " \n " + findcondition(si, hm));
                    }

                }
                else if((!Atom(condicionArray[1]) && !Atom(condicionArray[2])) && (hm.containsKey(condicionArray[1])&& hm.containsKey(condicionArray[2])))
                {
                    if(compare(condicionArray[0], hm.get(condicionArray[1]).toString(), hm.get(condicionArray[2]).toString()))
                    {
                        /*System.out.println("\nif: " + si);
                        findcondition(si, hm);*/
                        acu += (si + " \n " +findcondition(si, hm));
                    }
                    else
                    {
                        /*System.out.println("\nelse: "+ sino+"\n");
                        findcondition(sino, hm);*/
                        acu += (sino + " \n " + findcondition(si, hm));
                    }
                }
                else
                {
                    if(!Atom(condicionArray[1]) && Atom(condicionArray[2]) && (hm.containsKey(condicionArray[1])))
                    {
                        if(compare(condicionArray[0], hm.get(condicionArray[1]).toString(), condicionArray[2]))
                        {
                            /*System.out.println("\nif: " + si);
                            findcondition(si, hm);*/
                            acu += (si + " \n " +findcondition(si, hm));
                        }
                        else
                        {
                            /*System.out.println("\nelse: "+ sino+"\n");
                            findcondition(sino, hm);*/
                            acu += (sino + " \n " + findcondition(sino, hm));
                        }
                    }
                    else if(Atom(condicionArray[1]) && !Atom(condicionArray[2]) && (hm.containsKey(condicionArray[2])))
                    {
                        if(compare(condicionArray[0], condicionArray[1], hm.get(condicionArray[2]).toString()))
                        {
                            /*System.out.println("\nif: " + si);
                            findcondition(si, hm);*/
                            acu += (si + " \n " +findcondition(si, hm));
                        }
                        else
                        {
                            /*System.out.println("\nelse: "+ sino+"\n");
                            findcondition(sino, hm);*/
                            acu += (sino + " \n " + findcondition(sino, hm));
                        }
                    }
                }
            }
        }
        else
        {
            acu += "";
        }
        return acu + " \n ";
    }


    
    /** 
     * función que determina la comparación que se debe llevar a cabo en función 
     * del signo encontrada en el condicional
     * @param a
     * @param b
     * @param c
     * @return boolean
     */
    public boolean compare(String a, Object b, Object c)
    {
        boolean temp = true;
        if(a.equals("="))
        {
            temp = igual(b, c);
            return temp;
        }
        else if(a.equals("<"))
        {
            temp = menor(b, c);
            return temp;
        }
        else 
        {
            temp = mayor(b, c);
            return temp;
        }
    }

    
    /** 
     * @param s
     */
    public void ReadCond(String s)
    {
        char[] c = new char[s.length()];
        for(int i = 0; i < s.length(); i++)
        {
            c[i] = s.charAt(i);
        }
    }



    
    /** 
     * Compara dos objetos para ver si estos son iguales
     * @param x
     * @param y
     * @return boolean
     */
    public boolean igual(Object x, Object y)
    {
        if(x.equals(y))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    
    /** 
     * Compara dos objetos para ver si el primero es mayor al segundo. En caso de no poderse convertirse a formato Double
     * entonces se arroja una excepción al respecto
     * @param x
     * @param y
     * @return boolean
     */
    public boolean mayor(Object x, Object y)
    {
        try 
        {
            double a = Double.parseDouble(x.toString());
            double b = Double.parseDouble(y.toString());
            if(a > b)
            {
                return true;
            }
            else
            {
                return false;
            }
        } 
        catch (Exception e) 
        {
            return false;
        }
    
    }

    
    /** 
     * Compara dos objetos para ver si el primero es menor al segundo. En caso de no poderse convertirse a formato Double
     * entonces se arroja una excepción al respecto
     * @param x
     * @param y
     * @return boolean
     */
    public boolean menor(Object x, Object y)
    {   
        try 
        {
            double a = Double.parseDouble(x.toString());
            double b = Double.parseDouble(y.toString());
            if(a < b)
            {
                return true;
            }
            else
            {
                return false;
            }
        } 
        catch (Exception e) 
        {
            return false;
        }
    }


    /** 
     * Función de tipo boolean que devuelve el valor verdadero o falso según la cantidad de paréntesis 
     * de apertura y cierre coincidan.
     * @param s
     * @return boolean
     */
    public boolean validateParenthesis(String s)
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
     * Limpieza de los espacios sobrantes 
     * @param s
     * @return String
     */
    public String cleanup(String s)
    {
        String temp  = "";
        if(validateParenthesis2(s) == 0)
        {
            temp = s;
        }
        else
        {
            temp = cleanup(s.substring(0, s.length() -1));
        }
        return temp;
    }
    


    /** 
     * Función de tipo boolean que devuelve el valor verdadero o falso según la cantidad de paréntesis 
     * de apertura y cierre coincidan.
     * @param s
     * @return boolean
     */
    public int validateParenthesis2(String s)
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

        return acu;
    }





    
    /** 
     * Detección de espacios y borrado de estos, regresa un arreglo con los elementos que no son espacios.
     * @param s
     * @return String[]
     */
    public String[] detectSpaces(String s) //void detectSpaces(String s)
    {
        ArrayList<String> data = new ArrayList<>();
        int acu = 0;
        char[] c = new char[s.length()];
        for(int i = 0; i < s.length(); i++)
        {
            c[i] = s.charAt(i);
        }
        boolean spacing = false;
        String temp = "";
        for(char i: c)
        {
            if(i == ' ' || i =='(' || i==')')
            { 
                spacing = true;
                if (temp != "")
                {
                    data.add(temp);
                    temp = "";
                }
            }
            else
            {
                spacing = false; 
            }
            if(!spacing)
            {
                temp += i;
            }
            if(i == c[c.length - 1])
            {
                if (temp != "")
                {
                    data.add(temp);
                    temp = "";
                }
            }
        }

        String[] instrucciones = new String[data.size()];
        for(int i = 0; i < data.size(); i++)
        {
            instrucciones[i] = data.get(i);
        }
        return instrucciones;
    }


    /** 
     * Método para checar si un término es numérico o
     * @param x
     * @return boolean
     */
    public boolean Atom(Object x)
    {
        String temp = x.toString();
        try 
        {
            if((Integer)Integer.parseInt(temp) instanceof Integer){
                return true;
            }
        } 
        catch (NumberFormatException noInt) 
        {
            try 
            {
                if((Float)Float.parseFloat(temp) instanceof Float)
                {
                    return true;
                }
            } 
            catch (NumberFormatException noFloat) 
            {
                try 
                {
                    if((Double)Double.parseDouble(temp) instanceof Double)
                    {
                        return true;
                    }
                } 
                catch (NumberFormatException noDouble) 
                {
                    return false;
                }
            }
        }
        return false;
    }
}
