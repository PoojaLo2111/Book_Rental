package com.example.bookrental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

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

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mostlikeslayouttitle = view.findViewById(R.id.h_s_l_title);
        mostlikesviewallbtn = view.findViewById(R.id.h_s_l_button);
        mostlikesRecyclerView = view.findViewById(R.id.h_s_l_recyclerview);

        List<HorizontalBookScrollModel> horizontalBookScrollModelList = new ArrayList<>();
        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(R.drawable.book1,"Databse1","Rs.100","6 Months"));
        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(R.drawable.book2,"Databse2","Rs.150","6 Months"));
        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(R.drawable.book3,"Databse3","Rs.200","8 Months"));
        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(R.drawable.book4,"Databse4","Rs.170","7 Months"));
        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(R.drawable.book5,"Databse5","Rs.90","5 Months"));
        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(R.drawable.book6,"Databse6","Rs.200","1 Year"));
        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(R.drawable.book7,"Databse7","Rs.50","3 Months"));
        horizontalBookScrollModelList.add(new HorizontalBookScrollModel(R.drawable.book8,"Databse8","Rs.100","6 Months"));

        HorizontalBookScrollAdapter horizontalBookScrollAdapter = new HorizontalBookScrollAdapter(horizontalBookScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mostlikesRecyclerView.setLayoutManager(linearLayoutManager);
        mostlikesRecyclerView.setAdapter(horizontalBookScrollAdapter);
        horizontalBookScrollAdapter.notifyDataSetChanged();
        return view;
        
    }
}