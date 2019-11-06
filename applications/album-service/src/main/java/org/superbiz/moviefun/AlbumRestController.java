package org.superbiz.moviefun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.superbiz.moviefun.albums.Album;
import org.superbiz.moviefun.albums.AlbumsRepository;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumRestController {

    private final AlbumsRepository albumsRepository;

    public AlbumRestController(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    @PostMapping("/addAlbum")
    public void addAlbum(@RequestBody Album album){
        this.albumsRepository.addAlbum(album);
    }

    @GetMapping("/{id}")
    public Album find(@PathVariable Long id){
        return this.albumsRepository.find(id);
    }

    @GetMapping("/getAlbums")
    public List<Album> getAlbums(){
        return this.albumsRepository.getAlbums();
    }

    @PutMapping("/{album}")
    public void updateAlbum(@PathVariable Album album){
        this.albumsRepository.updateAlbum(album);
    }
}
