package com.example.bookrental;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddBookActivity extends AppCompatActivity {

    private static final int PICK_FROM_GALLERY = 101;

    TextView buttonAttachment;
    RecyclerView newAttachmentListView;
    private ArrayList<AttachmentListData> newAttachmentList = new ArrayList<>();
    private ArrayList<Uri> imageUriList = new ArrayList<>();
    AttachmentListAdapter attachmentListAdapter;
    private Button uploadbtn;
    private Uri ImageUri;
    TextView alert;
    private ProgressDialog progressDialog;
    private FirebaseStorage firebaseStorage;
    private FirebaseFirestore firebaseFirestore;
    //private StorageReference storageReference;
    private CollectionReference reference;
    private int counter;
    //private RecyclerView addBookRecyclerView;
    private TextView selectbookbtn;
    private String BookTypeList[],selectedBook,selecttime,TimeTypeList[];
    private EditText bookName,bookDiscription,bookOriginalPrice,bookRentalTime,bookRentalPrice,bookAddress;
    private Spinner bookTypespinner,Timetypesppinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add New Book");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseStorage = FirebaseStorage.getInstance();
        //reference = firebaseStorage.getReference("Images");
        firebaseFirestore = FirebaseFirestore.getInstance();
        selectbookbtn = findViewById(R.id.select_book_btn);
        //buttonAttachment = findViewById(R.id.buttonAttachment);
        newAttachmentListView = findViewById(R.id.add_book_recycleview);
        uploadbtn = findViewById(R.id.save_btn);
        reference = firebaseFirestore.collection("Books");

        bookName = findViewById(R.id.book_name);
        bookTypespinner = findViewById(R.id.book_type);
        bookDiscription = findViewById(R.id.detail_of_book);
        bookOriginalPrice = findViewById(R.id.book_original_price);
        bookRentalTime = findViewById(R.id.book_rental_time);
        bookRentalPrice = findViewById(R.id.book_rental_price);
        bookAddress = findViewById(R.id.book_address_torent);
        Timetypesppinner =findViewById(R.id.time_type);
        BookTypeList = getResources().getStringArray(R.array.BookType);
        TimeTypeList = getResources().getStringArray(R.array.TimeType);

        selectbookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFolder();
            }
        });

        ArrayAdapter spinnerbooktypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,BookTypeList);
        spinnerbooktypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bookTypespinner.setAdapter(spinnerbooktypeAdapter);
        bookTypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBook = BookTypeList[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter spinnertimetypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,TimeTypeList);
        spinnertimetypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Timetypesppinner.setAdapter(spinnertimetypeAdapter);
        Timetypesppinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selecttime = TimeTypeList[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImages(new ArrayList<String>(),imageUriList);
                //uploadImages(new ArrayList<>(),imageUriList);
                //uploadImages(newAttachmentList,imageUriList);
            }
        });

        /*addBookRecyclerView = findViewById(R.id.add_book_recycleview);
        selectbookbtn = findViewById(R.id.select_book_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        addBookRecyclerView.setLayoutManager(layoutManager);

        List<BookImageModel> bookImageModelList = new ArrayList<>();
        bookImageModelList.add(new BookImageModel(R.drawable.book1));
        bookImageModelList.add(new BookImageModel(R.drawable.book2));
        bookImageModelList.add(new BookImageModel(R.drawable.book3));
        bookImageModelList.add(new BookImageModel(R.drawable.book4));
        bookImageModelList.add(new BookImageModel(R.drawable.book5));
        bookImageModelList.add(new BookImageModel(R.drawable.book6));

        BookImageAdapter bookImageAdapter = new BookImageAdapter(bookImageModelList);
        addBookRecyclerView.setAdapter(bookImageAdapter);
        bookImageAdapter.notifyDataSetChanged();*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openFolder() {

        Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                for (int i = 0; i < count; i++) {
                    Uri returnUri = data.getClipData().getItemAt(i).getUri();
                    imageUriList.add(returnUri);
                    Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                    returnCursor.moveToFirst();
                    System.out.println("PIYUSH NAME IS" + returnCursor.getString(nameIndex));
                    System.out.println("PIYUSH SIZE IS" + Long.toString(returnCursor.getLong(sizeIndex)));
                    AttachmentListData attachmentListData = new AttachmentListData();
                    attachmentListData.setImageName(returnCursor.getString(nameIndex));
                    attachmentListData.setImageID(returnUri.toString());
                    newAttachmentList.add(attachmentListData);
                }
            } else if (data.getData() != null) {
                Uri returnUri = data.getData();
                Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                returnCursor.moveToFirst();
                System.out.println("PIYUSH NAME IS" + returnCursor.getString(nameIndex));
                System.out.println("PIYUSH SIZE IS" + Long.toString(returnCursor.getLong(sizeIndex)));
                AttachmentListData attachmentListData = new AttachmentListData();
                attachmentListData.setImageName(returnCursor.getString(nameIndex));
                attachmentListData.setImageID(returnUri.toString());
                newAttachmentList.add(attachmentListData);
            }
            generateNewAttachmentList(newAttachmentList);
        }
    }
    private void generateNewAttachmentList(ArrayList<AttachmentListData> newAttachmentList) {
        newAttachmentListView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(AddBookActivity.this);
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newAttachmentListView.setLayoutManager(MyLayoutManager);
        attachmentListAdapter = new AttachmentListAdapter(newAttachmentList, AddBookActivity.this);
        newAttachmentListView.setAdapter(attachmentListAdapter);
    }

    private void uploadImages(final ArrayList<String> imagesUrl, final ArrayList<Uri> imageUriList) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploaded 0/" + imageUriList.size());
        progressDialog.setCanceledOnTouchOutside(false); //Remove this line if you want your user to be able to cancel upload
        progressDialog.setCancelable(false);    //Remove this line if you want your user to be able to cancel upload
        progressDialog.show();

        final StorageReference storageReference = firebaseStorage.getReference()/*.child(UUID.randomUUID().toString())*/;

        for (int i = 0; i < newAttachmentList.size(); i++) {
            Uri uri = imageUriList.get(i);
            final int finalI = i;
            storageReference.child("userData").child(String.valueOf(imageUriList.get(i))).putFile(Uri.parse(imageUriList.get(i).toString())).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        storageReference.child("userData/").child(imageUriList.get(finalI).toString()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                counter++;
                                progressDialog.setMessage("Uploaded " + counter + "/" + imageUriList.size());
                                if (task.isSuccessful()) {
                                    imagesUrl.add(task.getResult().toString());
                                } else {
                                    //storageReference.child("userData/").child(imageUriList.get(finalI).toString()).delete();
                                    Toast.makeText(AddBookActivity.this, "Couldn't save " + imageUriList.get(finalI).toString(), Toast.LENGTH_SHORT).show();
                                }
                                if (counter == imageUriList.size()) {
                                    progressDialog.setMessage("Saving uploaded images...");
                                    Map<String, Object> dataMap = new HashMap<>();
                                    for (int i = 0; i < imagesUrl.size(); i++) {
                                        dataMap.put("image" + i, imagesUrl.get(i));
                                    }

                                    dataMap.put("BookName",bookName.getText().toString());
                                    dataMap.put("BookType",selectedBook);
                                    dataMap.put("BookDetails",bookDiscription.getText().toString());
                                    dataMap.put("BookOriginalPrice",bookOriginalPrice.getText().toString());
                                    dataMap.put("BookRentalTime",bookRentalTime.getText().toString());
                                    dataMap.put("BookRentalTimeType",selecttime);
                                    dataMap.put("BookRentalPrice",bookRentalPrice.getText().toString());
                                    dataMap.put("BookAddress",bookAddress.getText().toString());
                                    dataMap.put("OwenerId", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                    //cart.setText("1");
                                    dataMap.put("cart_item",false);
                                    dataMap.put("taken",false);
                                    dataMap.put("Num",counter);

                                    reference.add(dataMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            progressDialog.dismiss();
                                            Log.v("key",documentReference.getId());
                                            startActivity(new Intent(AddBookActivity.this,MyBookActivity.class));
                                            finish();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressDialog.dismiss();
                                            Log.e("MainActivity:SaveData", e.getMessage());
                                        }
                                    });
                                }
                            }
                        });
                    } else {
                        progressDialog.setMessage("Uploaded " + AddBookActivity.this.counter + "/" + imageUriList.size());
                        AddBookActivity.this.counter++;
                        Toast.makeText(AddBookActivity.this, "Couldn't upload " + imageUriList.get(finalI).toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

/*public void openFolder() {

        Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        Intent intent = new Intent();
        intent.setType("*//*");
        /*intent.putExtra(Intent.EXTRA_STREAM,uri);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                for (int i = 0; i < count; i++) {
                    Uri returnUri = data.getClipData().getItemAt(i).getUri();
                    imageUriList.add(returnUri);
                    Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
                    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                    returnCursor.moveToFirst();
                    System.out.println("PIYUSH NAME IS" + returnCursor.getString(nameIndex));
                    System.out.println("PIYUSH SIZE IS" + Long.toString(returnCursor.getLong(sizeIndex)));
                    AttachmentListData attachmentListData = new AttachmentListData();
                    attachmentListData.setImageName(returnCursor.getString(nameIndex));
                    attachmentListData.setImageID(returnUri.toString());
                    newAttachmentList.add(attachmentListData);
                }
            } else if (data.getData() != null) {
                Uri returnUri = data.getData();
                Cursor returnCursor = getContentResolver().query(returnUri, null, null, null, null);
                int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
                returnCursor.moveToFirst();
                System.out.println("PIYUSH NAME IS" + returnCursor.getString(nameIndex));
                System.out.println("PIYUSH SIZE IS" + Long.toString(returnCursor.getLong(sizeIndex)));
                AttachmentListData attachmentListData = new AttachmentListData();
                attachmentListData.setImageName(returnCursor.getString(nameIndex));
                attachmentListData.setImageID(returnUri.toString());
                newAttachmentList.add(attachmentListData);
            }
            generateNewAttachmentList(newAttachmentList);
        }
    }
    private void generateNewAttachmentList(ArrayList<AttachmentListData> newAttachmentList) {
        newAttachmentListView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(AddBookActivity.this);
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        newAttachmentListView.setLayoutManager(MyLayoutManager);
        attachmentListAdapter = new AttachmentListAdapter(newAttachmentList, AddBookActivity.this);
        newAttachmentListView.setAdapter(attachmentListAdapter);
    }

    private void uploadImages(@NonNull final ArrayList<String> imagesUrl, final ArrayList<Uri> imageUriList) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploaded 0/"+imageUriList.size());
        progressDialog.setCanceledOnTouchOutside(false); //Remove this line if you want your user to be able to cancel upload
        progressDialog.setCancelable(false);    //Remove this line if you want your user to be able to cancel upload
        progressDialog.show();

        final StorageReference storageReference = firebaseStorage.getInstance().getReference("BookImages");//.child(UUID.randomUUID().toString());

        for(int i =0;i<newAttachmentList.size();i++) {
            Uri uri = imageUriList.get(i);
            final int finalI = i;
            storageReference./*child("userData").*//*child(String.valueOf(imageUriList.get(i))).putFile(Uri.parse(imageUriList.get(i).toString())).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){
                        storageReference./*child("userData/").*//*child(imageUriList.get(finalI).toString()).getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                counter++;
                                progressDialog.setMessage("Uploaded "+counter+"/"+imageUriList.size());
                                if (task.isSuccessful()){
                                    imagesUrl.add(task.getResult().toString());
                                }else{
                                    //storageReference.child("userData/").child(imageUriList.get(finalI).toString()).delete();
                                    Toast.makeText(AddBookActivity.this, "Couldn't save "+imageUriList.get(finalI).toString(), Toast.LENGTH_SHORT).show();
                                }
                                if (counter == imageUriList.size()){
                                    progressDialog.setMessage("Saving uploaded images...");
                                    Map<Object, String> dataMap = new HashMap<>();
                                    for (int i=0; i<imagesUrl.size(); i++){
                                        dataMap.put("book"+i, imagesUrl.get(i));
                                    }
                                    dataMap.put("BookName",bookName.getText().toString());
                                    dataMap.put("BookType",bookType.getText().toString());
                                    dataMap.put("BookDetails",bookDiscription.getText().toString());
                                    dataMap.put("BookOriginalPrice",bookOriginalPrice.getText().toString());
                                    dataMap.put("BookRentalTime",bookRentalTime.getText().toString());
                                    dataMap.put("BookRentalPrice",bookRentalPrice.getText().toString());
                                    dataMap.put("BookAddress",bookAddress.getText().toString());

                                    reference.add(dataMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            progressDialog.dismiss();
                                            //coreHelper.createAlert("Success", "Images uploaded and saved successfully!", "OK", "", null, null, null);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressDialog.dismiss();
                                            //coreHelper.createAlert("Error", "Images uploaded but we couldn't save them to database.", "OK", "", null, null, null);
                                            Log.e("MainActivity:SaveData", e.getMessage());
                                        }
                                    });
                                }
                            }
                        });
                    }else{
                        progressDialog.setMessage("Uploaded "+counter+"/"+imageUriList.size());
                        counter++;
                        Toast.makeText(AddBookActivity.this, "Couldn't upload "+imageUriList.get(finalI).toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }*/