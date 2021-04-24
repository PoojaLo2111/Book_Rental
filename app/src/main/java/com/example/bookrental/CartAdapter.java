package com.example.bookrental;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.bookrental.DatabaseQuerries.firebaseAuth;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartItemViewHolder>{

    private List<CartItemModel> CartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        CartItemModelList = cartItemModelList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
        return new CartAdapter.CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        String resource = CartItemModelList.get(position).getCartBookImage();
        String title = CartItemModelList.get(position).getCartBookTital();
        String rentalprice = CartItemModelList.get(position).getCartBookRentalPrice();
        String originalprice = CartItemModelList.get(position).getCartBookOriginalPrice();
        String rentalTime = CartItemModelList.get(position).getCartBookRentTime();

        holder.setItemDetails(resource, title, rentalprice,originalprice,rentalTime);
    }

    @Override
    public int getItemCount() {
        return CartItemModelList.size();
    }

    public class CartItemViewHolder  extends RecyclerView.ViewHolder {
        private ImageView bookImage;
        private TextView bookTitle;
        private TextView bookOriginalPrice;
        private TextView bookRentalPrice;
        private TextView bookRentTime;
        private Button removecart;
        public CartItemViewHolder(@NonNull final View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.cart_book_image);
            bookTitle = itemView.findViewById(R.id.cart_book_title);
            bookOriginalPrice = itemView.findViewById(R.id.cart_book_original_price);
            bookRentalPrice = itemView.findViewById(R.id.cart_book_rental_price);
            bookRentTime = itemView.findViewById(R.id.cart_book_rent_time);
            removecart = itemView.findViewById(R.id.remove_book);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    productDetailsIntent.putExtra("cartbookId", getAbsoluteAdapterPosition()+1);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }
        private void setItemDetails(String resource, String title, String rentalPrice, String originalPrice, String time){
            Picasso.with(itemView.getContext())
                    .load(resource)
                    .into(bookImage);
            bookTitle.setText(title);
            bookOriginalPrice.setText(originalPrice);
            bookRentalPrice.setText(rentalPrice);
            bookRentTime.setText(time);
            removecart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"Delete",Toast.LENGTH_SHORT).show();
                    final int position = getAbsoluteAdapterPosition();
                    FirebaseFirestore.getInstance().collection("USERS")
                            .document(firebaseAuth.getCurrentUser().getUid())
                            .collection("CART")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        int i=0;
                                        for (DocumentSnapshot document : task.getResult()){
                                            if (i==position){
                                                String id = document.getString("Bookid");
                                                FirebaseFirestore.getInstance().collection("Books")
                                                        .document(id)
                                                        .update("cart_item",false);
                                                document.getReference().delete();
                                                Log.d(TAG,"cartList"+document.getString("Bookid"));
                                                break;
                                            }else {
                                                i++;
                                            }
                                        }
                                    }
                                }
                            });
                    CartItemModelList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }
}

/*public class CartAdapter extends RecyclerView.Adapter {

    private List<CartItemModel>CartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        CartItemModelList = cartItemModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cartItemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout,parent,false);
        return new cartItemViewHolder(cartItemview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int resource = CartItemModelList.get(position).getCartBookImage();
        String title = CartItemModelList.get(position).getCartBookTital();
        String rentalprice = CartItemModelList.get(position).getCartBookRentalPrice();
        String originalprice = CartItemModelList.get(position).getCartBookOriginalPrice();
        String rentalTime = CartItemModelList.get(position).getCartBookRentTime();

        holder.setDate(resource, title, rentalprice,originalprice,rentalTime);
    }

    @Override
    public int getItemCount() {
        return CartItemModelList.size();
    }

    class cartItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView bookImage;
        private TextView bookTitle;
        private TextView bookOriginalPrice;
        private TextView bookRentalPrice;
        private TextView bookRentTime;

        public cartItemViewHolder(@NonNull final View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.cart_book_image);
            bookTitle = itemView.findViewById(R.id.cart_book_title);
            bookOriginalPrice = itemView.findViewById(R.id.cart_book_original_price);
            bookRentalPrice = itemView.findViewById(R.id.cart_book_rental_price);
            bookRentTime = itemView.findViewById(R.id.cart_book_rent_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }

        private void setItemDetails(int resource,String title,String rentalPrice,String originalPrice, String time){
            bookImage.setImageResource(resource);
            bookTitle.setText(title);
            bookOriginalPrice.setText(originalPrice);
            bookRentalPrice.setText(rentalPrice);
            bookRentTime.setText(time);
        }
    }
}*/
