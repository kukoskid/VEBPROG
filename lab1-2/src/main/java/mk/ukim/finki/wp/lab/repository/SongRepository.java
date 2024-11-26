package mk.ukim.finki.wp.lab.repository;

import lombok.Getter;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Category;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
@Repository
@Getter

public class SongRepository {
    List<Song> songs;
    Category category1 = new Category("1");
    Category category2 = new Category("2");
    Category category3 = new Category("3");

    public SongRepository(){
        songs=new ArrayList<>();
        Song s1=new Song("SF","Heaven","rock",1967);
        Song s2=new Song("OTTR","Alone","alternative",2012);
        Song s3=new Song("HCTS","Faded","rock",1969);
        Song s4=new Song("HJ","Rowr","rock",1968);
        Song s5=new Song("LH","Like","rap",2024);
        songs.add(s1);
        songs.add(s2);
        songs.add(s3);
        songs.add(s4);
        songs.add(s5);

    }

    public List<Song> findAll(){
        return songs;
    }
    public Song findByTrackId(String trackId){
        Song found=null;
        for (var track:songs) {
            if (track.getTrackId().equals(trackId)){
                found=track;
                break;
            }
        }
        return found;
    }
    public Song findByCategory(Category category){
        Song found=null;
        for (var track:songs) {
            if (track.getTrackId().equals(category)){
                found=track;
                break;
            }
        }
        return found;
    }

    public Artist addArtistToSong(Artist artist, Song song){
        song.getPerformers().add(artist);
        return artist;
    }

    public Song addSong(Song song){
        songs.add(song);
        return song;
    }
    public Song findById(Long id){
        Song found=null;
        for (var track:songs) {
            if (track.getId().equals(id)){
                found=track;
                break;
            }
        }
        return found;
    }

    public Song modifySong(Long id,String trackId, String title,String genre,int releaseYear){
        Song song=findById(id);
        if (song!=null){
            song.setTrackId(trackId);
            song.setTitle(title);
            song.setGenre(genre);
            song.setReleaseYear(releaseYear);
        }
        return song;
    }

    public Song deleteById(Long id){
        Song song=findById(id);
        if (song!=null){
            songs.remove(song);
        }
        return song;
    }

}
