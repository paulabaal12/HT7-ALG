/*
Clase referente a una clase Association<K,V>
*/
public class Association<V,K> {
    public V key;// Representa lallave
    public K value;// Representa el valor
    public Association(V KEY, K VALUE) {
        key = KEY;
        value = VALUE;
    }
    //getters y setters
    public V getKey() {
        return key;
    }
    public K getValue() {
        return value;
    }
    public void setKey(V key) {
        this.key = key; 
    }
    public void setValue(K value) {
        this.value = value;
    }


}
