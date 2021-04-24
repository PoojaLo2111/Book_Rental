package com.example.bookrental;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

import static com.example.bookrental.DatabaseQuerries.firebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToRentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToRentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToRentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToRentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ToRentFragment newInstance(String param1, String param2) {
        ToRentFragment fragment = new ToRentFragment();
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

    private RecyclerView toRentRecycleView;
    private LinearLayout addNewBook;
    private ToRentAdapter toRentAdapter;
    private List<ToRentItemModel> toRentItemModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_rent,container,false);
        toRentRecycleView = view.findViewById(R.id.to_rent_recycleView);
        addNewBook = view.findViewById(R.id.add_new_book);

        addNewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(),AddBookActivity.class));
            }
        });

        toRentItemModelList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        toRentRecycleView.setLayoutManager(layoutManager);
        toRentAdapter = new ToRentAdapter(toRentItemModelList);
        toRentRecycleView.setAdapter(toRentAdapter);

        FirebaseFirestore.getInstance().collection("Books")
                .whereEqualTo("OwenerId",firebaseAuth.getCurrentUser().getUid())
                .whereEqualTo("taken",false)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String image,name,originalprice,rentalprice,time,timetype;
                                //horizontalBookScrollModelList.add(new HorizontalBookScrollModel(
                                image = document.getString("image0");
                                name = document.getString("BookName");
                                originalprice = document.getString("BookOriginalPrice");
                                rentalprice = document.getString("BookRentalPrice");
                                time = document.getString("BookRentalTime");
                                timetype = document.getString("BookRentalTimeType");

                                toRentItemModelList.add(new ToRentItemModel(image,name,"(Rs."+rentalprice+"/-)","Rs. "+originalprice+" /-",time+" "+timetype));
                            }
                            toRentAdapter.notifyDataSetChanged();
                        }
                    }
                });

        /*toRentItemModelList.add(new ToRentItemModel(R.drawable.book1,"DBMS1","Rs.500","(Rs.300/-)","4 Months"));
        toRentItemModelList.add(new ToRentItemModel(R.drawable.book2,"DBMS2","Rs.600","(Rs.400/-)","6 Months"));
        toRentItemModelList.add(new ToRentItemModel(R.drawable.book3,"DBMS3","Rs.400","(Rs.250/-)","2 Months"));
        toRentItemModelList.add(new ToRentItemModel(R.drawable.book4,"DBMS4","Rs.800","(Rs.500/-)","6 Months"));*/
        return view;
    }

}