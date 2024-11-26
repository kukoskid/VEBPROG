package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Song {
    String trackId;
    String  title;
    String  genre;
    int releaseYear;
    List<Artist> performers = new ArrayList<>();
    Long id;
    @ManyToOne
    Album album;
    public Song(String trackId, String title, String genre, int releaseYear) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits()+UUID.randomUUID().getMostSignificantBits());
    }

    @Override
    public String toString() {
        return "Song{" +
                "trackId='" + trackId + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }

    public void addPerformer(Artist performer) {
        performers.add(performer);
    }
}
