package com.example.bookrental;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.ViewHolder> {

    private List<WishlistItemModel> wishlistItemModelList;

    public WishlistAdapter(List<WishlistItemModel> wishlistItemModelList) {
        this.wishlistItemModelList = wishlistItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout,parent,false);
        return new WishlistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int resource = wishlistItemModelList.get(position).getWishlistBookImage();
        String title = wishlistItemModelList.get(position).getWishlistBookTital();
        String originalPrice = wishlistItemModelList.get(position).getWishlistBookOriginalPrice();
        String rentalPrice = wishlistItemModelList.get(position).getWishlistBookRentalPrice();
        String rentalTime = wishlistItemModelList.get(position).getWishlistBookRentTime();

        holder.setDate(resource, title, originalPrice,rentalPrice,rentalTime);
    }

    @Override
    public int getItemCount() {
        return wishlistItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productOriginalPrice;
        private TextView productRentalPrice;
        private TextView productRentalTime;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.wishlist_book_image);
            productName = itemView.findViewById(R.id.wishlist_book_title);
            productOriginalPrice = itemView.findViewById(R.id.wishlist_book_original_price);
            productRentalPrice = itemView.findViewById(R.id.wishlist_book_rental_price);
            productRentalTime = itemView.findViewById(R.id.wishlist_book_rent_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
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
