package org.college.android.itomer.recyclerviewswipetoremoveandmove;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataViewHolder> {

    @Bind(R.id.ivItem)
    ImageView ivItem;
    //private data members:
    private List<Contact> data = Collections.emptyList();

    //ctor:
    public RecyclerAdapter(List<Contact> data) {
        this.data = data;
    }



    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        Contact item = data.get(position);

        /*Picasso.with(holder.tvName.getContext()).
                load(item.getImageURL()).
                into(holder.ivItem);*/
        //holder.ivItem.
        holder.ivItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked item: "+position);
            }
        });
        holder.tvName.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void moveItem(int fromPos, int toPos) {
        Contact from = data.get(fromPos);
        Contact to = data.set(toPos, from);
        data.set(fromPos, to);
        notifyItemMoved(fromPos, toPos);
    }

    public void removeItem(int position) {
        if (position < 0) return;

        data.remove(position);
        notifyItemRemoved(position);
    }

    public void addItems(List<Contact> movies) {
        data.addAll(0, movies);
        notifyItemRangeInserted(0, movies.size());
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ivItem)
        ImageView ivItem;
        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvDescription)
        TextView tvDescription;
        @Bind(R.id.cardView)
        CardView cardView;


        public DataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
