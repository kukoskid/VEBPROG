package mk.ukim.finki.wp.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.repository.AlbumRepository;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import mk.ukim.finki.wp.lab.repository.SongRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    private AlbumRepository albumRepository;
    private SongRepository songRepository;
    private ArtistRepository artistRepository;

    public DataInitializer(AlbumRepository albumRepository, SongRepository songRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
    }
    @PostConstruct
    public void initData(){
        List<Album> albums=new ArrayList<>();
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
        albumRepository.saveAll(albums);

        List<Song> songs=new ArrayList<>();
        Song s1=new Song("SF","Yes","rock",1967);
        Song s2=new Song("OTTR","Snow","alternative",2012);
        Song s3=new Song("HCTS","Christmas","rock",1969);
        Song s4=new Song("HJ","a","rock",1968);
        Song s5=new Song("LH","Like","rap",2024);
        songs.add(s1);
        songs.add(s2);
        songs.add(s3);
        songs.add(s4);
        songs.add(s5);
        s1.setAlbum(album1);
        s2.setAlbum(album1);
        s3.setAlbum(album2);
        s4.setAlbum(album3);
        s5.setAlbum(album5);
        songRepository.saveAll(songs);

        List<Artist> artists=new ArrayList<>();
        Artist a1=new Artist("Damjan","Kukoski","bio");
        Artist a2=new Artist("Mila","Gjorgoska","bio");
        Artist a3=new Artist("Teodor","Duracoski","bio");
        Artist a4=new Artist("Angel","Mirceski","bio");
        Artist a5=new Artist("Ilija","Bunceski","bio");
        artists.add(a1);
        artists.add(a2);
        artists.add(a3);
        artists.add(a4);
        artists.add(a5);
        artistRepository.saveAll(artists);
    }
}
