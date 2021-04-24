package com.example.bookrental;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static com.example.bookrental.DatabaseQuerries.firebaseAuth;

public class OnRentAdapter extends RecyclerView.Adapter<OnRentAdapter.ViewHolder> {

    private List<OnRentItemModel> onRentItemModelList;
    private OnRentFragment onRentFragment;

    public OnRentAdapter(OnRentFragment onRentFragment, List<OnRentItemModel> onRentItemModelList) {
        this.onRentItemModelList = onRentItemModelList;
        this.onRentFragment = onRentFragment;
    }

    @NonNull
    @Override
    public OnRentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.on_rent_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnRentAdapter.ViewHolder holder, int position) {
        String resource = onRentItemModelList.get(position).getProductImage();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog myListDialog = new Dialog(onRentFragment.getContext());
                    myListDialog.setContentView(R.layout.my_list_dialog);
                    myListDialog.setCancelable(true);
                    final TextView bookname = myListDialog.findViewById(R.id.dialog_book_name);
                    final TextView bookrentalprice = myListDialog.findViewById(R.id.dialog_rental_price);
                    final TextView bookoriginalprice = myListDialog.findViewById(R.id.dialog_original_price);
                    final TextView bookstartingdate = myListDialog.findViewById(R.id.dialog_starting_date);
                    final TextView bookownername = myListDialog.findViewById(R.id.dialog_owner_name);
                    final TextView bookowneremail = myListDialog.findViewById(R.id.dialog_owner_email);
                    final TextView bookaddress = myListDialog.findViewById(R.id.dialog_owner_address);
                    final TextView bookaddresstitle = myListDialog.findViewById(R.id.dialog_book_address_title);
                    TextView bookownernametext = myListDialog.findViewById(R.id.dialog_owner_name_text);
                    TextView bookowneremailtext = myListDialog.findViewById(R.id.dialog_owner_email_text);
                    bookownernametext.setText("Receiver Name");
                    bookowneremailtext.setText("Receiver Email");
                    bookaddresstitle.setVisibility(View.GONE);
                    bookaddress.setVisibility(View.GONE);
                    Log.d(TAG,"abcdefgh");
                    final int position = getAbsoluteAdapterPosition();
                    FirebaseFirestore.getInstance().collection("Rental")
                            .whereEqualTo("OwenerId",firebaseAuth.getCurrentUser().getUid())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        int i = 0;
                                        for (final DocumentSnapshot document : task.getResult()) {
                                            if (i == position) {
                                                bookname.setText(document.getString("BookTitle"));
                                                bookoriginalprice.setText(document.getString("OriginalPrice"));
                                                bookrentalprice.setText(document.getString("BookRentalPrice"));
                                                bookstartingdate.setText(document.getString("StartDate"));
                                                String receiver = document.getString("ReceiverID");

                                                FirebaseFirestore.getInstance().collection("USERS")
                                                        .document(receiver)
                                                        .get()
                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                if (task.isSuccessful()){
                                                                    DocumentSnapshot documentSnapshot = task.getResult();
                                                                    bookownername.setText(documentSnapshot.getString("username"));
                                                                    bookowneremail.setText(documentSnapshot.getString("email"));
                                                                }
                                                            }
                                                        });
                                                break;
                                            } else {
                                                i++;
                                            }
                                        }
                                    }
                                }
                            });

                    myListDialog.getWindow().setLayout(MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    myListDialog.show();
                }
            });

        }

        private void setDate(String resource, String title, String lastdate){
            Picasso.with(itemView.getContext())
                    .load(resource)
                    .into(productImage);
            productTitle.setText(title);
            productLastDate.setText(lastdate);
        }
    }
}
