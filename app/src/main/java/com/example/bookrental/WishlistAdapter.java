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
        String resource = wishlistItemModelList.get(position).getWishlistBookImage();
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
        private Button productRemove;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.wishlist_book_image);
            productName = itemView.findViewById(R.id.wishlist_book_title);
            productOriginalPrice = itemView.findViewById(R.id.wishlist_book_original_price);
            productRentalPrice = itemView.findViewById(R.id.wishlist_book_rental_price);
            productRentalTime = itemView.findViewById(R.id.wishlist_book_rent_time);
            productRemove = itemView.findViewById(R.id.wishlist_remove_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                    productDetailsIntent.putExtra("wishlistbookId", getAbsoluteAdapterPosition()+1);
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
            productRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"Delete",Toast.LENGTH_SHORT).show();
                    final int position = getAbsoluteAdapterPosition();
                    FirebaseFirestore.getInstance().collection("USERS")
                            .document(firebaseAuth.getCurrentUser().getUid())
                            .collection("WISHLIST")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        int i=0;
                                        for (DocumentSnapshot document : task.getResult()){
                                            if (i==position){
                                                document.getReference().delete();
                                                Log.d(TAG,"wishlist"+document.getString("Bookid"));
                                                break;
                                            }else {
                                                i++;
                                            }
                                        }
                                    }
                                }
                            });
                    wishlistItemModelList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
