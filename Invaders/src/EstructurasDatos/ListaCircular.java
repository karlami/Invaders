
package EstructurasDatos;

public class ListaCircular <T> { 
  private NodeLista<T> head;
  private int capacidad;
  
  public ListaCircular() { head = null;
    capacidad = 0;
  }
  
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
  
  public void clear() {
    head = null;
    capacidad = 0;
  }
  
  public int size()
  {
    return capacidad;
  }
  
  public boolean isEmpty()
  {
    if (capacidad == 0) {
      return true;
    }
    return false;
  }
  
  public String print()
  {
    String fin = "[";
    NodeLista<T> aux = head;
    if (aux == null) {
      fin = fin + "]";
      return fin;
    }
    int a = 0;
    while (a != capacidad) {
      int b = a;
      if (b++ == capacidad - 1) {
        fin = fin + aux.getData().toString();
        aux = aux.getNext();
        a++;
      } else {
        fin = fin + aux.getData().toString() + ",";
        aux = aux.getNext();
        a++;
      }
    }
    fin = fin + "]";
    return fin;
  }
}
