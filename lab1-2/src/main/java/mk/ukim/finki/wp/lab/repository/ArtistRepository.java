package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {
    List<Artist> artists;

    public ArtistRepository(){
        artists=new ArrayList<>();
        Artist a1=new Artist(1l,"Damjan","Kukoski","bio");
        Artist a2=new Artist(2l,"Mila","Gjorgoska","bio");
        Artist a3=new Artist(3l,"Teodor","Duracoski","bio");
        Artist a4=new Artist(4l,"Nikola","Markoski","bio");
        Artist a5=new Artist(5l,"Sara","Korkut","bio");
        artists.add(a1);
        artists.add(a2);
        artists.add(a3);
        artists.add(a4);
        artists.add(a5);
    }
    public List<Artist> findAll(){
        return artists;
    }
    public Optional<Artist> findById(Long id){
        Artist found=null;
        for (var artist: artists) {
            if (artist.getId().equals(id)){
                found=artist;
                break;
            }
        }
        return Optional.ofNullable(found);
    }
    public Optional<Artist> findById2(Long id){
        return artists.stream().filter(x -> x.getId().equals(id)).findFirst();
    }
}
