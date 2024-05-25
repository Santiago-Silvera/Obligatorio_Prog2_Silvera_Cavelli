package Sistema;

import java.io.*;
import java.util.Scanner;
import uy.edu.um.prog2.adt.tree.SearchBinaryTreeImpl;
import uy.edu.um.prog2.adt.tree.BinaryTree;

public class CSVLoader {
    public String path;
    private final File file = new File(path);
    private final Scanner scanner = new Scanner(file);

    public CSVLoader(String path) throws FileNotFoundException {
        this.path = path;
    }

    public BinaryTree<String> loadComplete() {
        // Vamos a usar un arbol de busqueda por ahora pq no quiero pensar q es lo que quiero implementar todavia
        BinaryTree<String> datos = new SearchBinaryTreeImpl<>();
        while (scanner.hasNextLine()) {
            String record = scanner.nextLine();
            datos.add(record);
        }
        return datos;
    }


}
