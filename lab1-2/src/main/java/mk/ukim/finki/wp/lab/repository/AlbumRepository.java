package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class AlbumRepository {
    List<Album> albums;

    public AlbumRepository(SongRepository songRepository) {
        albums=new ArrayList<>();
        var album1=new Album("album1","rock","1999");
        var album2=new Album("album2","rock","1999");
        var album3=new Album("album3","rock","1999");
        var album4=new Album("album4","rock","1999");
        var album5=new Album("album5","rock","1999");
        albums.add(album1);
        albums.add(album2);
        albums.add(album3);
        albums.add(album4);
        albums.add(album5);

        var allSongs = songRepository.findAll();
        allSongs.get(0).setAlbum(album1);
        allSongs.get(1).setAlbum(album1);
        allSongs.get(2).setAlbum(album2);
        allSongs.get(3).setAlbum(album3);
        allSongs.get(4).setAlbum(album5);
    }
    public List<Album> findAll(){
        return albums;
    }
    public Album findAlbumById(Long id){
        Album found=null;
        for (var album:albums) {
            if (album.getId().equals(id)){
                found=album;
                break;
            }
        }
        return found;
    }
    public Album addSongToAlbum(Song song, Long albumId){
        var album=findAlbumById(albumId);
        if (album!=null){
            song.setAlbum(album);
        }
        return album;
    }
}
