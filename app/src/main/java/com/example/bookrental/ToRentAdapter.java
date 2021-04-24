package com.example.bookrental;

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
        String resource = toRentItemModelList.get(position).getToRentBookImage();
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
        private Button removeBook;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.to_rent_book_image);
            productName = itemView.findViewById(R.id.to_rent_book_title);
            productOriginalPrice = itemView.findViewById(R.id.to_rent_book_original_price);
            productRentalPrice = itemView.findViewById(R.id.to_rent_book_rental_price);
            productRentalTime = itemView.findViewById(R.id.to_rent_book_rent_time);
            removeBook = itemView.findViewById(R.id.remove_book_to_rent);
        }

        private void setDate(String resource, String title, String orignalPrice, String rentalPrice, String rentalTime){
            Picasso.with(itemView.getContext())
                    .load(resource)
                    .into(productImage);
            //productImage.setImageResource(Integer.parseInt(resource));
            productName.setText(title);
            productOriginalPrice.setText(orignalPrice);
            productRentalPrice.setText(rentalPrice);
            productRentalTime.setText(rentalTime);
            removeBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"Delete",Toast.LENGTH_SHORT).show();
                    final int position = getAbsoluteAdapterPosition();
                    FirebaseFirestore.getInstance().collection("Books")
                            .whereEqualTo("OwenerId",firebaseAuth.getCurrentUser().getUid())
                            .whereEqualTo("cart_item",false)
                            .whereEqualTo("taken",false)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        int i=0;
                                        for (DocumentSnapshot document : task.getResult()){
                                            if (i==position){
                                                document.getReference().delete();
                                                Log.d(TAG,"torent"+document.getString("Bookid"));
                                                break;
                                            }else {
                                                i++;
                                            }
                                        }
                                    }
                                }
                            });
                    toRentItemModelList.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
