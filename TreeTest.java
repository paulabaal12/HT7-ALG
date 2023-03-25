//PAULA BARILLAS #22764
import static org.junit.Assert.assertEquals;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

import org.junit.Test;

public class TreeTest {
    @Test
    public void searchTest(){//prueba de isnercion y busqueda, 
        GenericBST English = new GenericBST();
        try {
            String directory = "D:HT7-ALG\\diccionario.txt";//directorio del archivo en mi pc
            BufferedReader reader = new BufferedReader(new FileReader(directory));
            String line;
            while ((line = reader.readLine()) != null){//agregar al diccionario correspondiente
                line = line.replace(",", " ");
                String[] parts = line.split("\\s");
                English.insert(parts[0].strip().toLowerCase(), parts[1].toLowerCase());
                
            }
            
        }catch (Exception e) {
            System.out.println("Error inesperado, cerrando sesion");
            System.exit(1);
        }

        boolean result = English.contains("woman"); //se conoce que posee esta llave
        assertEquals(result, true);
        boolean result2 = English.contains("yes"); //se conoce que posee esta llave
        assertEquals(result2, true);
        boolean result3 = English.contains("egg");
        assertEquals(result3, false);
    }
    @Test
    public void getValueTest() {//test al metodo para obtener la traduccion para la llave
        GenericBST English = new GenericBST();
        English.insert("woman", "mujer");
        English.insert("yes", "si");
        English.insert("egg", "huevo");
        English.insert("up", "arriba");
        assertEquals((String)English.getValue("woman"), "mujer");
        assertEquals((String)English.getValue("yes"), "si");
        assertEquals((String)English.getValue("egg"), "huevo");
        assertEquals((String)English.getValue("up"), "arriba");
        assertEquals(English.getValue("down"), null); //si se solicita una llave inexistente debe de devolver null

    }
    @Test
    public void deleteTest() {//Metodo de eliminar valor
        GenericBST English = new GenericBST();
        English.insert("woman", "mujer");
        English.insert("yes", "si");
        English.delete("yes");
        assertEquals(English.contains("yes"), false); //al ser eliminado debe de indicar que ya no lo tiene
    }
    
}
import static org.junit.Assert.assertEquals;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

import org.junit.Test;

public class TreeTest {
    @Test
    public void searchTest(){//prueba de isnercion y busqueda, 
        GenericBST English = new GenericBST();
        try {
            String directory = "D:\\Universidad\\SEMESTRE 3\\Algoritmos y Datos\\HOJA7\\diccionario.txt";//directorio del archivo en mi pc
            BufferedReader reader = new BufferedReader(new FileReader(directory));
            String line;
            while ((line = reader.readLine()) != null){//agregar al diccionario correspondiente
                line = line.replace(",", " ");
                String[] parts = line.split("\\s");
                English.insert(parts[0].strip().toLowerCase(), parts[1].toLowerCase());
                
            }
            
        }catch (Exception e) {
            System.out.println("Error inesperado, cerrando sesion");
            System.exit(1);
        }

        boolean result = English.contains("woman"); //se conoce que posee esta llave
        assertEquals(result, true);
        boolean result2 = English.contains("yes"); //se conoce que posee esta llave
        assertEquals(result2, true);
        boolean result3 = English.contains("egg");
        assertEquals(result3, false);
    }
    @Test
    public void getValueTest() {//test al metodo para obtener la traduccion para la llave
        GenericBST English = new GenericBST();
        English.insert("woman", "mujer");
        English.insert("yes", "si");
        English.insert("egg", "huevo");
        English.insert("up", "arriba");
        assertEquals((String)English.getValue("woman"), "mujer");
        assertEquals((String)English.getValue("yes"), "si");
        assertEquals((String)English.getValue("egg"), "huevo");
        assertEquals((String)English.getValue("up"), "arriba");
        assertEquals(English.getValue("down"), null); //si se solicita una llave inexistente debe de devolver null

    }
    @Test
    public void deleteTest() {//Metodo de eliminar valor
        GenericBST English = new GenericBST();
        English.insert("woman", "mujer");
        English.insert("yes", "si");
        English.delete("yes");
        assertEquals(English.contains("yes"), false); //al ser eliminado debe de indicar que ya no lo tiene
    }
    
}
/* */