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

    public List<TmdbMovieResult> getRandomMoviePage() {
        int randomPage = new Random().nextInt(100) + 1;
        return tmdbMovieClient.getPopularMovies(randomPage).getResults();

    }
 }
