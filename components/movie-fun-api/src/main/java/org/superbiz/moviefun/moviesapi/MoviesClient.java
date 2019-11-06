package org.superbiz.moviefun.moviesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoviesClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String movieServiceUrl;

    RestTemplate restTemplate = new RestTemplate();

    public MoviesClient(@Value("${movie.service.url}") String movieServiceUrl) {
        this.movieServiceUrl = movieServiceUrl;
    }

    public void addMovie(MovieInfo movie) {

        restTemplate.postForEntity(moviesServiceUrl("/addMovie"), movie, MovieInfo.class);
    }

    public void deleteMovieId(long id) {
        restTemplate.delete(moviesServiceUrl("/{1}"), id);
    }

    public List<MovieInfo> getMovies() {
        return restTemplate.getForObject(moviesServiceUrl("/getMovies"), ArrayList.class);
    }

    ParameterizedTypeReference<List<MovieInfo>> moviesListType = new ParameterizedTypeReference<List<MovieInfo>>() {};

    public List<MovieInfo> findAll(int firstResult, int maxResults) {
        StringBuilder url = new StringBuilder();
        url.append("/findAll/").append(firstResult).append("/").append(maxResults);

        return restTemplate.exchange(moviesServiceUrl(url.toString()), HttpMethod.GET, null, moviesListType).getBody();
    }

    public int countAll() {
        return restTemplate.getForObject(moviesServiceUrl("/countAll"), Integer.class);
    }

    public int count(String field, String searchTerm) {

        StringBuilder url = new StringBuilder().append("/count/").append(field).append("/").append(searchTerm);

        return restTemplate.getForObject(moviesServiceUrl(url.toString()), Integer.class);
    }

    public List<MovieInfo> findRange(String field, String searchTerm, int firstResult, int maxResults) {

        StringBuilder url = new StringBuilder();
        url.append("/findRange/").append(field).append("/").append(searchTerm).append("/").append(firstResult).append("/").append(maxResults);

        return restTemplate.getForObject(moviesServiceUrl(url.toString()), ArrayList.class);
    }

    private String moviesServiceUrl(String path) {
        return movieServiceUrl + path;
    }

}
