package org.superbiz.moviefun;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieRestController {

    private final MoviesRepository repository;

    public MovieRestController(MoviesRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/countAll")
    public int countAll() {
        return repository.countAll();
    }

    @GetMapping("/count/{field}/{searchTerm}")
    public int count(@PathVariable String field, @PathVariable String searchTerm) {
        return repository.count(field, searchTerm);
    }

    @GetMapping("/findAll/{firstResult}/{maxResults}")
    public List<Movie> findAll(@PathVariable Integer firstResult, @PathVariable Integer maxResults) {
        return repository.findAll(firstResult, maxResults);
    }

    @GetMapping("/findRange/{field}/{searchTerm}/{firstResult}/{maxResults}")
    public List<Movie> findRange(@PathVariable String field, @PathVariable String searchTerm, @PathVariable Integer firstResult, @PathVariable Integer maxResults) {
        return repository.findRange(field, searchTerm, firstResult, maxResults);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieId(@PathVariable long id){
        repository.deleteMovieId(id);
    }

    @PostMapping("/addMovie")
    public void addMovie(@RequestBody Movie movie){
        repository.addMovie(movie);
    }

    @GetMapping("/getMovies")
    public List<Movie> getMovies() {
        return repository.getMovies();
    }
}
