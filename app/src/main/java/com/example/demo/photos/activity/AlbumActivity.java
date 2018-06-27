package com.example.demo.photos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.demo.photos.R;
import com.example.demo.photos.adapter.AlbumListAdapter;
import com.example.demo.photos.model.request.AlbumResponseVo;
import com.example.demo.photos.network.APIClient;
import com.example.demo.photos.network.APIInterface;
import com.example.demo.photos.utils.GridSpacingItemDecoration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AlbumActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView listview;
    private AlbumListAdapter photosGridAdapter;
    List<AlbumResponseVo> albumResponseVos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        listview = findViewById(R.id.listview);
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        setupListview();
        Call<List<AlbumResponseVo>> albumResponseVoCall = apiInterface.getAlbums();
        albumResponseVoCall.enqueue(new Callback<List<AlbumResponseVo>>() {
            @Override
            public void onResponse(Call<List<AlbumResponseVo>> call, Response<List<AlbumResponseVo>> response) {
                albumResponseVos = response.body();
                Log.d("albums: ", response.toString());
                photosGridAdapter.setListData(albumResponseVos);
                photosGridAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AlbumResponseVo>> call, Throwable t) {

            }
        });
    }

    private void setupListview() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listview.setLayoutManager(linearLayoutManager);
        photosGridAdapter = new AlbumListAdapter(this, this);
        photosGridAdapter.setListData(albumResponseVos);
        //listview.removeItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        listview.setAdapter(photosGridAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rtlParent:
                AlbumResponseVo albumResponseVo = (AlbumResponseVo) v.getTag();
                Intent intent = new Intent(this, PhotosActivity.class);
                intent.putExtra("ALBUM_ID", albumResponseVo.getId());
                startActivity(intent);
        }
    }
}
