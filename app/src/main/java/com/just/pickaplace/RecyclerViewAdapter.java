package com.just.pickaplace;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private  ArrayList<Business> bObjects = new ArrayList<Business>();
    private ArrayList<String> mbusinessNames = new ArrayList<String>();
    private ArrayList<String> mImages = new ArrayList<String>();
    private ArrayList<String> mCosts = new ArrayList<String>();
    private ArrayList<String> mRatings = new ArrayList<String>();
    private ArrayDeque<String> top3 = new ArrayDeque<String>();


    private Context mContext;

    private boolean isChecked;


    public RecyclerViewAdapter(ArrayList<Business> bObjects, ArrayList<String> mbusinessNames, ArrayList<String> mCosts, ArrayList<String> mRatings, ArrayList<String> mImages, Context mContext) {
        this.mbusinessNames = mbusinessNames;
        this.bObjects = bObjects;
        this.mImages = mImages;
        this.mContext = mContext;
        this.mCosts = mCosts;
        this.mRatings = mRatings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called +1 item");

        Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.image);

        holder.businessName.setText(mbusinessNames.get(position));
        holder.cost.setText(mCosts.get(position));
        holder.rating.setText(mRatings.get(position));

        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mbusinessNames.get(position));
                Toast.makeText(mContext,mbusinessNames.get(position), Toast.LENGTH_SHORT).show();

                if (!isChecked) {
                    holder.businessName.setTextColor(Color.RED);
                    isChecked = true;
                }
                else {
                    holder.businessName.setTextColor(Color.WHITE);
                    isChecked = false;
                }

                if (top3.size() < 3){
                    top3.add(mbusinessNames.get(position));
                }
                else{
                    top3.remove();
                    top3.add(mbusinessNames.get(position));
                }


            }
        });


    }

    public ArrayDeque<String> getTop(){
        return top3;
    }

    public ArrayList<Business> getbObjects() {
        return bObjects;
    }

    @Override
    public int getItemCount() {
        return mbusinessNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView businessName;
        RelativeLayout parent_layout;
        TextView rating;
        TextView cost;

        public ViewHolder(View itemView){
            super(itemView);
            image = itemView.findViewById(R.id.image);
            cost = itemView.findViewById(R.id.cost);
            rating = itemView.findViewById(R.id.rating);
            businessName = itemView.findViewById(R.id.businessName);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }

    }
}
