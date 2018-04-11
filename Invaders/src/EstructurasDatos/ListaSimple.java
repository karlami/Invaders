
package EstructurasDatos;

public class ListaSimple <T> { private NodeLista<T> head;
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
   * Elimina el nodo de una lista
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
  public void delete(T dato) {
    int pos_aux = 0;
    NodeLista<T> current = head;
    if (current.getData() == dato) {
      remove(pos_aux);
    } else {
      current = current.getNext();
      pos_aux++;
      int x = 0;
      while ((current != null) && (current.getData() != dato) && (capacidad >= x)) {
        current = current.getNext();
        pos_aux++;
        x++;
      }
      if (current == null) {
        System.out.println("El elemento no se encuentra en la lista");
      } else {
        remove(pos_aux);
      }
    }
  }
  /**
   * Devuelve el nodo en la posicion de la lista a elegir
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
   * Busca el nodo que contenga el dato del buscado
   * @param i que contiene el valor del nodo a buscar
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
  
  public void clear() {
    head = null;
    capacidad = 0;
  }
  
  public int getSize()
  {
    return capacidad;
  }
  /**
   * Busca en la lista el nodo que corresponda al dato ingresado
   * @param dato para indicar el dato del nodo a buscar
   * @return dato que se requiere
   */
  public T find(T dato) {
    NodeLista<T> aux = head;
    while (dato != aux.getData()) {
      aux = aux.getNext();
    }
    return aux.getData();
  }
  
  public boolean isEmpty()
  {
    if (capacidad == 0) {
      return true;
    }
    return false;
  }
  /**
   * Imprime en pantalla la lista con los nodos entre []
   */
  public void print()
  {
    String fin = "[";
    NodeLista<T> aux = head;
    if (aux == null) {
      fin = fin + "]";
      
    }
    while (aux.getNext() != null) {
      if (aux.getNext() == null) {
        fin = fin + aux.getData().toString();
        aux = aux.getNext();
      } else {
        fin = fin + aux.getData().toString() + ",";
        aux = aux.getNext();
      }
    }
    fin = fin + aux.getData().toString() + "]";
    System.out.println(fin) ;
  }
  

/**
 * Cambia las posiciones de los nodos requeridos
 * @param pos1 indica la posicion del primer nodo a intercambiar con el segundo
 * @param pos2 indica la posicion del segundo nodo a intercambiar con el primero
 */
  public void swap(int pos1, int pos2)
  {
    T i = get(pos1);
    T j = get(pos2);
    getNode(pos1).setData(j);
    getNode(pos2).setData(i);
  }
  
  public void setDATA(T data, int pos)
  {
    getNode(pos).setData(data);
  }
}
