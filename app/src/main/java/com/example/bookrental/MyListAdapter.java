package com.example.bookrental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.perfmark.Link;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.Viewhoder> {

    private List<MyListItemModel> myListItemModelList;

    public MyListAdapter(List<MyListItemModel> myListItemModelList) {
        this.myListItemModelList = myListItemModelList;
    }

    @NonNull
    @Override
    public MyListAdapter.Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item_layout,parent,false);
        return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.Viewhoder holder, int position) {
        int resource = myListItemModelList.get(position).getProductImage();
        String title = myListItemModelList.get(position).getProductTitle();
        String lastdate = myListItemModelList.get(position).getProductLastDate();

        holder.setDate(resource, title, lastdate);
    }

    @Override
    public int getItemCount() {
        return myListItemModelList.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder{

        private ImageView productImage;
        private TextView productTitle;
        private TextView productLastDate;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.my_list_item_image);
            productTitle = itemView.findViewById(R.id.my_list_item_title);
            productLastDate = itemView.findViewById(R.id.my_list_item_last_date);
        }

        private void setDate(int resource,String title,String lastdate){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            productLastDate.setText(lastdate);
        }
    }
}
