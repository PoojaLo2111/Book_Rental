package com.example.bookrental;

import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HorizontalBookScrollAdapter extends RecyclerView.Adapter<HorizontalBookScrollAdapter.ViewHolder> {

    private List<HorizontalBookScrollModel> horizontalBookScrollModelList;

    public HorizontalBookScrollAdapter(List<HorizontalBookScrollModel> horizontalBookScrollModelList) {
        this.horizontalBookScrollModelList = horizontalBookScrollModelList;
    }

    @NonNull
    @Override
    public HorizontalBookScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalBookScrollAdapter.ViewHolder holder, int position) {
        int resouce = horizontalBookScrollModelList.get(position).getBookImage();
        String title = horizontalBookScrollModelList.get(position).getBookTitle();
        String price = horizontalBookScrollModelList.get(position).getBookPrice();
        String rentTime = horizontalBookScrollModelList.get(position).getBookRentTime();

        holder.setBookImage(resouce);
        holder.setBookTitle(title);
        holder.setBookPrice(price);
        holder.setBookRentTime(rentTime);
    }

    @Override
    public int getItemCount() {
        return horizontalBookScrollModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView bookImage;
        private TextView bookTitle;
        private TextView bookPrice;
        private TextView bookRentTime;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.h_s_i_image);
            bookTitle = itemView.findViewById(R.id.h_s_i_title);
            bookPrice = itemView.findViewById(R.id.h_s_i_price);
            bookRentTime = itemView.findViewById(R.id.h_s_i_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }

        private void setBookImage(int resource){
            bookImage.setImageResource(resource);
        }
        private void setBookTitle(String title){
            bookTitle.setText(title);
        }
        private void setBookPrice(String price){
            bookPrice.setText(price);
        }
        private void setBookRentTime(String rentTime){
            bookRentTime.setText(rentTime);
        }
    }
}
