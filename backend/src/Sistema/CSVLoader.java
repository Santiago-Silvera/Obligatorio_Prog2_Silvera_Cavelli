
package Sistema;

import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static Sistema.System.topSongsByDateCountry;


public class CSVLoader {
    public String path;

    public CSVLoader(String path) {
        this.path = path;
    }

    public void LoadCSV() {
        try (Scanner scanner = new Scanner(new File(path))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();  // Skip the header line
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern regex = Pattern.compile("\"(?:[^\"\\\\]|\\\\.|\"\")*\"");
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
        MyList<String> a = new MyLinkedListImpl<>();  // attributes
        while (matcher.find()) {
            String attribute = matcher.group();
            attribute = attribute.substring(1, attribute.length() - 1).replace("\"\"", "\"");
            a.add(attribute);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        try {
            return new Song(
                    a.get(0),  // spotifyId
                    a.get(1),  // name
                    a.get(2),  // artist
                    Integer.parseInt(a.get(3)),  // dailyRank
                    Integer.parseInt(a.get(4)),  // dailyMovement
                    Integer.parseInt(a.get(5)),  // weeklyMovement
                    a.get(6),  // country
                    dateFormat.parse(a.get(7)),  // snapshotDate
                    Integer.parseInt(a.get(8)),  // popularity
                    Boolean.parseBoolean(a.get(9)),  // isExplicit
                    Long.parseLong(a.get(10)),  // duration
                    a.get(11),  // albumName
                    dateFormat.parse(a.get(12)),  // albumReleaseDate
                    Float.parseFloat(a.get(13)),  // danceability
                    Float.parseFloat(a.get(14)),  // energy
                    Integer.parseInt(a.get(15)),  // key
                    Float.parseFloat(a.get(16)),  // loudness
                    Integer.parseInt(a.get(17)),  // mode
                    Float.parseFloat(a.get(18)),  // speechiness
                    Float.parseFloat(a.get(19)),  // acousticness
                    Float.parseFloat(a.get(20)),  // instrumentalness
                    Float.parseFloat(a.get(21)),  // liveness
                    Float.parseFloat(a.get(22)),  // valence
                    Float.parseFloat(a.get(23)),  // tempo
                    Integer.parseInt(a.get(24))   // timeSignature
            );
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date", e);
        }
    }
}
