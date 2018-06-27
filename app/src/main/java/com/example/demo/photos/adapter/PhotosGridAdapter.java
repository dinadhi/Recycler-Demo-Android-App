package com.example.demo.photos.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo.photos.R;
import com.example.demo.photos.activity.PhotosActivity;
import com.example.demo.photos.model.request.PhotosResponseVo;
import com.example.demo.photos.utils.StringUtils;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class PhotosGridAdapter extends RecyclerView.Adapter<PhotosGridAdapter.ViewHolder> {

    private HashMap<Integer, Integer> priceDropProductList;
    private List<PhotosResponseVo> savedProductList;
    private Context mContext;
    private LayoutInflater mInflator;
    private List<PhotosResponseVo> mListdata;
    private OnClickListener mClickListener;
    private Picasso picasso;
    private int pos;

    public PhotosGridAdapter(Context context, PhotosActivity photosActivity) {
        mContext = context;
        mClickListener = photosActivity;
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        picasso = Picasso.with(mContext);
    }

    public void setListData(List<PhotosResponseVo> products) {
        this.mListdata = products;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mListdata == null ? 0 : mListdata.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewholder, final int position) {

        PhotosResponseVo photoData = mListdata.get(position);
        if (!StringUtils.isNullOrEmpty(photoData.getUrl())) {
            loadParseFileInBackground(mListdata.get(position).getUrl(), viewholder.imgProduct);
        }
        if (!StringUtils.isNullOrEmpty(photoData.getThumbnailUrl())) {
            loadParseFileInBackground(photoData.getThumbnailUrl(), viewholder.imgThumb);
        }
        viewholder.txtPhotoTitle.setText("Title: "+photoData.getTitle());
        viewholder.txtAlbumTitle.setText("Album ID: "+photoData.getAlbumId());
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumb;
        ImageView imgProduct;
        TextView txtAlbumTitle;
        TextView txtPhotoTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumb = (ImageView) itemView.findViewById(R.id.imgThumb);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            txtAlbumTitle = (TextView) itemView.findViewById(R.id.txtAlbumTitle);
            txtPhotoTitle = (TextView) itemView.findViewById(R.id.txtPhotoTitle);
        }
    }

    private void loadParseFileInBackground(String url, final ImageView imgView) {
        if (!StringUtils.isNullOrEmpty(url)) {
            picasso.load(url).into(imgView);
        }

    }


}
