package Sistema;

import uy.edu.um.prog2.adt.hashmap.MyHashTable;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Sistema.Sistema.topArtistByAppearance;
import static Sistema.Sistema.topSongsByDateCountry;


public class CSVLoader {
    public String path;

    public CSVLoader(String path) {
        this.path = path;
    }

    public void LoadCSV() {
        try (Scanner scanner = new Scanner(new File(path))) {
            // Start from second line
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Pattern regex = Pattern.compile("\"(?:[^\"\\\\]|\\\\.|\"\")*\"");
                Song song = createSong(regex, line);

                String country = song.getCountry();
                Date date = song.getDate();

                if (!topSongsByDateCountry.containsKey(date)) {
                    topSongsByDateCountry.put(date, new MyHashTable<>(50, 0.75f));
                }
                if (!topSongsByDateCountry.get(date).containsKey(country)) {
                    topSongsByDateCountry.get(date).put(country, new MyLinkedListImpl<>());
                }
                topSongsByDateCountry.get(date).get(country).add(song);

                if (!topArtistByAppearance.containsKey(date)) {
                    topArtistByAppearance.put(date, new MyHashTable<>(50, 0.75f));
                }
                for (String artist : song.getArtist()) {
                    if (!topArtistByAppearance.get(date).containsKey(artist)) {
                        topArtistByAppearance.get(date).put(artist, 0);
                    }
                    Integer count = topArtistByAppearance.get(date).remove(artist);
                    topArtistByAppearance.get(date).put(artist, count + 1);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Song createSong(Pattern regex, String line) {
        Matcher matcher = regex.matcher(line);
        MyList<String> attributes = new MyLinkedListImpl<>();  // attributes
        while (matcher.find()) {
            String attribute = matcher.group();
            attribute = attribute.substring(1, attribute.length() - 1).replace("\"\"", "\"");
            attributes.add(attribute);
        }
        // Get the attributes separated
        String spotifyId = attributes.get(0);
        String name = attributes.get(1);
        // Split the artist string into an array
        String[] artists = attributes.get(2).split(", ");
        int dailyRank = Integer.parseInt(attributes.get(3));
        int dailyMovement = Integer.parseInt(attributes.get(4));
        int weeklyMovement = Integer.parseInt(attributes.get(5));
        String country = attributes.get(6);
        Date snapshotDate = parseDate(attributes.get(7));
        int popularity = Integer.parseInt(attributes.get(8));
        boolean isExplicit = Boolean.parseBoolean(attributes.get(9));
        long duration = Long.parseLong(attributes.get(10));
        String albumName = attributes.get(11);
        Date albumReleaseDate = parseDate(attributes.get(12));
        float danceability = Float.parseFloat(attributes.get(13));
        float energy = Float.parseFloat(attributes.get(14));
        int key = Integer.parseInt(attributes.get(15));
        float loudness = Float.parseFloat(attributes.get(16));
        int mode = Integer.parseInt(attributes.get(17));
        float speechiness = Float.parseFloat(attributes.get(18));
        float acousticness = Float.parseFloat(attributes.get(19));
        float instrumentalness = Float.parseFloat(attributes.get(20));
        float liveness = Float.parseFloat(attributes.get(21));
        float valence = Float.parseFloat(attributes.get(22));
        float tempo = Float.parseFloat(attributes.get(23));
        int timeSignature = Integer.parseInt(attributes.get(24));
        // Return the song
        return new Song(spotifyId, name, artists, dailyRank, dailyMovement, weeklyMovement, country, snapshotDate, popularity, isExplicit, duration, albumName, albumReleaseDate, danceability, energy, key, loudness, mode, speechiness, acousticness, instrumentalness, liveness, valence, tempo, timeSignature);
    }
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
