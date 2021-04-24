package com.example.bookrental;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private TextView mostlikeslayouttitle;
    private Button mostlikesviewallbtn;
    private RecyclerView mostlikesRecyclerView;
    private FirebaseFirestore firebaseFirestore;
    private List<HorizontalBookScrollModel> horizontalBookScrollModelList;
    private HorizontalBookScrollAdapter horizontalBookScrollAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mostlikeslayouttitle = view.findViewById(R.id.h_s_l_title);
        mostlikesviewallbtn = view.findViewById(R.id.h_s_l_button);
        mostlikesRecyclerView = view.findViewById(R.id.h_s_l_recyclerview);

        mostlikesviewallbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(getActivity(), ViewAllActivity.class);
                startActivity(mainIntent);
            }
        });

        horizontalBookScrollModelList = new ArrayList<>();
        horizontalBookScrollAdapter = new HorizontalBookScrollAdapter(horizontalBookScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mostlikesRecyclerView.setLayoutManager(linearLayoutManager);
        mostlikesRecyclerView.setAdapter(horizontalBookScrollAdapter);

        FirebaseFirestore.getInstance().collection("Books").limit(6)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        String image,name,price,time,timetype;
                        //horizontalBookScrollModelList.add(new HorizontalBookScrollModel(
                                image = document.getString("image0");
                                name = document.getString("BookName");
                                price = document.getString("BookRentalPrice");
                                time = document.getString("BookRentalTime");
                                timetype = document.getString("BookRentalTimeType");
                        //));
                        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(image,name,"Rs. "+price+" /-",time+" "+timetype));
                    }
                    horizontalBookScrollAdapter.notifyDataSetChanged();
                    Log.d(TAG, "pooja" + horizontalBookScrollAdapter.getItemCount());
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
        return view;
    }
}