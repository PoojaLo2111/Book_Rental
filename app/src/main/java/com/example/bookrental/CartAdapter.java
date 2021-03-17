package com.example.bookrental;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.rpc.context.AttributeContext;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel>CartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        CartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (CartItemModelList.get(position).getType()){
            case 0:
                return CartItemModel.Cart_Item;
            /*case 1:
                return CartItemModel.Total_Amount;*/
            default:
                return -1;
        }
        //return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case CartItemModel.Cart_Item:
                View cartItemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
                return new cartItemViewHolder(cartItemview);
            default:
                return null;
        }
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (CartItemModelList.get(position).getType()){
            case CartItemModel.Cart_Item:
                int resource = CartItemModelList.get(position).getBookImage();
                String title = CartItemModelList.get(position).getBook_Tital();
                String price = CartItemModelList.get(position).getBook_price();

                ((cartItemViewHolder)holder).setItemDetails(resource,title,price);
                break;
            default:
                return;
        }
    }

    @Override
    public int getItemCount() {
        return CartItemModelList.size();
    }

    class cartItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView bookImage;
        private TextView bookTitle;
        private TextView bookPrice;

        public cartItemViewHolder(@NonNull final View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.book_image);
            bookTitle = itemView.findViewById(R.id.book_title);
            bookPrice = itemView.findViewById(R.id.book_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
        private void setItemDetails(int resource,String title,String price){
            bookImage.setImageResource(resource);
            bookTitle.setText(title);
            bookPrice.setText(price);
        }
    }
    /*class cartTotalAmountViewHolder extends RecyclerView.ViewHolder{
        public cartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }*/
}
