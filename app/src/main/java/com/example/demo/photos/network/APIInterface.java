package com.example.demo.photos.network;

import com.example.demo.photos.model.request.AlbumResponseVo;
import com.example.demo.photos.model.request.PhotosResponseVo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {

    @GET("/albums")
    Call<List<AlbumResponseVo>> getAlbums();

    @GET("/albums/{albumId}/photos")
    Call<List<PhotosResponseVo>> getAlbumPhotos(@Path("albumId") int albumId);

}