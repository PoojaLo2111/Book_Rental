package com.example.bookrental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ToRentAdapter extends RecyclerView.Adapter<ToRentAdapter.ViewHolder> {

    private List<ToRentItemModel> toRentItemModelList;

    public ToRentAdapter(List<ToRentItemModel> toRentItemModelList) {
        this.toRentItemModelList = toRentItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_rent_item_layout,parent,false);
        return new ToRentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = toRentItemModelList.get(position).getToRentBookImage();
        String title = toRentItemModelList.get(position).getToRentBookTital();
        String originalPrice = toRentItemModelList.get(position).getToRentOriginalPrice();
        String rentalPrice = toRentItemModelList.get(position).getToRentBookRentalPrice();
        String rentalTime = toRentItemModelList.get(position).getToRentBookRentTime();

        holder.setDate(resource, title, originalPrice,rentalPrice,rentalTime);
    }

    @Override
    public int getItemCount() {
        return toRentItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productOriginalPrice;
        private TextView productRentalPrice;
        private TextView productRentalTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.to_rent_book_image);
            productName = itemView.findViewById(R.id.to_rent_book_title);
            productOriginalPrice = itemView.findViewById(R.id.to_rent_book_original_price);
            productRentalPrice = itemView.findViewById(R.id.to_rent_book_rental_price);
            productRentalTime = itemView.findViewById(R.id.to_rent_book_rent_time);
        }

        private void setDate(int resource,String title,String orignalPrice, String rentalPrice, String rentalTime){
            productImage.setImageResource(resource);
            productName.setText(title);
            productOriginalPrice.setText(orignalPrice);
            productRentalPrice.setText(rentalPrice);
            productRentalTime.setText(rentalTime);
        }
    }
}
