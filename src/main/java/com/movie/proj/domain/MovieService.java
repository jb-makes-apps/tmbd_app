package com.movie.proj.domain;

import com.movie.proj.tmdb.TmdbMovieClient;
import com.movie.proj.tmdb.TmdbMovieDetailsResponse;
import com.movie.proj.tmdb.TmdbMovieResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MovieService {

    private final TmdbMovieClient tmdbMovieClient;

    public MovieService(TmdbMovieClient tmdbMovieClient) {
        this.tmdbMovieClient = tmdbMovieClient;
    }

    public TmdbMovieDetailsResponse getMovie(long id) {
        return tmdbMovieClient.getMovieById(id);
    }

    public TmdbMovieResult getRandomMovie() {
        int randomPage = new Random().nextInt(100) + 1;
        List<TmdbMovieResult> movies = tmdbMovieClient
                .getPopularMovies(randomPage)
                .getResults();

        return movies.get(new Random().nextInt(movies.size()));
    }
}
