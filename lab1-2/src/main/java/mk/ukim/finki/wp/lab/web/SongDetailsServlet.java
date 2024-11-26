package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import mk.ukim.finki.wp.lab.service.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "SongDetailsServlet", urlPatterns = {"/songs/details"})
public class SongDetailsServlet extends HttpServlet {
    private SpringTemplateEngine templateEngine;
    private ArtistService artistService;
    private SongService songService;
    public SongDetailsServlet(SpringTemplateEngine templateEngine, ArtistService artistService, SongService songService) {
        this.templateEngine = templateEngine;
        this.artistService = artistService;
        this.songService = songService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var song = songService.listSongs().stream().findFirst().orElse(null);

        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("songDetails",song);
        templateEngine.process("songDetails.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("trackId");
        String artistId = req.getParameter("artistId");

        if (trackId == null || artistId == null){
            resp.sendRedirect("/listSongs");
            return;
        }
        Song song = songService.findByTrackId(trackId);
        Artist a = artistService.findById(Long.valueOf(artistId));
        song.addPerformer(a);

        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(req.getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("songDetails",song);
        templateEngine.process("songDetails.html",context,resp.getWriter());
    }
}
