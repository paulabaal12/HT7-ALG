//PAULA REBECA BARILLAS

/*
Obtenido  https://stackoverflow.com/questions/11263244/java-how-do-i-implement-a-generic-binary-search-tree
Arbol generico
*/
public class GenericBST<V extends Comparable<V>,K> {

    private class Node extends Association<V,K>{//clase de nodo que hereda de Association<k,v>
        Node left, right;
        public Node(V value , K transaltion) {
            super(value, transaltion);
        }
    }
    
    private Node root;//nodo raiz
    private int size;//tamanno del arbol
    
    public int getSize() {//obtener tamanno
        return size;
    }
    
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public GenericBST() {//constructor
        this.setRoot(null);
        this.size = 0;
    }
    
    public boolean isEmpty() {//metodo para ver si esta vacio
        if (getRoot() == null) return true;
        else return false;
    }
    
    public void insert(V value , K transaltion) {//insertar nodo
        size++;//aumentar tamano
        if (isEmpty()) setRoot(new Node(value, transaltion));//si es el primer nodo colocarlo como raiz, de lo contrario ir al metodo recursivo
        else insert(getRoot(), value, transaltion);
    }
    
    public boolean contains(V value) {//ver si tiene un valor
        return contains(getRoot(), value);
    }
    
    public void print() {//mostrar contenido del arbol
        print(getRoot());//metodo recursivo para imprimir
    }
    
    public K getValue(V key) {//devuelve el valor en el nodo con la llave
        return getValue(getRoot(), key);//metodo recursivo de busqueda que devuelve valor

    }
    
    public void delete(V value) {//elimitar nodo con llave
        setRoot(deleteRecursive(getRoot(),value));
    }

    private Node deleteRecursive(Node node, V value) {//metodo recursivo de eliminacion
        if (node == null){
            return null;
        }
        if(value.compareTo(node.key) == 0){//si es el nodo que se busca eliminar
            //si no tiene hijos
            if (node.left == null && node.right == null) {
                return null;
            }
            //con  1 hijo
            
            if (node.right == null) {
                return node.left;
            }

            if (node.left == null) {
                return node.right;
            }
            // en caso que tenga dos hijos

            Node smallestValue = findSmallestValue(node.right);//ontener nodo mas pequeno del lado derecho
            node.key = smallestValue.key; //asignar valor y llave del nodo menor al actual
            node.value = smallestValue.value;
            node.right = deleteRecursive(node.right, smallestValue.key);//colocar el nuevo nodo derecho entrando al metodo de eliminar
            return node;
        }if(value.compareTo(node.key) < 0){ // si la llave es mas pequenna que el nodo actual colocar los nuevos nodos del lado izquierdo
            node.left = deleteRecursive(node.left, value);
            return node;
        }
        node.right = deleteRecursive(node.right, value);//asignar nodo derecho al nodo 
        return node;
    }

    private Node findSmallestValue(Node node) {//metodo recursivo para hallar nodo menor
        return node.left == null? node : findSmallestValue(node.left);
    }
    
    
    private K getValue(Node node, V key) {//metodo recursivo para obtener el valor con la llave
        if (key.compareTo(node.key) == 0) {//si tiene la llave regresarlo
            return node.value;
        } else if (key.compareTo(node.key) < 0) {//compara si el valor es mas pequenno que la llave o mas grande , para recorrer ya sea la izquiera o derecha del nodo para seguir buscando
            if (node.left == null) return null;
            else return getValue(node.left, key);
        } else {
            if (node.right == null) return null;
            else return getValue(node.right, key);
        }

    }

    private boolean contains(Node node, V value) {//realiza lo mismo que el GetVlue, solo que devuelve un booleando para indicar si tiene o no la llave en algun nodo
        if (value.compareTo(node.key) == 0) {
            return true;
        } else if (value.compareTo(node.key) < 0) {
            if (node.left == null) return false;
            else return contains(node.left, value);
        } else {
            if (node.right == null) return false;
            else return contains(node.right, value);
        }
    }
    
    private void print(Node node) {//recoorer nodos y mostrarlos en Inorder
        if (getRoot() == null) return;
        if (node.left != null) print(node.left);
        System.out.println(node.key + ": " + node.value);
        if (node.right != null) print(node.right);
    }
    
    private void insert(Node node, V value, K transaltion) {//insercion recursiva
        if(value.compareTo(node.key) <= 0) { //compara si la llave es pequena o menor al nodo , de esa manera recorrer izquierda o derecha
            if(node.left == null) node.left = new Node(value, transaltion); //si no hay nodo en ese lado, insertarlo, de lo contrario aplicar el metodo recursivo con el nodo izquiero hallado
            else insert(node.left, value, transaltion);
        } else {
            if(node.right == null) node.right = new Node(value, transaltion);
            else insert(node.right, value , transaltion);
        }
    }
}
