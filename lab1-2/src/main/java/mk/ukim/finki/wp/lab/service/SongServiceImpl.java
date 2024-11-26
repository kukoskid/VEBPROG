package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;
    private ArtistRepository artistRepository;

    public SongServiceImpl(SongRepository songRepository, ArtistRepository artistRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }


    @Override
    public List<Song> listSongs() {
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist,song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public Song findByCategory(Category category) {
        return songRepository.findByCategory(category);
    }
    @Override
    public Song addSong(String trackId, String title, String genre, int releaseYear){
        Song song = new Song(trackId,title,genre,releaseYear);
        songRepository.addSong(song);
        return song;
    }
    @Override
    public Song modifySong(Long id, String trackId, String title, String genre, int releaseYear){
        return  songRepository.modifySong(id, trackId,title,genre,releaseYear);
    }
    @Override
    public  Song deleteSongById(Long id){
        Song song=songRepository.deleteById(id);
        return song;
    }
    @Override
    public Song findById(Long id) {
        return songRepository.findById(id);
    }

}
