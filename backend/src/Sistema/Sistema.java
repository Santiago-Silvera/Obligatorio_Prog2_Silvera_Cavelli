package Sistema;

import uy.edu.um.prog2.adt.hashmap.MyHash;
import uy.edu.um.prog2.adt.hashmap.MyHashTable;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.util.Objects;
import java.util.Scanner;

public class Sistema {
    public static MyHash<String, MyHash<String, MyList<Song>>> topSongsByDateCountry = new MyHashTable<>(50, 0.75f);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CSVLoader loader = new CSVLoader("C:\\Users\\santi\\Downloads\\universal_top_spotify_songs.csv");
        loader.LoadCSV();
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed loading CSV: " + (endTime - startTime)/1000 + "s");
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de consulta de canciones de Spotify \n Ingrese el número de la consulta que desea realizar: \n 1. Top 10 canciones en un país en un día dado. \n 2. Top 5 canciones que aparecen en más top 50 en un día dado. \n 3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado. \n 4. Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada. \n 5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD");
                String date = scanner.next();
                System.out.println("Ingrese el país");
                String country = scanner.next();
                MyList<Song> top = consulta1(date, country);
                if (top == null) return;
                for (Song song : top) {
                    System.out.println(song.getName() + " by " + song.getArtist());
                }
                break;
            case 2:
                System.out.println("Sorry bro, no la hice todavia.");
                break;
            case 3:
                System.out.println("Sorry bro, no la hice todavia.");
                break;
            case 4:
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD");
                date = scanner.next();
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
        }
    }

    public static MyList<Song> consulta1(String date, String country) {
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
            System.out.println("No data for " + date + " in " + country);
            return null;
        }
        if (top == null) {
            System.out.println("No data for " + date + " in " + country);
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

    public static void consulta3() {
        /*
         Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado. Cada
         aparición (como cada canción) distinta debe contarse, y se debe separar las
         canciones que tengan más de un artista contabilizando una aparición para cada uno.
        */
    }

    public static int consulta4(String date, String country, String artist) {
        /*
         Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.
        */
        MyList<Song> top = topSongsByDateCountry.get(date).get(country);
        int counter = 0;
        for (Song song : top) {
            if (Objects.equals(song.getArtist(), artist)) counter++;
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
