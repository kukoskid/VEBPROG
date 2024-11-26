package mk.ukim.finki.wp.lab.model;


import lombok.Getter;
import java.util.UUID;

@Getter
public class Album {
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;

    public Album( String name, String genre, String releaseYear) {
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.id = UUID.randomUUID().getLeastSignificantBits()+UUID.randomUUID().getMostSignificantBits();
    }
}
