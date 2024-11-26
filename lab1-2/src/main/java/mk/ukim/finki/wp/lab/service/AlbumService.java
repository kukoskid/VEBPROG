package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;

import java.util.List;

public interface AlbumService {
    public List<Album> findAll();
    public Album addSongToAlbum(Long albumId, Song song);

}
