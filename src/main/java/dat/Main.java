package dat;

import dat.dtos.MovieDTO;
import dat.services.MovieService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MovieService movieService = new MovieService();
        MovieDTO movie = movieService.getMovieById("tt6710474");
        List<MovieDTO> highRated = movieService.getByRating(8.5, 9.0, 1);


        System.out.println("Title: " + movie.getTitle());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("Release Date: " + movie.getRelease_date());

        for (MovieDTO mov : highRated) {
            System.out.println(" - " + mov.getTitle() + " (" + mov.getVote_average() + ")");
        }
    }
}