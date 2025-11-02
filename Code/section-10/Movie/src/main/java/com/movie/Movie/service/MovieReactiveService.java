package com.movie.Movie.service;

import com.movie.Movie.domain.Movie;
import com.movie.Movie.domain.Review;
import com.movie.Movie.exception.MovieException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class MovieReactiveService {

    private MovieInfoService movieInfoService;
    private ReviewService reviewService;
    private RevenueService revenueService;

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
    }

    public MovieReactiveService(MovieInfoService movieInfoService, ReviewService reviewService, RevenueService revenueService) {
        this.movieInfoService = movieInfoService;
        this.reviewService = reviewService;
        this.revenueService = revenueService;
    }

    public Flux<Movie> getAllMovies(){
        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                    .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo,reviewsList));
                }).onErrorMap(error->{
                    log.info("Exception is {} ", error.getMessage());
                    throw new MovieException(error.getMessage());
                })
                .log();
    }

    public Flux<Movie> getAllMovies_Retry(){
        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo,reviewsList));
                }).onErrorMap(error->{
                    log.info("Exception is {} ", error.getMessage());
                    throw new MovieException(error.getMessage());
                })
                .retry(3)
                .log();
    }

    public Flux<Movie> getAllMovies_RetryWhen(){
        var moviesInfoFlux = movieInfoService.retrieveMoviesFlux();
        RetryBackoffSpec backoff = Retry.backoff(3, Duration.ofMillis(500));
        return moviesInfoFlux
                .flatMap(movieInfo -> {
                    Mono<List<Review>> reviewsMono = reviewService.retrieveReviewsFlux(movieInfo.getMovieInfoId())
                            .collectList();
                    return reviewsMono
                            .map(reviewsList -> new Movie(movieInfo,reviewsList));
                }).onErrorMap(error->{
                    log.info("Exception is {} ", error.getMessage());
                    throw new MovieException(error.getMessage());
                }).retryWhen(backoff)
                .log();
    }

    public Mono<Movie> getMovieById(long movieId){
        var movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        var reviewsFlux =  reviewService.retrieveReviewsFlux(movieId)
                .collectList();
        return movieInfoMono.zipWith(reviewsFlux,(movieInfo,reviews) -> new Movie(movieInfo, reviews));
    }

    public Mono<Movie> getMovieAndRevenueById(long movieId){
        var movieInfoMono = movieInfoService.retrieveMovieInfoMonoUsingId(movieId);
        var reviewsFlux =  reviewService.retrieveReviewsFlux(movieId)
                .collectList();

        var revenueMono = Mono.fromCallable(()-> revenueService.getRevenue(movieId))
                .subscribeOn(Schedulers.boundedElastic());

        return movieInfoMono
                .zipWith(reviewsFlux,(movieInfo,reviews) -> new Movie(movieInfo, reviews))
                .zipWith(revenueMono, (movie, revenue)->{
                    movie.setRevenue(revenue);
                    return movie;
                });
    }

}
