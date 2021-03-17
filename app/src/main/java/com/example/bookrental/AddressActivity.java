package com.example.bookrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends AppCompatActivity {

    private RecyclerView addressRecyclerView;
    private Button addAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        addAddress = findViewById(R.id.add_new_address_btn);
        addressRecyclerView = findViewById(R.id.addresses_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        addressRecyclerView.setLayoutManager(layoutManager);

        List<AddressModel> addressModelList = new ArrayList<>();
        addressModelList.add(new AddressModel("Pooja","asdfghjkert","123456"));
        addressModelList.add(new AddressModel("Pooja","asdfghjkert","123456"));
        addressModelList.add(new AddressModel("Pooja","asdfghjkert","123456"));
        addressModelList.add(new AddressModel("Pooja","asdfghjkert","123456"));
        addressModelList.add(new AddressModel("Pooja","asdfghjkert","123456"));
        addressModelList.add(new AddressModel("Pooja","asdfghjkert","123456"));
        addressModelList.add(new AddressModel("Pooja","asdfghjkert","123456"));


        AddressAdapter addressAdapter = new AddressAdapter(addressModelList);
        addressRecyclerView.setAdapter(addressAdapter);
        addressAdapter.notifyDataSetChanged();

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class).putExtra("INTENT","manage"));
            }
        });
    }
}