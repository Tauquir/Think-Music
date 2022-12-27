package com.example.thinkmusic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MusicListAdapter<imagebutton> extends RecyclerView.Adapter<MusicListAdapter.ViewHolder> {

    ArrayList<AudioModel> songList;
    ArrayList<AudioModel> songSearchList;
    RecyclerView recyclerView;




    Context context;
    ImageButton imagebutton;
    boolean Imagedisplaying = true;

    public MusicListAdapter(ArrayList<AudioModel> songList, Context context) {
        this.songList = songList;
        this.context = context;
        songSearchList = new ArrayList<>(songList);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);

       // recyclerView =(R.id.recycle_view);

       // listView = new ArrayList<AudioModel>(this,android.R.layout.)




    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder holder, int position) {
        AudioModel songData = songList.get(position);
        holder.titleTextView.setText(songData.getTitle());
        if (myMediaPlayer.currentIndex==position){
            holder.titleTextView.setTextColor(Color.parseColor("#000000"));
        }
        else {holder.titleTextView.setTextColor(Color.parseColor("#FFFFFFFF"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigate to other activity
                myMediaPlayer.getInstance().reset();
                myMediaPlayer.currentIndex = position;
                Intent intent = new Intent(context, MusicPlayerActivity.class);
                intent.putExtra("LIST", songList);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
       holder.itemView.findViewById(R.id.favouraitbt).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               if(Imagedisplaying) {
                   holder.imagebutton.setImageResource(R.drawable.ic_baseline_favorite_24);
                   Log.d("demo","Added to favouraites"+position);
                   Imagedisplaying = false;
               }else {
                   holder.imagebutton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                   Imagedisplaying = true;
               }


               //AudioModel = new AudioModel(favlist.get(songData),)




           }
       });


    }

    @Override
    public int getItemCount() {
        return songList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView;
        ImageView iconImageView;
        ImageButton imagebutton;
        public ViewHolder (View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.music_title_text);
            iconImageView = itemView.findViewById(R.id.icon_view);
            imagebutton = itemView.findViewById(R.id.favouraitbt);
        }
    }

 /*   @Override
    public Filter getFilter() {
        return songfilter;
    }
    private Filter songfilter = new Filter() {


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        }
    };*/

    public void filterList(ArrayList<AudioModel> filteredList){
        songList = filteredList;
        notifyDataSetChanged();
    }
    public void Favor(ArrayList<AudioModel> FavoriteList){
        songList = FavoriteList;
        notifyDataSetChanged();
    }
}
