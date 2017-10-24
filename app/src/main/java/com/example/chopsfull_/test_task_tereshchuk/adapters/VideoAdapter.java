package com.example.chopsfull_.test_task_tereshchuk.adapters;

import android.gesture.GestureLibraries;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chopsfull_.test_task_tereshchuk.R;
import com.example.chopsfull_.test_task_tereshchuk.model.Video;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoHolder> {

    List<Video> videoList;

    public VideoAdapter(List<Video> list){
        this.videoList = list;
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        Video video = videoList.get(position);
        Glide.with(holder.v).load(video.getThumbnailUrl()).into(holder.thumbNail);
        Glide.with(holder.v).load(video.getUser().getAvatarUrl()).into(holder.avatarImage);
        holder.username.setText("Published by: "+video.getUser().getUsername());
        holder.videoName.setText(video.getTitle());
        holder.countLikes.setText(String.valueOf(video.getLikesCount()) + " liked");
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        return new VideoHolder(v);
    }

    @Override
    public int getItemCount() {
        if(videoList!=null)
            return videoList.size();
        return 0;
    }

    class VideoHolder extends RecyclerView.ViewHolder{
        CircleImageView avatarImage;
        ImageView thumbNail;
        TextView videoName,countLikes, username;
        View v;

        public VideoHolder(View itemView) {
            super(itemView);
            v = itemView;
            username = (TextView) itemView.findViewById(R.id.text_publisher);
            avatarImage = (CircleImageView) itemView.findViewById(R.id.image_publisher);
            thumbNail = (ImageView) itemView.findViewById(R.id.image_video_thumbnail);
            videoName = (TextView) itemView.findViewById(R.id.text_video_name);
            countLikes = (TextView) itemView.findViewById(R.id.text_number_of_likes);
        }
    }
}
