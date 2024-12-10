package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlbumServiceImpl implements AlbumService{
    private AlbumRepository albumRepository;
    private SongRepository songRepository;
    public AlbumServiceImpl(AlbumRepository albumRepository,SongRepository songRepository) {
        this.albumRepository=albumRepository;
        this.songRepository=songRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
    public Album addSongToAlbum(Long albumId, Song song){
        Album album=albumRepository.findById(albumId).get();
        song.setAlbum(album);
        songRepository.save(song);
        return album;
    }
    public Album findById(Long albumId){
        return albumRepository.findById(albumId).get();
    }
}
