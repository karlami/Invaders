
package EstructurasDatos;

public class NodeLista <T> {
  private NodeLista<T> next;
  private T data;
  private NodeLista<T> prev;
/**
 * Elemento basico para las listas, contiene el dato y referencia al siguiente y anterior
 * segun lista lo requiera
 * @param dato que contiene el objeto a guardar en la lista
 */ 
  public NodeLista(T dato) {
    data = dato;
    next = null;
  }
  
  public T getData() {
    return data;
  }
  
  public void setData(T data) {
    this.data = data;
  }
  
  public NodeLista<T> getNext() {
    return next;
  }
  
  public void setNext(NodeLista<T> next) {
    this.next = next;
  }
  
  public NodeLista<T> getPrev() {
    return prev;
  }
  
  public void setPrev(NodeLista<T> prev) {
    this.prev = prev;
  }
}