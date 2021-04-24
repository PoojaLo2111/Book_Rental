package com.example.bookrental;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.example.bookrental.DatabaseQuerries.firebaseAuth;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView bookName,bookOriginalPrice,bookRentalPrice,bookRentalTime,bookDiscription,bookAddress;
    private static int bookID ;
    private static int cartbookID;
    private static int wishlistID;
    String id;
    private LinearLayout addToCartBtn;
    private TextView addToCartText;
    private ViewPager productImagesViewPager;
    //private TabLayout viewPagerIndicator;
    private FloatingActionButton addToFavouritetBtn;
    private static boolean alreadyAddedToFavourite = false;
    //private NavController navController;
    private boolean cartitem;
    private Button takeNow;
    private boolean takenBook;

    String BookName;
    String BookImage;
    String BookOriginalPrice;
    String BookRentalTime;
    String BookDiscription;
    String BookAddress;
    String BookOwenerid;
    String BookReceiverid;
    String BookRentalPrice;
    String BookRentStartDate;
    String BookRentEndDate;
    String TimeType;

    private FirebaseFirestore firebaseFirestore;
    HorizontalBookScrollModel horizontalBookScrollModel = null;

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
        addToFavouritetBtn = findViewById(R.id.add_to_wishlist_btn);
        //navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        bookName = findViewById(R.id.book_title);
        bookOriginalPrice = findViewById(R.id.rent_original_price);
        bookRentalPrice = findViewById(R.id.book_rental_price);
        bookRentalTime = findViewById(R.id.rent_time);
        bookDiscription = findViewById(R.id.book_discription);
        bookAddress = findViewById(R.id.address_area);
        addToCartBtn = findViewById(R.id.add_to_cart_btn);
        addToCartText = findViewById(R.id.add_to_cart_text);
        takeNow = findViewById(R.id.take_now);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        bookID = getIntent().getIntExtra("bookId",0);
        cartbookID = getIntent().getIntExtra("cartbookId",0);
        wishlistID = getIntent().getIntExtra("wishlistbookId",0);
        //final Object object = getIntent().getSerializableExtra("product");

        final List<String> productImages = new ArrayList<>();
        /*productImages.add(R.drawable.book1);
        productImages.add(R.drawable.book2);
        productImages.add(R.drawable.book3);
        productImages.add(R.drawable.book4);
        productImages.add(R.drawable.book5);*/

        if (bookID != 0){
            FirebaseFirestore.getInstance().collection("Books")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        int i = 1;
                        for (DocumentSnapshot document : task.getResult()) {
                            if(i == bookID){
                                id = document.getId();
                                BookImage = document.getString("image0");
                            /*for (int x = 0; x < (long) document.get("Num"); x++) {
                                productImages.add(document.getString("image"+x));
                                Log.d(TAG, "image " + productImages);
                                //productImages.add(Integer.valueOf(document.get("product_image_" + x).toString()));
                            }*/

                                for (int x = 0; x < (long) document.get("Num"); x++) {
                                    productImages.add(document.getString("image"+x));
                                    Log.d(TAG, "image " + productImages);
                                }
                                productImagesAdupter productImagesAdepter = new productImagesAdupter(productImages);
                                productImagesViewPager.setAdapter(productImagesAdepter);

                                BookName = document.getString("BookName");
                                BookRentalPrice = document.getString("BookRentalPrice");
                                BookOriginalPrice = document.getString("BookOriginalPrice");
                                BookRentalTime = document.getString("BookRentalTime");
                                BookDiscription = document.getString("BookDetails");
                                BookAddress = document.getString("BookAddress");
                                BookOwenerid = document.getString("OwenerId");
                                cartitem = document.getBoolean("cart_item");
                                takenBook = document.getBoolean("taken");
                                TimeType = document.getString("BookRentalTimeType");

                                displayBookDetail();

                                break;
                            } else {
                                i++;
                            }
                        }

                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });

        } else if (cartbookID !=0){
            FirebaseFirestore.getInstance().collection("USERS")
                    .document(firebaseAuth.getCurrentUser().getUid())
                    .collection("CART")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int i = 1;
                                for (DocumentSnapshot document1 : task.getResult()) {
                                    if (i == cartbookID) {
                                        String BookID = document1.getString("Bookid");
                                        FirebaseFirestore.getInstance().collection("Books")
                                                .document(BookID)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            DocumentSnapshot document = task.getResult();
                                                            id = document.getId();
                                                            Log.d(TAG,"Cartid"+document.getId());
                                                            for (int x = 0; x < (long) document.get("Num"); x++) {
                                                                productImages.add(document.getString("image"+x));
                                                                Log.d(TAG, "image " + productImages);
                                                            }
                                                            productImagesAdupter productImagesAdepter = new productImagesAdupter(productImages);
                                                            productImagesViewPager.setAdapter(productImagesAdepter);

                                                            BookName = document.getString("BookName");
                                                            BookRentalPrice = document.getString("BookRentalPrice");
                                                            BookOriginalPrice = document.getString("BookOriginalPrice");
                                                            BookRentalTime = document.getString("BookRentalTime");
                                                            BookDiscription = document.getString("BookDetails");
                                                            BookAddress = document.getString("BookAddress");
                                                            BookOwenerid = document.getString("OwenerId");
                                                            cartitem = document.getBoolean("cart_item");
                                                            takenBook = document.getBoolean("taken");
                                                            TimeType = document.getString("BookRentalTimeType");

                                                            displayBookDetail();
                                                        }
                                                    }
                                                });
                                        break;
                                    } else {
                                        i++;
                                    }
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        } else if (wishlistID != 0){
            FirebaseFirestore.getInstance().collection("USERS")
                    .document(firebaseAuth.getCurrentUser().getUid())
                    .collection("WISHLIST")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                int i = 1;
                                for (DocumentSnapshot document1 : task.getResult()) {
                                    if (i == wishlistID) {
                                        String BookID = document1.getString("Bookid");
                                        FirebaseFirestore.getInstance().collection("Books")
                                                .document(BookID)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            DocumentSnapshot document = task.getResult();
                                                            id = document.getId();
                                                            for (int x = 0; x < (long) document.get("Num"); x++) {
                                                                productImages.add(document.getString("image"+x));
                                                                Log.d(TAG, "image " + productImages);
                                                            }
                                                            productImagesAdupter productImagesAdepter = new productImagesAdupter(productImages);
                                                            productImagesViewPager.setAdapter(productImagesAdepter);

                                                            BookName = document.getString("BookName");
                                                            BookRentalPrice = document.getString("BookRentalPrice");
                                                            BookOriginalPrice = document.getString("BookOriginalPrice");
                                                            BookRentalTime = document.getString("BookRentalTime");
                                                            BookDiscription = document.getString("BookDetails");
                                                            BookAddress = document.getString("BookAddress");
                                                            BookOwenerid = document.getString("OwenerId");
                                                            cartitem = document.getBoolean("cart_item");
                                                            takenBook = document.getBoolean("taken");
                                                            TimeType = document.getString("BookRentalTimeType");

                                                            displayBookDetail();
                                                        }
                                                    }
                                                });
                                        break;
                                    } else {
                                        i++;
                                    }
                                }
                            } else {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                            }
                        }
                    });
        }


        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firebaseAuth.getCurrentUser().getUid().equals(BookOwenerid)){
                    Toast.makeText(ProductDetailsActivity.this,"This is your Book You can't add this in cart",Toast.LENGTH_SHORT).show();
                } else {
                    firebaseFirestore.collection("Books")
                            .document(id)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()){
                                        DocumentSnapshot document = task.getResult();
                                    }
                                }
                            });

                    if (addToCartText.getText().toString().equals("ADD TO CART")){
                    /*addToCartText.setText("REMOVE");
                    Toast.makeText(ProductDetailsActivity.this,"Book add in Your Cart", Toast.LENGTH_SHORT).show();*/
                        Map<String, Object> cartMap = new HashMap<>();
                        cartMap.put("Bookid",id);
                        firebaseFirestore.collection("USERS")
                                .document(firebaseAuth.getCurrentUser().getUid())
                                .collection("CART")
                                .add(cartMap)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        firebaseFirestore.collection("Books").document(id).update("cart_item",true);
                                        addToCartText.setText("REMOVE");
                                        Toast.makeText(ProductDetailsActivity.this,"Book add in Your Cart",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProductDetailsActivity.this,"Book not add in Your Cart",Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (addToCartText.getText().toString().equals("REMOVE")){
                    /*addToCartText.setText("ADD TO CART");
                    Toast.makeText(ProductDetailsActivity.this,"Book remove from Your Cart",Toast.LENGTH_SHORT).show();*/
                        firebaseFirestore.collection("USERS")
                                .document(firebaseAuth.getCurrentUser().getUid())
                                .collection("CART")
                                .whereEqualTo("Bookid",id)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()){
                                            for (QueryDocumentSnapshot document : task.getResult()){
                                                document.getReference().delete();
                                                firebaseFirestore.collection("Books").document(id).update("cart_item",false);
                                                addToCartText.setText("ADD TO CART");
                                                Toast.makeText(ProductDetailsActivity.this,"Book remove from Your Cart",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });
                    } else if (addToCartText.getText().toString().equals("Book is Not Available")){
                        Toast.makeText(ProductDetailsActivity.this,"Book is not Available",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        takeNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (firebaseAuth.getCurrentUser().getUid().equals(BookOwenerid)){
                    Toast.makeText(ProductDetailsActivity.this,"This is your Book You can't take this",Toast.LENGTH_SHORT).show();
                }
                else{
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                    Date date1 = new Date();
                    BookRentStartDate = dateFormat.format(date1);
                    Calendar c = Calendar.getInstance();
                    c.setTime(date1);
                    if (TimeType.equals("Week")){
                        int time = Integer.parseInt(BookRentalTime);
                        c.add(Calendar.WEEK_OF_YEAR, Integer.parseInt(BookRentalTime));
                    } else if (TimeType.equals("Day")){
                        int time = Integer.parseInt(BookRentalTime);
                        c.add(Calendar.DATE, Integer.parseInt(BookRentalTime));
                    } else if (TimeType.equals("Month")){
                        int time = Integer.parseInt(BookRentalTime);
                        c.add(Calendar.MONTH, Integer.parseInt(BookRentalTime));
                    }
                    Date date2 = c.getTime();
                    BookRentEndDate = dateFormat.format(date2);

                    Map<String, Object> Rental = new HashMap<>();
                    Rental.put("BookImage",BookImage);
                    Rental.put("OwenerId",BookOwenerid);
                    Rental.put("BookTitle",BookName);
                    Rental.put("StartDate",BookRentStartDate);
                    Rental.put("EndDate",BookRentEndDate);
                    Rental.put("Address",BookAddress);
                    Rental.put("OriginalPrice",BookOriginalPrice);
                    Rental.put("BookRentalPrice",BookRentalPrice);
                    Rental.put("ReceiverID",FirebaseAuth.getInstance().getCurrentUser().getUid());

                    Log.d(TAG,"RentalData"+BookRentEndDate);

                    firebaseFirestore.collection("Rental")
                            .add(Rental).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                        }
                    });

                    Map<String, Object> takenMap = new HashMap<>();
                    takenMap.put("Bookid",id);

                    DocumentReference db = firebaseFirestore.collection("USERS")
                            .document(firebaseAuth.getCurrentUser().getUid());
                    db.collection("TAKEN")
                            .add(takenMap)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    firebaseFirestore.collection("Books").document(id).update("taken",true);
                                    addToCartText.setText("You Take This Book");
                                    takeNow.setVisibility(View.GONE);
                                    Toast.makeText(ProductDetailsActivity.this,"You Take This Book",Toast.LENGTH_SHORT).show();

                                }
                            });

                    if (addToCartText.getText().toString().equals("REMOVE")){

                        db.collection("CART")
                                .whereEqualTo("Bookid",id)
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()){
                                            for (QueryDocumentSnapshot document : task.getResult()){
                                                document.getReference().delete();
                                                firebaseFirestore.collection("Books").document(id).update("cart_item",false);
                                                addToCartText.setText("You Take This Book");
                                                takeNow.setVisibility(View.GONE);
                                                Toast.makeText(ProductDetailsActivity.this,"You Take This Book",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });
                    }
                }
            }
        });

        //viewPagerIndicator.setupWithViewPager(productImagesViewPager,true);
        addToFavouritetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alreadyAddedToFavourite){
                    alreadyAddedToFavourite = false;
                    firebaseFirestore.collection("USERS")
                            .document(firebaseAuth.getCurrentUser().getUid())
                            .collection("WISHLIST")
                            .whereEqualTo("Bookid",id)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()){
                                        for (QueryDocumentSnapshot document : task.getResult()){
                                            document.getReference().delete();
                                            addToFavouritetBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));
                                            Toast.makeText(ProductDetailsActivity.this,"Book remove from Your Wishlist",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                }else{
                    alreadyAddedToFavourite = true;
                    Map<String, Object> wishlistMap = new HashMap<>();
                    wishlistMap.put("Bookid",id);
                    firebaseFirestore.collection("USERS")
                        .document(firebaseAuth.getCurrentUser().getUid())
                        .collection("WISHLIST")
                        .add(wishlistMap)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                addToFavouritetBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorred));
                                Toast.makeText(ProductDetailsActivity.this,"Book add in Your Wishlist",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ProductDetailsActivity.this,"Book not add in Your Wishlist",Toast.LENGTH_SHORT).show();
                        }
                    });
                    //addToFavouritetBtn.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
                }
            }
        });

    }

    void displayBookDetail(){

        bookName.setText(BookName);
        bookRentalPrice.setText("Rs."+BookRentalPrice);
        bookOriginalPrice.setText("(Rs."+BookOriginalPrice+"/-)");
        bookRentalTime.setText("("+BookRentalTime+" "+TimeType+")");
        bookDiscription.setText(BookDiscription);
        bookAddress.setText(BookAddress);
        Log.d(TAG, "cartItem " + cartitem);

        if (cartitem || takenBook){
            //Toast.makeText(ProductDetailsActivity.this,"call cart item if",Toast.LENGTH_SHORT).show();;
            firebaseFirestore.collection("USERS")
                    .document(firebaseAuth.getCurrentUser().getUid())
                    .collection("CART")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot document : task.getResult()) {
                                    String bookid = document.getString("Bookid");
                                    //assert bookid != null;
                                    if (bookid.equals(id)) {
                                        Log.d(TAG, "both are write ");
                                        takeNow.setVisibility(View.VISIBLE);
                                        addToCartText.setText("REMOVE");
                                        //Toast.makeText(ProductDetailsActivity.this, "call if", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });

            firebaseFirestore.collection("USERS")
                    .document(firebaseAuth.getCurrentUser().getUid())
                    .collection("TAKEN")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot document : task.getResult()) {
                                    String bookid = document.getString("Bookid");
                                    assert bookid != null;
                                    if (bookid.equals(id)) {
                                        addToCartText.setText("You Take This Book");
                                        takeNow.setVisibility(View.GONE);
                                        //Toast.makeText(ProductDetailsActivity.this, "call if", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
        }
        else {
            //Toast.makeText(ProductDetailsActivity.this,"call cart else",Toast.LENGTH_SHORT).show();
            takeNow.setVisibility(View.VISIBLE);
            addToCartText.setText("ADD TO CART");
        }

        firebaseFirestore.collection("USERS")
                .document(firebaseAuth.getCurrentUser().getUid())
                .collection("WISHLIST")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                String bookid = document.getString("Bookid");
                                assert bookid != null;
                                if (bookid.equals(id)) {
                                    alreadyAddedToFavourite = true;
                                    addToFavouritetBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorred));
                                    //Toast.makeText(ProductDetailsActivity.this, "call if", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });

        Log.d(TAG, "bookID " + id);
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