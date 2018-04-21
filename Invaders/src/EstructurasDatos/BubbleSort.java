
package EstructurasDatos;

public class BubbleSort {
  public BubbleSort() {}
  
  /**
   * Crea una copia de la lista, y llama al metodo privado para recursividad
   * @param lista para trabaja con una copia de la lista
   */
  public static void sort(ListaSimple<Integer> lista)
  {
      ListaSimple<Integer> list = lista;
      BubbleSortAuxiliar(list, lista.getSize() - 1);
  }
  private static void BubbleSortAuxiliar(ListaSimple<Integer> list, int amount){
      for (int out = amount; out > 0; out--) {
        for (int in = 0; in < out; in++) {
          if ((list.get(in)) < (list.get(in + 1))) {
            list.swap(in, in + 1);
          }
        }
      }
    }
  }
