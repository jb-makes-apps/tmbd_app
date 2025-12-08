package com.movie.proj.tmdb;
import com.movie.proj.config.TmdbProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TmdbMovieClient {

    private final RestTemplate restTemplate;
    private final TmdbProperties props;

    public TmdbMovieClient(RestTemplate tmdbRestTemplate, TmdbProperties props) {
        this.restTemplate = tmdbRestTemplate;
        this.props = props;
    }

    public TmdbMovieDetailsResponse getMovieById(long id) {
        String url = UriComponentsBuilder
                .fromUriString(props.getBaseUrl())   // <-- correct method
                .path("/movie/{id}")
                .queryParam("api_key", props.getApiKey())
                .buildAndExpand(id)
                .toUriString();

        return restTemplate.getForObject(url, TmdbMovieDetailsResponse.class);
    }
}
