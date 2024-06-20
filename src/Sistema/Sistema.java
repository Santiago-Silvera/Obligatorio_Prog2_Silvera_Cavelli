package Sistema;

import lombok.Getter;
import uy.edu.um.prog2.adt.hashmap.MyHash;
import uy.edu.um.prog2.adt.hashmap.MyHashTable;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.heap.MyHeapImpl;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.prog2.adt.linkedlist.MyList;
import uy.edu.um.prog2.adt.linkedlist.NodeWithKeyValue;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Sistema {
    public static MyHash<Date, MyHash<String, MyList<Song>>> topSongsByDateCountry = new MyHashTable<>(50, 0.75f);
    public static MyHash<Date, MyHash<String, Integer>> topArtistByAppearance = new MyHashTable<>(50, 0.75f);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String path = "C:\\Users\\santi\\Downloads\\universal_top_spotify_songs.csv";
        System.out.println("Loading CSV from: " + path);
        CSVLoader loader = new CSVLoader(path);
        loader.LoadCSV();
        long endTime = System.currentTimeMillis();
        System.out.println("Time elapsed loading CSV: " + (endTime - startTime)/1000 + "s");
        while (menu());
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
                System.out.println("Ingrese la fecha en formato YYYY-MM-DD");
                String dateString = scanner.next();
                Date fechaDada = CSVLoader.parseDate(dateString);
                MyList<Song> top5Songs = consulta2(fechaDada);
                if (top5Songs.size() == 0) {
                    System.out.println("No hay datos para la fecha: " + dateString);
                } else { //por la definición de la consulta, la única forma de que devuelva lista no vacía es con 5 elementos
                    System.out.println("Top 5 canciones que aparecen en más top 50 en la fecha " + dateString + ":");
                    for (Song song : top5Songs) {
                        System.out.println(song.getName() + " de " + Arrays.toString(song.getArtist()));
                    }
                }
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
                System.out.println("Ingrese el tempo inicial");
                float initialTempo = scanner.nextFloat();
                System.out.println("Ingrese el tempo final");
                float finalTempo = scanner.nextFloat();
                System.out.println("Ingrese la fecha inicial en formato YYYY-MM-DD");
                fechaInicial = CSVLoader.parseDate(scanner.next());
                System.out.println("Ingrese la fecha final en formato YYYY-MM-DD");
                fechaFinal = CSVLoader.parseDate(scanner.next());
                int songCount = consulta5(initialTempo, finalTempo, fechaInicial, fechaFinal);
                if (songCount == -1) {
                    System.out.println("Error en los datos ingresados");
                    return true;
                }
                System.out.println("Cantidad de canciones con tempo entre " + initialTempo + " y " + finalTempo + " entre " + fechaInicial + " y " + fechaFinal + ": " + songCount);
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
        if (country.equalsIgnoreCase("GLOBAL")) {
            country = "";
        }
        country = country.toUpperCase();
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

    public static MyList<Song> consulta2(Date fechaDada) {
        /*
         Top 5 canciones que aparecen en más top 50 en un día dado. Las canciones deben
         estar ordenadas de manera descendente. Se espera que esta operación sea de
         orden n en notación Big O.
        */
        MyList<Song> top5 = new MyLinkedListImpl<>();

        //primero verifico si la fecha existe en el hash
        MyHash<String, MyList<Song>> countryToSongs = topSongsByDateCountry.get(fechaDada);
        if (countryToSongs == null) {
            return top5; //si no hay datos para esa fecha, retorna una lista vacía
        }

        //hash para contar apariciones de cada canción
        MyHash<String, Integer> songCount = new MyHashTable<>(50, 0.75f);

        //recorro todas las listas del top 50 para cada país en la fecha dada
        for (String country : countryToSongs.keySet()) { //itero sobre los países
            MyList<Song> songs = countryToSongs.get(country); //lista de canciones del país
            for (Song song : songs) { //itero sobre las canciones
                String songId = song.getSpotifyId(); // obtengo el ID de la canción
                // incremento el contador de apariciones de la canción
                if (songCount.containsKey(songId)) {
                    songCount.put(songId, songCount.remove(songId) + 1);
                } else {
                    songCount.put(songId, 1);
                }
            }
        }

        //utilizo un heap para almacenar las top 5 canciones
        MyHeap<SongCountPair> heap = new MyHeapImpl<>(5, false); //Heap max, pues ordeno en forma decreciente

        // itero sobre las canciones del hash en el conteo de canciones
        for (String songId : songCount.keySet()) {
            int count = songCount.get(songId); //obtengo el conteo de apariciones de la canción
            Song exampleSong = getExampleSongFromId(fechaDada, songId); // Obtengo una instancia de la canción para obtener y mostrar su información

            SongCountPair pair = new SongCountPair(exampleSong, count); //creo un par canción-conteo (clase interna definida más abajo)
            if (heap.size() < 5) {
                heap.insert(pair); //si el heap no está lleno, lo inserto directamente
            } else if (pair.compareTo(heap.get()) > 0) {
                // reemplazo el elemento del heap menor si el nuevo par es mayor
                heap.delete();
                heap.insert(pair);
            }
        }

        //ahora convierto el heap en la lista final de resultados
        while (heap.size() > 0) {
            top5.add(heap.delete().getSong()); //extraigo las canciones del heap (en orden descendente)
        }
        return top5;
    }

    //método auxiliar para consulta 2 para obtener una instancia de canción desde su ID
    private static Song getExampleSongFromId(Date fechaDada, String songId) {
        MyHash<String, MyList<Song>> countryToSongs = topSongsByDateCountry.get(fechaDada);
        for (String country : countryToSongs.keySet()) {
            MyList<Song> songs = countryToSongs.get(country);
            for (Song song : songs) {
                if (song.getSpotifyId().equals(songId)) {
                    return song;
                }
            }
        }
        return null;
    }

    //clase interna auxiliar para consulta 2 para manejar pares de canción y conteo
    static class SongCountPair implements Comparable<SongCountPair> {
        @Getter
        private final Song song;
        @Getter
        private final int count;

        public SongCountPair(Song song, int count) {
            this.song = song;
            this.count = count;
        }

        @Override
        public int compareTo(SongCountPair o) {
            return Integer.compare(this.count, o.count); //comparo por conteo de apariciones
        }
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
        MyHash<String, Integer> artistCount = new MyHashTable<>(50, 0.75f);
        for (; fechaInicial.before(fechaFinal); fechaInicial.setTime(fechaInicial.getTime() + 86400000)) {
            NodeWithKeyValue<String, Integer>[] allArtistsForDate = topArtistByAppearance.get(fechaInicial).getTable();
            for (NodeWithKeyValue<String, Integer> artist : allArtistsForDate) {
                if (artist == null) continue;
                Integer count = artist.getValue();
                if (artistCount.containsKey(artist.getKey())) {
                    count += artistCount.remove(artist.getKey());
                }
                artistCount.put(artist.getKey(), count);
            }
        }
        for (NodeWithKeyValue<String, Integer> node : artistCount.getTable()) {
            if (node == null) continue;
            for (int i = 0; i < 7; i++) {
                if (result[i] == null || node.getValue() > artistCount.get(result[i])) {
                    result[i] = node.getKey();
                    break;
                }
            }
        }
        return result;
    }

    public static int consulta4(Date date, String country, String artist) {
        /*
         Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.
        */
        if (country == null || artist == null || date == null) {
            System.out.println("Invalid Data");
            return -1;
        }
        if (country.equalsIgnoreCase("GLOBAL")) {
            country = "";
        }
        country = country.toUpperCase();
        artist = artist.toUpperCase();
        MyList<Song> top = topSongsByDateCountry.get(date).get(country);
        int counter = 0;
        for (Song song : top) {
            // if artist is in the artist array increase counter
            if (Arrays.asList(song.getArtist()).contains(artist)) counter++;
        }
        return counter;
    }

    public static int consulta5(float initialTempo, float finalTempo, Date initialDate, Date finalDate) {
        /* 
         Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.
        */
        if (initialTempo < 0 || finalTempo < 0) {
            System.out.println("Tempo must be positive");
            return -1;
        }
        if (initialDate == null || finalDate == null) {
            System.out.println("Invalid dates");
            return -1;
        }
        if (initialTempo > finalTempo) {
            System.out.println("Initial tempo must be lower than final tempo");
            return -1;
        }
        if (initialDate.after(finalDate)) {
            System.out.println("Initial date must be before final date");
            return -1;
        }
        // Este numero magico es un dia entero pero en segundos (creo)
        MyHash<String, Song> cancionesEncontradas = new MyHashTable<>(50, 0.75f);
        for (; initialDate.before(finalDate); initialDate.setTime(initialDate.getTime() + 86400000)) {
            NodeWithKeyValue<String, MyList<Song>>[] allTopsForDate = topSongsByDateCountry.get(initialDate).getTable();
            for (NodeWithKeyValue<String, MyList<Song>> top : allTopsForDate) {
                if (top == null) continue;
                for (Song song : top.getValue()) {
                    if (song.getTempo() >= initialTempo && song.getTempo() <= finalTempo) {
                        if (!cancionesEncontradas.containsKey(song.getSpotifyId())) {
                            cancionesEncontradas.put(song.getSpotifyId(), song);
                        }
                    }
                }
            }

        }
        return cancionesEncontradas.size();
    }
}
