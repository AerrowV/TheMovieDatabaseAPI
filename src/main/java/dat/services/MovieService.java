package dat.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dat.dtos.MovieDTO;
import dat.utils.DataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private final String key = System.getenv("MOVIE_API_KEY");
    private final ObjectMapper objectMapper = new ObjectMapper();
    DataReader reader = new DataReader();

    public MovieDTO getMovieById(String movieId) {

        String url = "https://api.themoviedb.org/3/find/" + movieId + "?external_source=imdb_id&api_key=" + key;
        String json = reader.getDataFromClient(url);

        if (json == null) {
            return null;
        }

        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            MovieSearchService response = objectMapper.readValue(json, MovieSearchService.class);

            List<MovieDTO> results = response.getMovie_results();
            if (results != null && !results.isEmpty()) {
                return results.get(0);
            } else {
                System.out.println(" No movie_results found for ID: " + movieId);
            }

        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<MovieDTO> getByRating(double minRating, double maxRating, int maxPages) {
        List<MovieDTO> movies = new ArrayList<>();
        int currentPage = 1;

        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            while (currentPage <= maxPages) {
                String url = "https://api.themoviedb.org/3/discover/movie" +
                        "?api_key=" + key +
                        "&vote_average.gte=" + minRating +
                        "&vote_average.lte=" + maxRating +
                        "&page=" + currentPage;

                String json = reader.getDataFromClient(url);

                if (json == null) {
                    System.out.println("Failed to retrieve data. Check API key or internet connection.");
                    break;
                }

                MovieDiscoverService response = objectMapper.readValue(json, MovieDiscoverService.class);

                if (response.getResults() != null) {
                    movies.addAll(response.getResults());
                }

                currentPage++;
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return movies;
    }
}