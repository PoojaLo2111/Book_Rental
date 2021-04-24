package com.example.bookrental;
/*
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.FrameLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout frameLayout;
    private static final int home_fragment = 0;
    private static final int cart_fragment = 1;
    private static int current_fragment;
    private NavigationView navigationView;

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_home){
                    setFragment(new HomeFragment(),home_fragment);
                }else if(id == R.id.favorite) {

                }else if(id == R.id.nav_cart){
                    myCart();
                }else if(id == R.id.nav_list){

                }else if (id == R.id.nav_book){

                }else if(id == R.id.nav_account){

                }else if (id == R.id.nav_signout){

                }
                onBackPressed();
                return true;
            }
        });

        navigationView.getMenu().getItem(0).setChecked(true);
        frameLayout = findViewById(R.id.main_frame_layout);
        setFragment(new HomeFragment(),home_fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.isDrawerOpen(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (current_fragment == home_fragment) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.main_search){
            //search
            return true;
        }else if(id == R.id.main_notification){
            //notification
            return true;
        }else if(id == R.id.main_cart){
            myCart();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void myCart(){
        invalidateOptionsMenu();
        setFragment(new MyCartFragment(),cart_fragment);
        navigationView.getMenu().getItem(2).setChecked(true);
    }

    public void setFragment(Fragment fragment,int fragmentNo){
        current_fragment = fragmentNo;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}*/

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.bookrental.DatabaseQuerries.firebaseAuth;

//import static com.example.bookrental.RegisterActivity.loginsignupstatus;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavController navController;
    public NavigationView navigationView;
    private static final int home_fragment = 0;
    private static final int cart_fragment = 1;
    private static int current_fragment = -1;
    private TextView actionBarTitle;
    private Object MyAccountFragment;
    private Dialog signinDialog;
    private TextView username,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigation();

        /*if(DatabaseQuerries.currentUser == null){
            navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setEnabled(false);
        }else {
            navigationView.getMenu().getItem(navigationView.getMenu().size()-1).setEnabled(true);
        }

        signinDialog = new Dialog(MainActivity.this);
        signinDialog.setContentView(R.layout.signin_signup_dialog);
        signinDialog.setCancelable(true);
        signinDialog.getWindow().setLayout(MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        Button dialogSigninbtn  = signinDialog.findViewById(R.id.sign_in_btn);
        Button dialogSignupbtn = signinDialog.findViewById(R.id.sign_up_btn);

        final Intent intent = new Intent(MainActivity.this,RegisterActivity.class);

        dialogSigninbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signinDialog.dismiss();
                setsignupFragment = false;
                startActivity(intent);
            }
        });

        dialogSignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signinDialog.dismiss();
                setsignupFragment =true;
                startActivity(intent);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Setting Up One Time Navigation
    private void setupNavigation() {

        toolbar = findViewById(R.id.toolbar);
        actionBarTitle = findViewById(R.id.actionbar_title);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        /*Fragment fragment = new Fragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.address_fragment,MyAccountFragment).commit();*/

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);

        username=navigationView.getHeaderView(0).findViewById(R.id.main_name);
        email=navigationView.getHeaderView(0).findViewById(R.id.main_email);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        drawerLayout.closeDrawers();
        int id = menuItem.getItemId();
        switch (id) {
            case R.id.nav_home:
                navController.navigate(R.id.homeFragment);
                break;
            case R.id.nav_wishlist:
                navController.navigate(R.id.mywishlist);
                break;
            case R.id.nav_cart:
                navController.navigate(R.id.cartFragment);
                break;
            case R.id.nav_mylist:
                navController.navigate(R.id.mylistFragment);
                break;
            case R.id.nav_mybook:
                navController.navigate(R.id.mybookActivity);
                break;
            case R.id.nav_account:
                navController.navigate(R.id.myacountFragment);
                break;
            case R.id.nav_signout:
                //singout ftagment
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                finish();
                break;
        }
        return true;
    }

    @Override
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseFirestore.getInstance()
                .collection("USERS")
                .document(firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot snapshot) {
                        username.setText(snapshot.getString("username"));
                        email.setText(snapshot.getString("email"));
                    }
                });
    }
}