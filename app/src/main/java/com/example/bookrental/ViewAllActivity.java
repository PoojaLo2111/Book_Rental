package com.example.bookrental;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView viewAllRecycleView;
    private List<ProductItemModel> productItemModelList;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        viewAllRecycleView = findViewById(R.id.viewallrecyclerView);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Most Liked Book");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productItemModelList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewAllRecycleView.setLayoutManager(layoutManager);
        final ViewAllProductItemAdapter viewAllProductItemAdapter = new ViewAllProductItemAdapter(productItemModelList);
        viewAllRecycleView.setAdapter(viewAllProductItemAdapter);

        FirebaseFirestore.getInstance().collection("Books")
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

                                productItemModelList.add(new ProductItemModel(image,name,"Rs. "+rentalprice+" /-","(Rs."+originalprice+"/-)",time+" "+timetype));
                            }
                            viewAllProductItemAdapter.notifyDataSetChanged();
                        }
                    }
                });


        /*productItemModelList.add(new ProductItemModel(R.drawable.book1,"DBMS1","Rs.500","(Rs.300/-)","4 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book2,"DBMS2","Rs.600","(Rs.400/-)","6 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book3,"DBMS3","Rs.400","(Rs.250/-)","2 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book4,"DBMS4","Rs.800","(Rs.500/-)","6 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book1,"DBMS1","Rs.500","(Rs.300/-)","4 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book2,"DBMS2","Rs.600","(Rs.400/-)","6 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book3,"DBMS3","Rs.400","(Rs.250/-)","2 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book4,"DBMS4","Rs.800","(Rs.500/-)","6 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book1,"DBMS1","Rs.500","(Rs.300/-)","4 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book2,"DBMS2","Rs.600","(Rs.400/-)","6 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book3,"DBMS3","Rs.400","(Rs.250/-)","2 Months"));
        productItemModelList.add(new ProductItemModel(R.drawable.book4,"DBMS4","Rs.800","(Rs.500/-)","6 Months"));
        viewAllProductItemAdapter.notifyDataSetChanged();*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}