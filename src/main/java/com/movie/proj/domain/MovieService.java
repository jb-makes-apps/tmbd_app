package com.movie.proj.domain;

import com.movie.proj.tmdb.TmdbMovieClient;
import com.movie.proj.tmdb.TmdbMovieDetailsResponse;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final TmdbMovieClient tmdbMovieClient;

    public MovieService(TmdbMovieClient tmdbMovieClient) {
        this.tmdbMovieClient = tmdbMovieClient;
    }

    public TmdbMovieDetailsResponse getMovie(long id) {
        return tmdbMovieClient.getMovieById(id);
    }
}
