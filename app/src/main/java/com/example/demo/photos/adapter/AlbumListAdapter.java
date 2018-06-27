package com.example.demo.photos.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.demo.photos.R;
import com.example.demo.photos.activity.AlbumActivity;
import com.example.demo.photos.model.request.AlbumResponseVo;
import com.example.demo.photos.utils.StringUtils;

import java.util.List;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder> {

    private final int screenWidth;
    private final int screenHeight;
    private final float density;
    private Context mContext;
    private LayoutInflater mInflator;
    private List<AlbumResponseVo> mListdata;
    private OnClickListener mClickListener;
    private int pos;

    public AlbumListAdapter(Context context, AlbumActivity albumActivity) {
        mContext = context;
        density = mContext.getResources().getDisplayMetrics().density;
        mClickListener = albumActivity;
        screenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        screenHeight = mContext.getResources().getDisplayMetrics().heightPixels;
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setListData(List<AlbumResponseVo> products) {
        this.mListdata = products;
    }

    public List<AlbumResponseVo> getListData() {
        return mListdata;
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
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_album, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewholder, final int position) {

        AlbumResponseVo a = mListdata.get(position);
        viewholder.txtSno.setText("" + (position + 1));
        viewholder.txtAlbumTitle.setText(a.getTitle());

        viewholder.rtlParent.setTag(mListdata.get(position));
        viewholder.rtlParent.setOnClickListener(mClickListener);

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rtlParent;
        TextView txtSno;
        TextView txtAlbumTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            rtlParent = itemView.findViewById(R.id.rtlParent);
            txtSno = itemView.findViewById(R.id.txtSno);
            txtAlbumTitle = itemView.findViewById(R.id.txtAlbumTitle);
        }
    }


}
