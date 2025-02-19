package dat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dat.dtos.MovieDTO;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MovieDiscoverService {
    private int page;
    private List<MovieDTO> results;
    private int total_pages;
    private int total_results;
}

