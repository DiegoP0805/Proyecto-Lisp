/*******************************************************
* Universidad del Valle de Guatemala
* Algoritmos y Estructuras de Datos
* Profesor: Moises Gonzales
*@author Stefano Aragoni
*
********************************************************/

import java.util.Vector;

public class Stack<E> implements stackInterface<E>
{
	public Vector<E> data;

	/**
	 *Constructor method of stack class.
	*
	*@return Doesnt return a value.
	*
	*/
	public Stack()
	// post: constructs a new, empty stack
	{
		data = new Vector<E>();
	}

	/**
    * Metodo para meter al stack
    *
    * @param E Elemento generico que se mete al stack
    */
	public void push(E item)
	// post: the value is added to the stack
	// will be popped next if no intervening push
	{
		data.add(item);
	}

	/**
    * Metodo para sacar el articulo mas reciente
    * @return devuelve el item más reciente que se metio en el stack
    */
	public E pop()
	// pre: stack is not empty
	// post: most recently pushed item is removed and returned
	{
		E temp = data.get(size() - 1);
		data.remove(size()-1);
		return temp;
	}

	/**
    * Metodo revisar el articulo mas reciente
    * @return devuelve el item más reciente que se metio en el stack, sin borrarlo
    */
	public E peek()
	// pre: stack is not empty
	// post: top value (next to be popped) is returned
	{
		return data.get(size() - 1);
	}
	
	/**
    * Metodo revisar el tamaño del stack
    * @return el tamaño del stack
    */
	public int size()
	// post: returns the number of elements in the stack
	{
		return data.size();
	}

	/**
    * Metodo para ver si hay algo en el stack
    * @return boolean si esta vacio o no
    */
	public boolean empty()
	// post: returns true if and only if the stack is empty
	{
		return size() == 0;
	}
	}