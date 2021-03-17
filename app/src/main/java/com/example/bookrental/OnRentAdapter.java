package com.example.bookrental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnRentAdapter extends RecyclerView.Adapter<OnRentAdapter.ViewHolder> {

    private List<OnRentItemModel> onRentItemModelList;

    public OnRentAdapter(List<OnRentItemModel> onRentItemModelList) {
        this.onRentItemModelList = onRentItemModelList;
    }

    @NonNull
    @Override
    public OnRentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.on_rent_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnRentAdapter.ViewHolder holder, int position) {
        int resource = onRentItemModelList.get(position).getProductImage();
        String title = onRentItemModelList.get(position).getProductTitle();
        String lastdate = onRentItemModelList.get(position).getProductLastDate();

        holder.setDate(resource, title, lastdate);
    }

    @Override
    public int getItemCount() {
        return onRentItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productLastDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.on_rent_item_image);
            productTitle = itemView.findViewById(R.id.on_rent_item_title);
            productLastDate = itemView.findViewById(R.id.on_rent_item_last_date);
        }

        private void setDate(int resource,String title,String lastdate){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            productLastDate.setText(lastdate);
        }
    }
}
