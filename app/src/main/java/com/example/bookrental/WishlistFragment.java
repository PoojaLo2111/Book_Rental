package com.example.bookrental;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.bookrental.DatabaseQuerries.firebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishlistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishlistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WishlistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishlistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishlistFragment newInstance(String param1, String param2) {
        WishlistFragment fragment = new WishlistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private RecyclerView wishlistRecycleView;
    private List<WishlistItemModel> wishlistItemModelList;
    private WishlistAdapter wishlistAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wishlist,container,false);
        wishlistRecycleView = view.findViewById(R.id.wishlist_recycleview);

        wishlistItemModelList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishlistRecycleView.setLayoutManager(layoutManager);
        wishlistAdapter = new WishlistAdapter(wishlistItemModelList);
        wishlistRecycleView.setAdapter(wishlistAdapter);

        FirebaseFirestore.getInstance().collection("USERS")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("WISHLIST")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document1 : task.getResult()) {

                                String BookID = document1.getString("Bookid");

                                FirebaseFirestore.getInstance().collection("Books")
                                        .document(BookID)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    Log.d(TAG,"bookidcart"+document);
                                                    String image,name,originalprice,rentalprice,time,timetype;
                                                    //horizontalBookScrollModelList.add(new HorizontalBookScrollModel(
                                                    image = document.getString("image0");
                                                    name = document.getString("BookName");
                                                    originalprice = document.getString("BookOriginalPrice");
                                                    rentalprice = document.getString("BookRentalPrice");
                                                    time = document.getString("BookRentalTime");
                                                    timetype = document.getString("BookRentalTimeType");
                                                    wishlistItemModelList.add(new WishlistItemModel(image,name,"Rs."+rentalprice+"/-","(Rs. "+originalprice+" /-)",time+" "+timetype));
                                                }
                                                wishlistAdapter.notifyDataSetChanged();
                                            }
                                        });
                            }
                        }
                    }
                });




        /*wishlistItemModelList.add(new WishlistItemModel(R.drawable.book1,"DBMS1","Rs.500","(Rs.300/-)","4 Months"));
        wishlistItemModelList.add(new WishlistItemModel(R.drawable.book2,"DBMS2","Rs.600","(Rs.400/-)","6 Months"));
        wishlistItemModelList.add(new WishlistItemModel(R.drawable.book3,"DBMS3","Rs.400","(Rs.250/-)","2 Months"));
        wishlistItemModelList.add(new WishlistItemModel(R.drawable.book4,"DBMS4","Rs.800","(Rs.500/-)","6 Months"));*/
        return view;
    }
}