package Sistema;

import uy.edu.um.prog2.adt.hashmap.MyHash;
import uy.edu.um.prog2.adt.hashmap.MyHashTable;
import uy.edu.um.prog2.adt.linkedlist.MyList;

public class System {
    public static MyHash<DateCountryPair, MyList<Song>> topSongsByDateCountry = new MyHashTable<>(50, 0.75f);

    public static void main(String[] args) {
        CSVLoader loader = new CSVLoader("C:\\Users\\santi\\IdeaProjects\\obligatorio\\universal_top_spotify_songs.csv");
        loader.LoadCSV();
    }
}
