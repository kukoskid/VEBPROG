package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.implementation.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.implementation.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "details", urlPatterns = "/servlet/showDetails")
public class SongDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final SongServiceImpl songService;
    private final ArtistServiceImpl artistService;

    public SongDetailsServlet(SpringTemplateEngine springTemplateEngine, SongServiceImpl songService, ArtistServiceImpl artistService) {
        this.springTemplateEngine = springTemplateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("trackId");
        String artistId = req.getParameter("artistId");

        if (trackId == null || artistId == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Track ID and Artist ID are required");
            return;
        }

        try {
            long artistIdLong = Long.parseLong(artistId);
            var song = songService.findByTrackId(trackId);
            var artist = artistService.findById(artistIdLong);

            if (song == null || artist == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Song or Artist not found");
                return;
            }

            // Add the artist to the song's performers if not already present
            if (!song.getPerformers().contains(artist)) {
                song.getPerformers().add(artist);
            }

            IWebExchange exchange = JakartaServletWebApplication
                    .buildApplication(getServletContext())
                    .buildExchange(req, resp);

            WebContext context = new WebContext(exchange);
            context.setVariable("wantedSong", song);
            context.setVariable("wantedArtist", artist);

            springTemplateEngine.process("songDetails.html", context, resp.getWriter());
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Artist ID format");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the request");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = req.getParameter("trackId");
        String artistId = req.getParameter("artistId");

        if (trackId == null || artistId == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Track ID and Artist ID are required");
            return;
        }

        // Redirect with parameters instead of storing in instance variables
        resp.sendRedirect("/servlet/showDetails?trackId=\" + trackId + \"&artistId=\" + artistId");
    }
}