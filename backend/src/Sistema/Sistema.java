package Sistema;

import uy.edu.um.prog2.adt.hashmap.MyHash;
import uy.edu.um.prog2.adt.hashmap.MyHashTable;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;
import uy.edu.um.prog2.adt.linkedlist.NodeWithKeyValue;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Sistema {
    public static MyHash<Date, MyHash<String, MyList<Song>>> topSongsByDateCountry = new MyHashTable<>(50, 0.75f);
    public static MyHash<Date, MyHash<String, Integer>> topArtistByAppearance = new MyHashTable<>(50, 0.75f);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CSVLoader loader = new CSVLoader("C:\\Users\\santi\\Downloads\\universal_top_spotify_songs.csv");
        loader.LoadCSV();
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed loading CSV: " + (endTime - startTime)/1000 + "s");
        while (menu()) {}
    }

    public static boolean menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de consulta de canciones de Spotify \n" +
                "Ingrese el número de la consulta que desea realizar: \n" +
                " 1. Top 10 canciones en un país en un día dado. \n" +
                " 2. Top 5 canciones que aparecen en más top 50 en un día dado. \n" +
                " 3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado. \n" +
                " 4. Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada. \n" +
                " 5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas. \n" +
                " 6. Salir del sistema. ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD");
                String s = scanner.next();
                Date date = CSVLoader.parseDate(s);
                System.out.println("Ingrese el país");
                String country = scanner.next();
                MyList<Song> top = consulta1(date, country);
                if (top == null) {
                    System.out.println("No data for " + date + " in " + country);
                    return true;
                }
                for (Song song : top) {
                    System.out.println(song.getName() + " by " + Arrays.toString(song.getArtist()));
                }
                break;
            case 2:
                System.out.println("Sorry bro, no la hice todavia.");
                break;
            case 3:
                System.out.println("Ingrese la fecha inicial en formato YYYY-MM-DD");
                Date fechaInicial = CSVLoader.parseDate(scanner.next());
                System.out.println("Ingrese la fecha final en formato YYYY-MM-DD");
                Date fechaFinal = CSVLoader.parseDate(scanner.next());
                String[] topArtists = consulta3(fechaInicial, fechaFinal);
                for (String artist : topArtists) {
                    System.out.println(artist);
                }
                break;
            case 4:
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD");
                date = CSVLoader.parseDate(scanner.next());
                System.out.println("Ingrese el país");
                country = scanner.next();
                System.out.println("Ingrese el artista");
                String artist = scanner.next();
                int appearances = consulta4(date, country, artist);
                System.out.println(artist + " aparece " + appearances + " veces en " + country + " el " + date);
                break;
            case 5:
                System.out.println("Sorry bro, no la hice todavia.");
                break;
            case 6:
                System.out.println("Gracias por usar el sistema de consulta de canciones de Spotify");
                return false;
            default:
                System.out.println("Opción inválida");
                break;
        }
        return true;
    }

    public static MyList<Song> consulta1(Date date, String country) {
        /*
         Top 10 canciones en un país en un día dado. Este reporte debe incluir el nombre de
         la canción, el artista, y en qué puesto se encuentra en el top. Las canciones deben
         estar ordenadas de manera descendente. El día será ingresado en el formato
         YYYY-MM-DD.
        */
        MyList<Song> top;
        try {
            top = topSongsByDateCountry.get(date).get(country);
        } catch (NullPointerException e) {
            return null;
        }
        if (top == null) {
            return null;
        }
        MyList<Song> result = new MyLinkedListImpl<>();
        for (int i = 0; i < 10; i++) {
            result.add(top.get(i));
        }
        return result;
    }

    public static void consulta2() {
        /*
         Top 5 canciones que aparecen en más top 50 en un día dado. Las canciones deben
         estar ordenadas de manera descendente. Se espera que esta operación sea de
         orden n en notación Big O.
        */
        return;
    }

    public static String[] consulta3(Date fechaInicial, Date fechaFinal) {
        /*
         Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado. Cada
         aparición (como cada canción) distinta debe contarse, y se debe separar las
         canciones que tengan más de un artista contabilizando una aparición para cada uno.
        */
        if (fechaInicial.after(fechaFinal)) {
            System.out.println("Fecha inicial debe ser anterior a fecha final");
            return null;
        }
        String[] result = new String[7];
        for (; fechaInicial.before(fechaFinal); fechaInicial.setTime(fechaInicial.getTime() + 86400000)) {
            NodeWithKeyValue<String, Integer>[] topTable = topArtistByAppearance.get(fechaInicial).getTable();
            for (NodeWithKeyValue<String, Integer> node : topTable) {
                if (node != null) {
                    // Add the node in order based on appearences (biggest counter first)
                    for (int i = 0; i < 7; i++) {
                        if (result[i] == null) {
                            result[i] = node.getKey();
                            break;
                        }
                        if (topArtistByAppearance.get(fechaInicial).get(result[i]) < node.getValue()) {
                            result[i] = node.getKey();
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static int consulta4(Date date, String country, String artist) {
        /*
         Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.
        */
        MyList<Song> top = topSongsByDateCountry.get(date).get(country);
        int counter = 0;
        for (Song song : top) {
            // if artist is in the artist array increase counter
            if (Arrays.asList(song.getArtist()).contains(artist)) counter++;
        }
        return counter;
    }

    public static void consulta5() {
        /* 
         Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.
        */
        return;
    }
}
