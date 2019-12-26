package com.upbeatdigital.enjoy.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.upbeatdigital.enjoy.R;
import com.upbeatdigital.enjoy.databinding.ItemSilderBinding;
import com.upbeatdigital.enjoy.databinding.ItemVideoBinding;
import com.upbeatdigital.enjoy.models.Slider;
import com.upbeatdigital.enjoy.models.Video;
import com.upbeatdigital.enjoy.utils.ExoPlayerManager;

import java.util.List;


public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private Context context;
    private List<Slider> videoList;
    private final ListItemClickListener clickListener;

    public interface ListItemClickListener {
        void onListItemClicked(int clickedItemIndex);
    }

    public SliderAdapter(Context context, List<Slider> videoList, ListItemClickListener listener) {
        this.context = context;
        this.videoList = videoList;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemSilderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_silder, viewGroup, false);
        return new SliderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        ExoPlayerManager exoPlayerManager = new ExoPlayerManager(holder.binding.videoPlayerView);
        exoPlayerManager.initializeMediaSession(context);
        exoPlayerManager.setPlayerAndLoadingIndicatorSize(context);
        exoPlayerManager.initializePlayer(context, Uri.parse(videoList.get(position).getVideo()));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }


    public class SliderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemSilderBinding binding;

        SliderViewHolder(ItemSilderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onListItemClicked(getAdapterPosition());
        }
    }
}
