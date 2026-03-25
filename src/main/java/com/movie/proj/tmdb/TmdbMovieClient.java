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
                .fromUriString(props.getBaseUrl())
                .path("/movie/{id}")
                .buildAndExpand(id)
                .toUriString();

        return restTemplate.getForObject(url, TmdbMovieDetailsResponse.class);
    }

    public TmdbMovieListResponse getPopularMovies(int randomPage) {
        String url = UriComponentsBuilder
                .fromUriString(props.getBaseUrl())
                .path("/movie/popular")
                .queryParam("page", randomPage)
                .buildAndExpand()
                .toUriString();

        return restTemplate.getForObject(url, TmdbMovieListResponse.class);
    }
}