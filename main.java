import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main{
    public static void main(String[] args) {
        GenericBST English = new GenericBST();
        GenericBST French = new GenericBST();
        boolean translate = false;
        int mode = -1; // -1 sin idioma definido, 0 English,  1 French
        //leet el archivo 
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Ingrese el archivo con los datos");
            String directory = scanner.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader(directory));
            String line;
            while ((line = reader.readLine()) != null){//agregar al diccionario correspondiente
                line = line.replace(",", " ");
                String[] parts = line.split("\\s");
                English.insert(parts[0].strip().toLowerCase(), parts[1].toLowerCase());
                French.insert(parts[2].strip().toLowerCase(), parts[1].toLowerCase());
            }
            translate = true;
        }catch (Exception e) {
            System.out.println("Error inesperado, cerrando sesion");
            System.exit(1);
        }
        
        if(translate){
        Scanner op = new Scanner(System.in);
        while (true) {
            System.out.println("1. Traducir archivo");
            System.out.println("2. Editar traduccion de palabra");
            System.out.println("3. Borrar palabra de dicconario"); 
            System.out.println("4. Salir");
            System.out.println("5. Mostrar contenido de diccionarios");
            System.out.println("6. Traducir una palabra (Ingresela en inglés o francés)");
            try {
                switch(op.nextInt()){
                    case 1 ->{
                        System.out.println("ingrese direccion de archivo");
                        Scanner scanner = new Scanner(System.in);
                        try{
                            String file = scanner.nextLine();
                            BufferedReader reader = new BufferedReader(new FileReader(file));
                            String line;
                            ArrayList<String> notfoundTokens = new ArrayList<String>();//ArrayList con las palabras no encontradas en los diccionarios
                            while ((line = reader.readLine()) != null){
                                line = removePunctuations(line);
                                String[] tokens = line.split(" ");
                                ArrayList<String> tokensList = new ArrayList<String>();//ArrayList con los resultados de traduccion
                                for(String word: tokens){
                                    if(English.contains(word.toLowerCase())){
                                        mode = 0;
                                    }
                                    if(French.contains(word.toLowerCase())){
                                        mode = 1;
                                    }
                                }
                                switch(mode){ //segun el idioma detectado se traducira
                                    case 0 -> {
                                        for(String word: tokens){ //recorre el array
                                            if(English.contains(word.toLowerCase())){
                                                tokensList.add((String) English.getValue(word.toLowerCase()));
                                            }else{//si no esta en el diccionario, colocarlo entre * * em e; array de resultado y agregarlo al array de palabras no encontradas
                                                notfoundTokens.add(word);
                                                tokensList.add("*" + word + "*");
                                            }
                                        }
                                        String transaltion = "";
                                        for(String word : tokensList){
                                            transaltion = transaltion + " " + word;
                                        }
                                        System.out.println(transaltion);
                                    }
                                    case 1 -> {//realiza lo mismo que el case 0 solo que con el frances
                                        for(String word: tokens){
                                            if(French.contains(word.toLowerCase())){
                                                tokensList.add((String) French.getValue(word.toLowerCase()));
                                            }else{
                                                notfoundTokens.add(word);
                                                tokensList.add("*" + word + "*");
                                            }
                                        }
                                        String transaltion = "";//mostrar el resultado de la traduccion
                                        for(String word : tokensList){
                                            transaltion = transaltion + " " + word;
                                        }
                                        System.out.println(transaltion);

                                    }
                                    case -1 ->{//si no se detecta idioma, mostrarlo
                                        System.out.println("no se reconoce el idioma");
                                        break;
                                    }
                                }
                            }
                            for(String newword : notfoundTokens){//agregar las palabras sin significado a los diccionarios se agrega segun el idioma detectado en la traduccion
                                System.out.println("agregar significado para " + newword);
                                String meaning =  scanner.nextLine();
                                switch(mode){
                                    case 0->{
                                        English.insert(newword.toLowerCase(), meaning.toLowerCase());
                                    }
                                    case 1->{
                                        French.insert(newword.toLowerCase(), meaning.toLowerCase());
                                    }
                                }
                            }
                        }
                        catch(FileNotFoundException e){//catch de no encontrar el archivo y otro para cualquier error
                            System.out.println("file not found");
                            scanner.next();
                        }
                        catch (Exception e){
                            System.out.println("unexpected error");
                            scanner.next();
                        }
                        
                    }
                    case 2 ->{//editar significado
                        System.out.println("seleccione el idioma a editar");
                        System.out.println("1. English");
                        System.out.println("2. Français");
                        try { 
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("ingrese palabra");
                            String word = scanner.nextLine();
                            switch(op.nextInt()){
                                
                                case 1 ->{//segun el diccionario seleccionado deditar
                                    
                                    if(English.contains(word.toLowerCase())){//verificar que exista
                                        System.out.println("ingrese nuevo significado");
                                        String newmeanings = scanner.nextLine();
                                        English.delete(word.toLowerCase()); //remover nodo con significado anterior
                                        English.insert(word.toLowerCase(), newmeanings.toLowerCase()); //insertar nuevo nodo
                                        
                                    }else{
                                        System.out.println("word not found");
                                    }
                                }
                                case 2 ->{//realiza lo mismo que el case 1 solo que con el frances
                                    
                                    if(French.contains(word.toLowerCase())){
                                        System.out.println("ingrese nuevo significado");
                                        String newmeanings = scanner.nextLine();
                                        French.delete(word.toLowerCase());
                                        French.insert(word.toLowerCase(), newmeanings.toLowerCase());
                                    }else{
                                        System.out.println("word not found");
                                    }
                                }
                            }

                        }catch(InputMismatchException ie){
                            System.out.println("entrada invalida");
                            op.next();
                        }
                    }
                    case 3 ->{//borrar palabra
                        System.out.println("ingrese la palabra a borrar");
                        Scanner scanner = new Scanner(System.in);
                        String word = scanner.nextLine();
                            if(English.contains(word.toLowerCase())){//detectar en que diccionario existe
                                English.delete(word.toLowerCase()); //borrar palabra
                                System.out.println("deleted");
                            }else if(French.contains(word.toLowerCase())){
                                French.delete(word.toLowerCase()); //borrar palabra
                                System.out.println("deleted");
                            }else{
                                System.out.println("word not found");
                            }
                    }
                    case 4->{ // salir del programa
                        System.exit(0);
                    }
                    case 5->{ //mostrar contenido en INORDER
                        System.out.println("Diccionario en Inglés");
                        English.print();
                        System.out.println("\n Diccionario en Francés");
                        French.print();
                        System.out.println();
                    }
                    case 6 ->{//traducir una sola palabra
                        System.out.println("ingrese palabra");
                        Scanner scanner = new Scanner(System.in);
                        String word = scanner.nextLine();
                        if(English.contains(word.toLowerCase())){//detectar si esta en un diccionario y devolver el significado
                            System.out.println(English.getValue(word.toLowerCase()));
                        }
                        if(French.contains(word.toLowerCase())){
                            System.out.println(French.getValue(word.toLowerCase()));
                        }

                    }
                }
            } catch (InputMismatchException e){//se ingresa un caracter que no sea un int
                System.out.println("Entrada no valida");
                op.next();
            }
            
            
        }
    } 
}
public static String removePunctuations(String source) {
    return source.replaceAll("\\p{Punct}", "");
}
}

/* */