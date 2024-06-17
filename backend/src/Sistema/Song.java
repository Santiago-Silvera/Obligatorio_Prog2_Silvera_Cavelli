
package Sistema;

import lombok.Getter;

import java.util.Date;
import java.util.Objects;

public class Song {
    private String spotify_id;
    @Getter
    private String name;
    @Getter
    private String[] artist;
    private int daily_rank;
    private int daily_movement;
    private int weekly_movement;
    @Getter
    private String country;
    private String snapshot_date;
    @Getter
    private int popularity;
    private boolean is_explicit;
    @Getter
    private long duration;
    private String album_name;
    private Date album_release_date;
    @Getter
    private float danceability;
    @Getter
    private float energy;
    @Getter
    private int key;
    @Getter
    private float loudness;
    @Getter
    private int mode;
    @Getter
    private float speechiness;
    @Getter
    private float acousticness;
    @Getter
    private float instrumentalness;
    @Getter
    private float liveness;
    @Getter
    private float valence;
    @Getter
    private float tempo;
    private int time_signature;

    public Song(String spotifyId, String name, String[] artist, int dailyRank, int dailyMovement, int weeklyMovement, String country, String snapshotDate, int popularity, boolean isExplicit, long duration, String album_name, Date albumReleaseDate, float danceability, float energy, int key, float loudness, int mode, float speechiness, float acousticness, float instrumentalness, float liveness, float valence, float tempo, int timeSignature) {
        this.spotify_id = spotifyId;
        this.name = name;
        this.artist = artist;
        this.daily_rank = dailyRank;
        this.daily_movement = dailyMovement;
        this.weekly_movement = weeklyMovement;
        this.country = country;
        this.snapshot_date = snapshotDate;
        this.popularity = popularity;
        this.is_explicit = isExplicit;
        this.duration = duration;
        this.album_name = album_name;
        this.album_release_date = albumReleaseDate;
        this.danceability = danceability;
        this.energy = energy;
        this.key = key;
        this.loudness = loudness;
        this.mode = mode;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.valence = valence;
        this.tempo = tempo;
        this.time_signature = timeSignature;
    }

    public String getSpotifyId() {
        return spotify_id;
    }

    public int getDailyRank() {
        return daily_rank;
    }

    public int getDailyMovement() {
        return daily_movement;
    }

    public int getWeeklyMovement() {
        return weekly_movement;
    }

    public String getDate() {
        return snapshot_date;
    }

    public boolean isExplicit() {
        return is_explicit;
    }

    public String getAlbumName() {
        return album_name;
    }

    public Date getAlbumReleaseDate() {
        return album_release_date;
    }

    public int getTimeSignature() {
        return time_signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(spotify_id, song.spotify_id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(spotify_id);
    }
}

