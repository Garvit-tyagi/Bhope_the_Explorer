package com.example.bhopetheexplorer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class exploreInsideAdapter extends RecyclerView.Adapter<exploreInsideAdapter.ExampleViewHolder> {

    private Context mcontext;
    private ArrayList<recyclerview_item>  mList;
    private OnIemClickListener mListener;

    public interface OnIemClickListener{
        void onItemClick( int position);
    }
    public void setOnItemClickListener(OnIemClickListener listener){
        mListener=listener;
    }

    public exploreInsideAdapter(Context context, ArrayList<recyclerview_item> mList){
        mcontext=context;
        this.mList=mList;
    }
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mcontext).inflate(R.layout.recyclerview_item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        recyclerview_item currentItem= mList.get(position);
        String Name=currentItem.getTitle();
        String href=currentItem.getHref();
        String nasa_id=currentItem.getNasa_id();
        String media_type=currentItem.getMedia_type();
        holder.name.setText(Name);
       Picasso.with(mcontext).load(href).fit().centerCrop().placeholder(R.drawable.ic_launcher_foreground).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView name;
        public String nasaId;
        public String mediaType;

        public ExampleViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.item_image_view);
            name=itemView.findViewById(R.id.item_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
