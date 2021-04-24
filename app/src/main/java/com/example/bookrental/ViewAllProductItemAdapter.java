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

public class ViewAllProductItemAdapter extends RecyclerView.Adapter<ViewAllProductItemAdapter.ViewHolder> {

    private List<ProductItemModel> productItemModelList;

    public ViewAllProductItemAdapter(List<ProductItemModel> productItemModelList) {
        this.productItemModelList = productItemModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,parent,false);
        return new ViewAllProductItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String resource = productItemModelList.get(position).getViewAllBookImage();
        String title = productItemModelList.get(position).getViewAllBookTitle();
        String originalPrice = productItemModelList.get(position).getViewAllBookOriginalPrice();
        String rentalPrice = productItemModelList.get(position).getViewAllBookRentalPrice();
        String rentalTime = productItemModelList.get(position).getViewAllBookRentalTime();

        holder.setDate(resource, title, originalPrice,rentalPrice,rentalTime);
    }

    @Override
    public int getItemCount() {
        return productItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productName;
        private TextView productOriginalPrice;
        private TextView productRentalPrice;
        private TextView productRentalTime;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.viewall_book_image);
            productName = itemView.findViewById(R.id.viewall_book_title);
            productOriginalPrice = itemView.findViewById(R.id.viewall_book_original_price);
            productRentalPrice = itemView.findViewById(R.id.viewall_book_rental_price);
            productRentalTime = itemView.findViewById(R.id.viewall_book_rent_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    productDetailsIntent.putExtra("bookId", getAbsoluteAdapterPosition()+1);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });

        }

        private void setDate(String resource, String title, String orignalPrice, String rentalPrice, String rentalTime){
            Picasso.with(itemView.getContext())
                    .load(resource)
                    .into(productImage);
            productName.setText(title);
            productOriginalPrice.setText(orignalPrice);
            productRentalPrice.setText(rentalPrice);
            productRentalTime.setText(rentalTime);
        }
    }
}
