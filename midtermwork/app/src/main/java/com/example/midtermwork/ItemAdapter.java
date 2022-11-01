package com.example.midtermwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{
    private List<Item> itemData;

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(itemData.get(position));
    }

    public void setItemData(List<Item> itemData) {
        this.itemData = itemData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView contents;
        ImageView menuImage;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_board_title);
            contents = (TextView) itemView.findViewById(R.id.item_board_content);
            menuImage = (ImageView) itemView.findViewById(R.id.menuImage);
        }
        void onBind(Item item){
            title.setText(item.getTitle());
            contents.setText(item.getContents());
            menuImage.setImageResource(item.getItemImage());
        }
    }

}
