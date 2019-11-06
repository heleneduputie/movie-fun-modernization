package org.superbiz.moviefun.albumsapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumsClient {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String albumServiceUrl;

    RestTemplate restTemplate = new RestTemplate();

    public AlbumsClient(@Value("${album.service.url}") String albumServiceUrl) {
        this.albumServiceUrl = albumServiceUrl;
    }

    private String albumsServiceUrl(String path) {
        return albumServiceUrl + path;
    }

    public void addAlbum(AlbumInfo album) {

        restTemplate.postForEntity(albumsServiceUrl("/addAlbum"), album, AlbumInfo.class);
    }

    public AlbumInfo find(long id) {

        StringBuilder url = new StringBuilder();
        url.append("/").append(id);

        return restTemplate.getForObject(albumsServiceUrl(url.toString()), AlbumInfo.class);
    }

    public List<AlbumInfo> getAlbums() {
        return restTemplate.getForObject(albumsServiceUrl("/getAlbums"), ArrayList.class);
    }

    public void updateAlbum(AlbumInfo album) {

        StringBuilder url = new StringBuilder();
        url.append("/").append(album);

        restTemplate.postForEntity(albumsServiceUrl(url.toString()), album, AlbumInfo.class);
    }
}
