package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SongController {
    private AlbumService albumService;
    private SongService songService;
    public SongController(SongService songService, AlbumService albumService){
        this.albumService=albumService;
        this.songService=songService;
    }

    @GetMapping("/songs")
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("songList",songService.listSongs());
        return "listSongs";
    }
    @GetMapping("/album/{albumId}")
    public String getSongsFromAlbum(@PathVariable Long albumId, Model model){
        Album album=albumService.findById(albumId);
        model.addAttribute("album",album);
        model.addAttribute("songList",songService.getSongsByAlbumId(albumId));
        return "listSongsInAlbum";
    }
    @PostMapping("/songs/add")
    public String saveSong(
            @RequestParam String trackId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId,
            Model model){

            Song song=songService.addSong(trackId,title,genre,releaseYear);
            Album album=albumService.addSongToAlbum(albumId,song);
            model.addAttribute("songList",songService.listSongs());
            return  "listSongs";

    }
    @PostMapping("/songs/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId,
                           Model model){
        Song song = songService.modifySong(songId,trackId,title,genre,releaseYear);
        Album oldAlbum=song.getAlbum();
        Album newAlbum=albumService.addSongToAlbum(albumId,song);
        model.addAttribute("songList",songService.listSongs());
        return "listSongs";
    }
    @GetMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable Long id, Model model){
        songService.deleteSongById(id);
        model.addAttribute("songList",songService.listSongs());
        return "listSongs";
    }
    @GetMapping("/songs/add-form")
    public String getAddSongPage(Model model){
        model.addAttribute("albumList",albumService.findAll());
        return "add-song";
    }
    @GetMapping("/songs/edit-form/{id}")
    public String editForm(Model model,@PathVariable Long id){
        model.addAttribute("albumList",albumService.findAll());
        Song song=songService.findById(id);
        model.addAttribute("song",song);
        return "add-song";
    }
}
