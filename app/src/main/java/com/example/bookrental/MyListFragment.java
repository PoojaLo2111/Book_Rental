package com.example.bookrental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyListFragment newInstance(String param1, String param2) {
        MyListFragment fragment = new MyListFragment();
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

    private RecyclerView myListRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_list, container, false);
        myListRecyclerView =view.findViewById(R.id.my_list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myListRecyclerView.setLayoutManager(layoutManager);

        List<MyListItemModel> myListItemModelList = new ArrayList<>();
        myListItemModelList.add(new MyListItemModel(R.drawable.book1,"DBMS1","Last Date Mon,1 Jan 2021"));
        myListItemModelList.add(new MyListItemModel(R.drawable.book2,"DBMS2","Last Date Mon,2 Jan 2021"));
        myListItemModelList.add(new MyListItemModel(R.drawable.book3,"DBMS3","Last Date Mon,3 Jan 2021"));
        myListItemModelList.add(new MyListItemModel(R.drawable.book4,"DBMS4","Last Date Mon,4 Jan 2021"));
        myListItemModelList.add(new MyListItemModel(R.drawable.book5,"DBMS5","Last Date Mon,5 Jan 2021"));
        myListItemModelList.add(new MyListItemModel(R.drawable.book6,"DBMS6","Last Date Mon,6 Jan 2021"));
        myListItemModelList.add(new MyListItemModel(R.drawable.book7,"DBMS7","Last Date Mon,7 Jan 2021"));

        MyListAdapter myListAdapter = new MyListAdapter(myListItemModelList);
        myListRecyclerView.setAdapter(myListAdapter);
        myListAdapter.notifyDataSetChanged();
        return view;
    }
}