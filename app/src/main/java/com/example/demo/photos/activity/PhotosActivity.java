package com.example.demo.photos.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.demo.photos.R;
import com.example.demo.photos.adapter.PhotosGridAdapter;
import com.example.demo.photos.model.request.PhotosResponseVo;
import com.example.demo.photos.network.APIClient;
import com.example.demo.photos.network.APIInterface;
import com.example.demo.photos.utils.GridSpacingItemDecoration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhotosActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView listview;
    private PhotosGridAdapter photosGridAdapter;
    List<PhotosResponseVo> photosResponseVos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        listview = findViewById(R.id.listview);
        setupListview();
        int id = getIntent().getIntExtra("ALBUM_ID", 1);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<PhotosResponseVo>> albumResponseVoCall = apiInterface.getAlbumPhotos(id);
        albumResponseVoCall.enqueue(new Callback<List<PhotosResponseVo>>() {
            @Override
            public void onResponse(Call<List<PhotosResponseVo>> call, Response<List<PhotosResponseVo>> response) {
                photosResponseVos = response.body();
                photosGridAdapter.setListData(photosResponseVos);
                photosGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PhotosResponseVo>> call, Throwable t) {

            }
        });

    }

    private void setupListview() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listview.setLayoutManager(linearLayoutManager);
        photosGridAdapter = new PhotosGridAdapter(this, this);
        photosGridAdapter.setListData(photosResponseVos);
        listview.removeItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        listview.setAdapter(photosGridAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
