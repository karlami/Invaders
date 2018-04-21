
package EstructurasDatos;

public class BubbleSort {
  public BubbleSort() {}

  public static void sort(ListaSimple<Integer> lista)
  {
      ListaSimple<Integer> list = lista;
      BubbleSortAuxiliar(list, lista.getSize() - 1);
  }
  
  private static void BubbleSortAuxiliar(ListaSimple<Integer> list, int amount){
      for (int out = amount; out > 0; out--) {
        for (int in = 0; in < out; in++) {
          if (((Integer)list.get(in)).intValue() < ((Integer)list.get(in + 1)).intValue()) {
            list.swap(in, in + 1);
          }
        }
      }
    }
  }
