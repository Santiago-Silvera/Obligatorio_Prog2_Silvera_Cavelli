package Sistema;

import lombok.Getter;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.util.Date;

public class Song {
    private String spotify_id;
    private String name;
    private String artist;
    private int daily_rank;
    private int daily_movement;
    private int weekly_movement;
    @Getter
    private String country;
    private Date snapshot_date;
    // TODO ... Faltan atributos

    public Song(MyList<String> attributes) {
        // TODO ... inicializar atributos a partir de la linked list
    }

    public Date getDate() {
        return snapshot_date;
    }

}
