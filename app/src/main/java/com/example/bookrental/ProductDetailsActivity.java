package com.example.bookrental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    //private TabLayout viewPagerIndicator;
    private FloatingActionButton addToFavouritetBtn;
    private static boolean alreadyAddedToFavourite = false;
    //private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        //viewPagerIndicator = findViewById(R.id.view_pager_indicator);
        addToFavouritetBtn = findViewById(R.id.add_to_favourite);
        //navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.book1);
        productImages.add(R.drawable.book2);
        productImages.add(R.drawable.book3);
        productImages.add(R.drawable.book4);
        productImages.add(R.drawable.book5);

        productImagesAdupter productImagesAdepter = new productImagesAdupter(productImages);
        productImagesViewPager.setAdapter(productImagesAdepter);
        //viewPagerIndicator.setupWithViewPager(productImagesViewPager,true);
        addToFavouritetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alreadyAddedToFavourite){
                    alreadyAddedToFavourite = false;
                    addToFavouritetBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                }else{
                    alreadyAddedToFavourite = true;
                    addToFavouritetBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorred));
                    //addToFavouritetBtn.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }else if(id == R.id.main_search){
            return true;
        }else if(id == R.id.main_cart){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.main_search){
            //search
            return true;
        }else if(id == R.id.main_notification){
            //notification
            return true;
        }else if(id == R.id.main_cart){
            navController.navigate(R.id.cartFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}