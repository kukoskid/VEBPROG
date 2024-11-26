package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AtristServiceImpl implements ArtistService{
    private ArtistRepository artistRepository;
    public AtristServiceImpl(ArtistRepository artistRepository){
        this.artistRepository=artistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findById(Long id) {
        return artistRepository.findById(id).get();
    }


}
