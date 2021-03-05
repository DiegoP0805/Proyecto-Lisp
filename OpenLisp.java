/**
 * @author: Diego Perdomo
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class OpenLisp 
{
    /**
     * Constructor de la clase
     */
    public OpenLisp()
    {

    }   
    
    
    /** 
     * función que devuelve un arreglo de string que contiene las líneas del código
     * en lisp.
     * @param location
     * @return String[]
     */
    public String[] open(String location)
    {
        try 
        {
            File archivo = new File(location);
            BufferedReader lisp = new BufferedReader(new FileReader(archivo));
            String[] lineas = lisp.lines().toArray(String[]::new);
            lisp.close();
            if(validateParenthesis(lineas))
            {
                return lineas;
            }
            else
            {
                return null;
            }
        } 
        catch (Exception e) 
        {
            return null;
        }
    }


    
    /** 
     * función privada que sirve para checar que la cantidad de paréntesis de apertura 
     * y cierre coincidan.
     * @param s
     * @return boolean
     */
    private static boolean validateParenthesis(String[] s)
    {
        int acu = 0;
        for(int i = 0; i < s.length; i++)
        {
            for(int j = 0; j < s[i].length(); j++)
            {
                if((s[i].charAt(j)) == '(')
                {
                    acu++;
                }
                else if((s[i].charAt(j)) == ')')
                {
                    acu--;
                }
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
}
