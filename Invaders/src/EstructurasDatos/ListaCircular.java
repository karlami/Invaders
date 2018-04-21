
package EstructurasDatos;
/**
 * Estructura de datos, lista circular
 * @param <T> para generics
 */
public class ListaCircular <T> { 
    private NodeLista<T> head;
    private int capacidad;

    public ListaCircular() { head = null;
      capacidad = 0;
    }
    /**
     * Agrega un nodo a la lista
     * @param data para agregarlo a la lista con el dato
     */
    public void add(T data) {
      if (capacidad == 0) {
        NodeLista<T> aux = new NodeLista(data);
        head = aux;
        head.setNext(aux);
        capacidad += 1;
      } else {
        NodeLista<T> current = head;
        while (current.getNext() != head) {
          current = current.getNext();
        }
        NodeLista<T> aux = new NodeLista(data);
        aux.setNext(head);
        current.setNext(aux);
        capacidad += 1;
      }
    }
    /**
     * Elimina por posicion
     * @param posicion para verificar el nodo a eliminar
     */
    public void remove(int posicion)
    {
      int pos_aux = posicion;
      NodeLista<T> current = head;
      if (pos_aux == 0) {
        head = current.getNext();
        capacidad -= 1;
      } else if (pos_aux == capacidad - 1) {
        int a = 0;
        while (a != pos_aux) {
          current = current.getNext();
          a++;
        }
        current.setNext(null);
        capacidad -= 1;
      } else if (pos_aux >= size()) {
        System.out.println("No se encuentra el elemento");
      } else {
        int b = 0;
        while (pos_aux - 1 != b) {
          current = current.getNext();
          b++;
        }
        current.setNext(current.getNext().getNext());
        capacidad -= 1;
      }
    }
    /**
     * Toma el nodo en la posicion
     * @param i para la posicion
     * @return dato del nodo en la posicion indicada
     */
    public T get(int i) {
      NodeLista<T> aux = getHead();
      int x = 0;
      while (x != i) {
        aux = aux.getNext();
        x++;
      }
      if (aux == null) {
        System.out.println("El elemento no se encuentra en la lista");
        return null;
      }
      return aux.getData();
    }

    public NodeLista<T> getHead()
    {
      return head;
    }

    public void setHead(NodeLista<T> head) {
      this.head = head;
    }
    /**
     * Vacia la lista
     */
    public void clear() {
      head = null;
      capacidad = 0;
    }
      /**
       * Da el tamaño de la lista
       * @return tamaño
       */
    public int size()
    {
      return capacidad;
    }
    /**
     * verifica si la lista esta vacia
     * @return true o false si esta vacia
     */
    public boolean isEmpty()
    {
      if (capacidad == 0) {
        return true;
      }
      return false;
    }

    }
