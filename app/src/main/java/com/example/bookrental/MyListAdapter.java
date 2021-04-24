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

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.Viewhoder> {

    private List<MyListItemModel> myListItemModelList;
    private MyListFragment myListFragment;

    public MyListAdapter(MyListFragment myListFragment, List<MyListItemModel> myListItemModelList) {
        this.myListItemModelList = myListItemModelList;
        this.myListFragment = myListFragment;
    }

    @NonNull
    @Override
    public MyListAdapter.Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item_layout,parent,false);
        return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.Viewhoder holder, int position) {
        String resource = myListItemModelList.get(position).getProductImage();
        String title = myListItemModelList.get(position).getProductTitle();
        String lastdate = myListItemModelList.get(position).getProductLastDate();

        holder.setDate(resource, title, lastdate);
    }

    @Override
    public int getItemCount() {
        return myListItemModelList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productLastDate;

        public Viewhoder(@NonNull final View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.my_list_item_image);
            productTitle = itemView.findViewById(R.id.my_list_item_title);
            productLastDate = itemView.findViewById(R.id.my_list_item_last_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog myListDialog = new Dialog(myListFragment.getContext());
                    myListDialog.setContentView(R.layout.my_list_dialog);
                    myListDialog.setCancelable(true);
                    final TextView bookname = myListDialog.findViewById(R.id.dialog_book_name);
                    final TextView bookrentalprice = myListDialog.findViewById(R.id.dialog_rental_price);
                    final TextView bookoriginalprice = myListDialog.findViewById(R.id.dialog_original_price);
                    final TextView bookstartingdate = myListDialog.findViewById(R.id.dialog_starting_date);
                    final TextView bookownername = myListDialog.findViewById(R.id.dialog_owner_name);
                    final TextView bookowneremail = myListDialog.findViewById(R.id.dialog_owner_email);
                    final TextView bookaddress = myListDialog.findViewById(R.id.dialog_owner_address);
                    Log.d(TAG,"abcdefgh");
                    final int position = getAbsoluteAdapterPosition();
                    FirebaseFirestore.getInstance().collection("Rental")
                            .whereEqualTo("ReceiverID",firebaseAuth.getCurrentUser().getUid())
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
                                                bookaddress.setText(document.getString("Address"));
                                                String ownerid = document.getString("OwenerId");

                                                FirebaseFirestore.getInstance().collection("USERS")
                                                        .document(ownerid)
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
