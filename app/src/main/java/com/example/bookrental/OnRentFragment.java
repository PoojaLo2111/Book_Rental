package com.example.bookrental;

import android.os.Bundle;
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

import static com.example.bookrental.DatabaseQuerries.firebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnRentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnRentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnRentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnRentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnRentFragment newInstance(String param1, String param2) {
        OnRentFragment fragment = new OnRentFragment();
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

    private RecyclerView onRentRecyclerView;
    private List<OnRentItemModel> onRentItemModelList;
    private OnRentAdapter onRentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_on_rent, container, false);
        onRentRecyclerView =view.findViewById(R.id.on_rent_recycler_view);

        onRentItemModelList = new ArrayList<>();
        onRentAdapter = new OnRentAdapter(OnRentFragment.this,onRentItemModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        onRentRecyclerView.setLayoutManager(layoutManager);
        onRentRecyclerView.setAdapter(onRentAdapter);

        FirebaseFirestore.getInstance().collection("Rental")
                .whereEqualTo("OwenerId",firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String image,name,enddate;
                                //horizontalBookScrollModelList.add(new HorizontalBookScrollModel(
                                image = document.getString("BookImage");
                                name = document.getString("BookTitle");
                                enddate = document.getString("EndDate");
                                onRentItemModelList.add(new OnRentItemModel(image,name,"Last Date : "+enddate));
                            }
                            onRentAdapter.notifyDataSetChanged();
                        }
                    }
                });

        /*onRentItemModelList.add(new OnRentItemModel(R.drawable.book1,"DBMS1","Last Date Mon,1 Jan 2021"));
        onRentItemModelList.add(new OnRentItemModel(R.drawable.book2,"DBMS2","Last Date Mon,2 Jan 2021"));
        onRentItemModelList.add(new OnRentItemModel(R.drawable.book3,"DBMS3","Last Date Mon,3 Jan 2021"));
        onRentItemModelList.add(new OnRentItemModel(R.drawable.book4,"DBMS4","Last Date Mon,4 Jan 2021"));
        onRentItemModelList.add(new OnRentItemModel(R.drawable.book5,"DBMS5","Last Date Mon,5 Jan 2021"));
        onRentItemModelList.add(new OnRentItemModel(R.drawable.book6,"DBMS6","Last Date Mon,6 Jan 2021"));
        onRentItemModelList.add(new OnRentItemModel(R.drawable.book7,"DBMS7","Last Date Mon,7 Jan 2021"));*/

        return view;
    }
}