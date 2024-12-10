package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
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
        song.addPerformer(artist);
        songRepository.save(song);
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        var songO = songRepository.findByTrackId(trackId);
        return songO.get();
    }

    @Override
    public Song addSong(String trackId, String title, String genre, int releaseYear){
        Song song = new Song(trackId,title,genre,releaseYear);
        songRepository.save(song);
        return song;
    }
    @Override
    public Song modifySong(Long id, String trackId, String title, String genre, int releaseYear){
        Song song=songRepository.findById(id).get();
        if (song!=null){
            song.setTrackId(trackId);
            song.setTitle(title);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
        }
        songRepository.save(song);
        return song;
    }
    @Override
    public void deleteSongById(Long id){
        songRepository.deleteById(id);
    }
    @Override
    public Song findById(Long id) {
        return songRepository.findById(id).get();
    }
    @Override
    public List<Song> getSongsByAlbumId(Long albumId){
        return songRepository.findAllByAlbum_Id(albumId);
    }

}
