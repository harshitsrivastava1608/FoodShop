package com.example.networking;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopViewHolder>{
    List<Shop> shopList;

    public ShopAdapter(List<Shop> shopList) {
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent,false);
        return new ShopViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        Shop shop=shopList.get(position);
        holder.name.setText(shop.name);
        holder.cost.setText(shop.cost);
        holder.rating.setText(shop.rating);
        Log.d("adapt123",shop.rating+"*/"+shop.image);
        Picasso.get().load(shop.image.toString()).into(holder.shopImage);
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public class ShopViewHolder extends RecyclerView.ViewHolder{
        TextView name,cost,rating;
        ImageView shopImage;
        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tvname);
            cost=itemView.findViewById(R.id.tvcost);
            rating=itemView.findViewById(R.id.tvrating);
            shopImage=itemView.findViewById(R.id.shopImage);
        }
    }
}
