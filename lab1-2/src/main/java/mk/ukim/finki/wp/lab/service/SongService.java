package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();
    Artist addArtistToSong(Artist artist, Song song);
    Song findByTrackId(String trackId);
    Song findByCategory(Category category);
    Song addSong(String trackId, String title, String genre, int releaseYear);
    Song modifySong(Long id, String trackId, String title, String genre, int releaseYear);

    Song deleteSongById(Long id);
    public Song findById(Long id);
}
