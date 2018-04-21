
package EstructurasDatos;
/**
 * Estructura de datos, lista simple enlazada
 * @param <T> para generics
 */
public class ListaSimple <T> { 
    private NodeLista<T> head;
    private int capacidad;
  
    public ListaSimple() { 
      head = null;
      capacidad = 0;
    }
    /**
     * Agrega un nuevo nodo al final de la lista existente, o al principio si esta vacia
     * @param data para darle el valor al nuevo nodo a agregar
     */
    public void add(T data) {
      if (capacidad == 0) {
        NodeLista<T> aux = new NodeLista(data);
        head = aux;
        head.setNext(null);
        capacidad += 1;
      } else {
        NodeLista<T> current = head;
        while (current.getNext() != null) {
          current = current.getNext();
        }
        NodeLista<T> aux = new NodeLista(data);
        aux.setNext(null);
        current.setNext(aux);
        capacidad += 1;
      }
    }
    /**
     * Elimina el nodo de una lista por posicion
     * @param posicion para indicar el nodo que se quiere eliminar
     */
    public void remove(int posicion)
    {
      int pos_aux = posicion;
      NodeLista<T> current = head;
      if (pos_aux == 0) {
        head = current.getNext();
        capacidad -= 1;
      } else if (pos_aux == capacidad - 1) {
        while (current.getNext().getNext() != null) {
          current = current.getNext();
        }
        current.setNext(null);
        capacidad -= 1;
      } else if (pos_aux >= getSize()) {
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
     * Elimina el nodo de una lista que corresponda al dato
     * @param dato para indicar el dato que se quiere eliminar de la lista
     */

    /**
     * Devuelve el dato del en la posicion de la lista a elegir
     * @param i para indicar la posicion de la lista a devolver el dato del nodo
     * @return el valor del nodo en la posicion indicada
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
    /**
     * Obtiene el nodo segun la referencia
     * @param i indice de busqueda
     * @return el nodo que coincide con el requerido
     */
    public NodeLista<T> getNode(int i)
    {
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
      System.out.println(aux);
      return aux;
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
    public int getSize()
    {
      return capacidad;
    }
      /**
       * Verifica si la lista esta vacia
       * @return true o false si esta vacia
       */
    public boolean isEmpty()
    {
      if (capacidad == 0) {
        return true;
      }
      return false;
    }

    public void setDATA(T data, int pos)
    {
      getNode(pos).setData(data);
    }
    
    public void swap(int i1, int i2){
        T i = get(i1);
        T j = get(i2);
        getNode(i1).setData(j);
        getNode(i2).setData(i);
  }
    
  }
