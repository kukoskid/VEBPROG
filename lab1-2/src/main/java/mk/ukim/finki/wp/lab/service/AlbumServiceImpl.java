package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlbumServiceImpl implements AlbumService{
    private AlbumRepository albumRepository;
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository=albumRepository;
    }

    @Override
    public List<Album> findAll() {
        return albumRepository.findAll();
    }
    public Album addSongToAlbum(Long albumId, Song song){
        return albumRepository.addSongToAlbum(song,albumId);
    }
}
