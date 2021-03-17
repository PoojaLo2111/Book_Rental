package com.example.bookrental;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private List<AddressModel> addressModelList;

    public AddressAdapter(List<AddressModel> addressModelList) {
        this.addressModelList = addressModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = addressModelList.get(position).getFullname();
        String address = addressModelList.get(position).getAddress();
        String pincode = addressModelList.get(position).getPincode();
        holder.setData(name,address,pincode);
    }

    @Override
    public int getItemCount() {
        return addressModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fullname;
        private TextView address;
        private TextView pincode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fullname = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            pincode = itemView.findViewById(R.id.pincode);
        }

        private void setData(String username,String useraddress,String userpincode){
            fullname.setText(username);
            address.setText(useraddress);
            pincode.setText(userpincode);
        }
    }
}
