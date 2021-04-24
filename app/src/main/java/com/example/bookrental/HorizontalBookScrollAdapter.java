package com.example.bookrental;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HorizontalBookScrollAdapter extends RecyclerView.Adapter<HorizontalBookScrollAdapter.ViewHolder>{

    //private OnListItemClick onListItemClick;

    private List<HorizontalBookScrollModel> horizontalBookScrollModelList;

    public HorizontalBookScrollAdapter(List<HorizontalBookScrollModel> horizontalBookScrollModelList/*, OnListItemClick onListItemClick*/) {
        this.horizontalBookScrollModelList = horizontalBookScrollModelList;
        //this.onListItemClick = onListItemClick;
    }

    @NonNull
    @Override
    public HorizontalBookScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalBookScrollAdapter.ViewHolder holder, int position) {
        String resouce = horizontalBookScrollModelList.get(position).getBookImage();
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

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{

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
                    productDetailsIntent.putExtra("bookId", getAbsoluteAdapterPosition()+1);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });

        }

        private void setBookImage(String resource){
            //Picasso.get().load(resource).into(bookImage);
            Picasso.with(itemView.getContext())
                    .load(resource)
                    .into(bookImage);
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

        /*@Override
        public void onClick(View view) {
            onListItemClick.onItemClick(, getAdapterPosition());
        }*/
    }

    /*public interface OnListItemClick {
        void onItemClick(DocumentSnapshot snapshot, int position);
    }*/
}
