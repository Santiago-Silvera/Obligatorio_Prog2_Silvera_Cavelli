package Sistema;

import uy.edu.um.prog2.adt.hashmap.MyHash;
import uy.edu.um.prog2.adt.hashmap.MyHashTable;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.util.Date;
import java.util.Objects;

public class System {
    public static MyHash<DateCountryPair, MyList<Song>> topSongsByDateCountry = new MyHashTable<>(50, 0.75f);

    public static void main(String[] args) {
        CSVLoader loader = new CSVLoader("C:\\Users\\santi\\Downloads\\universal_top_spotify_songs.csv");
        loader.LoadCSV();
    }

    public static MyList<Song> consulta1(Date date, String country) {
        /*
         Top 10 canciones en un país en un día dado. Este reporte debe incluir el nombre de
         la canción, el artista, y en qué puesto se encuentra en el top. Las canciones deben
         estar ordenadas de manera descendente. El día será ingresado en el formato
         YYYY-MM-DD.
        */
        MyList<Song> top = topSongsByDateCountry.get(new DateCountryPair(date, country));
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

    public static int consulta4(Date date, String country, String artist) {
        /*
         Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.
        */
        MyList<Song> top = topSongsByDateCountry.get(new DateCountryPair(date, country));
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
