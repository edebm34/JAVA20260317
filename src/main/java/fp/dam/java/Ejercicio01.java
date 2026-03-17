package fp.dam.java;

import java.util.Deque;
import java.util.LinkedList;

public class Ejercicio01 {

    public static void colapsar(Deque<Integer> pila) {
        Deque<Integer> aux = new LinkedList<>();

        while (!pila.isEmpty()) {
            int primero = pila.pop();
            if (!pila.isEmpty()) {
                int segundo = pila.pop();
                aux.push(primero + segundo);
            } else {
                aux.push(primero);
            }
        }

        while (!aux.isEmpty()) {
            pila.push(aux.pop());
        }
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
