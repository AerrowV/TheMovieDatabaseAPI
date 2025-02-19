package dat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dat.dtos.MovieDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchService {

    private List<MovieDTO> movie_results;
    private List<Object> person_results;
    private List<Object> tv_results;
    private List<Object> episode_results;
    private List<Object> tv_seasons_results;

}
