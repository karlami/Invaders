
package EstructurasDatos;
/**
 * Estructura de datos, lista doble enlazada
 * @param <T> para generics
 */
public class ListaDoble <T> { 
    private NodeLista<T> head;
    private int capacidad;

    public ListaDoble() { 
      head = null;
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
        head.setNext(null);
        head.setPrev(null);
        capacidad += 1;
      } else {
        NodeLista<T> current = head;
        while (current.getNext() != null) {
          current = current.getNext();
        }
        NodeLista<T> aux = new NodeLista(data);
        aux.setNext(null);
        current.setNext(aux);
        aux.setPrev(current);
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
        NodeLista<T> aux = current.getNext().getNext();
        current.setNext(aux);
        if (aux == null) {
          capacidad -= 1;
        } else {
          aux.setPrev(current);


          capacidad -= 1;
        }
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
       * Toma el nodo en la posicion
       * @param i para la posicion
       * @return nodo en la posicion indicada
       */
    public NodeLista<T> getNode(int i) {
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
      return aux;
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
    
    /**
       * Agrega un nodo a la lista
       * @param pos para agregarlo en la posicion indicada
       * @param dato para agregarlo a la lista con el dato
       */ 
    public void agregar(int pos, T dato){
        if(this.isEmpty()){
            if(pos == 0){
                NodeLista<T> aux = new NodeLista(dato);
                this.head = aux;
                this.capacidad ++;
            }
            else{
                System.out.println("Posicion no valida");
            }
        }
        else{
            if(pos > this.capacidad){
                System.out.println("Posicion mayor al tamaño");
            }
            else if(pos == this.capacidad){
                this.add(dato);
            }
            else{
                NodeLista<T> aux = this.head;
                NodeLista<T> nuevo = new NodeLista(dato);
                if(pos == 0){
                    nuevo.setNext(aux);
                    aux.setPrev(nuevo);
                    this.head = nuevo;
                    capacidad++;
                }
                else{
                    int contador = 0;
                    while(contador < pos-1){
                        aux = aux.getNext();
                        contador++;
                    }
                    nuevo.setNext(aux.getNext());
                    aux.getNext().setPrev(nuevo);
                    nuevo.setPrev(aux);
                    aux.setNext(nuevo);
                    capacidad++;
                }
            }
        }
    }
    
    /**
     * Intercambia las posiciones de la lista
     * @param pos1 del elemento en posicion inicial a intercambiar
     * @param pos2 del elemento en posicion final a intercambiar
     */
    public void intercambiar(int pos1, int pos2){
        if(this.isEmpty())
            return;
        
        else{
            if(pos1 < this.capacidad && pos2 < this.capacidad){
                NodeLista<T> aux1 = this.getNode(pos1);
                NodeLista<T> aux2 = this.getNode(pos2);
                if(pos1 > pos2){
                    this.agregar(pos2, (T)aux1.getData());
                    this.agregar(pos1 + 1, (T)aux2.getData());
                    this.remove(pos2 + 1);
                    this.remove(pos1 + 1);
                }
                else{
                    this.agregar(pos1, (T)aux2.getData());
                    this.print();
                    this.agregar(pos2 + 1, (T)aux1.getData());
                    this.print();
                    this.remove(pos1 + 1);
                    this.print();
                    this.remove(pos2 + 1);
                    this.print();
                }
                
            }
            else{
                System.out.println("Verifique las posiciones");
            }
        }
    }
    
    public void print()
  {
    String fin = "[";
    NodeLista<T> aux = head;
    if (aux == null) {
      fin = fin + "]";
      System.out.println(fin);
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
    System.out.println(fin);
  }
    

}