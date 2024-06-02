package Sistema;

import uy.edu.um.prog2.adt.hashmap.MyHashTable;
import uy.edu.um.prog2.adt.hashmap.MyHash;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Sistema.System.topSongsByDateCountry;


public class CSVLoader {
    public String path;

    public CSVLoader(String path) {
        this.path = path;
    }

    public void LoadCSV() {
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern regex = Pattern.compile("\"(?:[^\"\\\\]|\\\\.|\"\")*\"");   // magiaGPT
                Song song = getSong(regex, line);

                String country = song.getCountry();
                Date date = song.getDate();
                DateCountryPair DCpair = new DateCountryPair(date, country);
                MyList<Song> songVector;

                if (topSongsByDateCountry.containsKey(DCpair)) {
                    songVector = topSongsByDateCountry.remove(DCpair);
                } else {
                    songVector = new MyLinkedListImpl<>();
                }
                songVector.add(song);
                topSongsByDateCountry.put(DCpair, songVector);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Song getSong(Pattern regex, String line) {
        Matcher matcher = regex.matcher(line);
        // Create the song
        MyList<String> attributes = new MyLinkedListImpl<>();
        while (matcher.find()) {
            // Remove the surrounding quotes
            String attribute = matcher.group();
            attribute = attribute.substring(1, attribute.length() - 1).replace("\"\"", "\"");
            attributes.add(attribute);
        }
        return new Song(attributes);
    }
}
