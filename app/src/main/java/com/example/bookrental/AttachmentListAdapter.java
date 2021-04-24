package com.example.bookrental;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AttachmentListAdapter extends RecyclerView.Adapter<AttachmentListAdapter.AttachmentListViewHolder> {
    public ArrayList<AttachmentListData> newAttachmentList;
    public Activity mActivity;


    public AttachmentListAdapter(ArrayList<AttachmentListData> list, Activity activity) {
        newAttachmentList = list;
        mActivity = activity;
    }

    @Override
    public AttachmentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_newattachment_list, parent, false);

        AttachmentListViewHolder holder = new AttachmentListViewHolder(view, mActivity, newAttachmentList);

        return holder;
    }

    @Override
    public void onBindViewHolder(final AttachmentListViewHolder holder, int position) {
        holder.attachedImageName.setText((newAttachmentList.get(position).getImageName()));
        String userImage = newAttachmentList.get(position).getImageID();
        if (userImage.isEmpty()||userImage.equals(null)||userImage.equals("")) {

        } else {
            Picasso.with(mActivity)
                    .load(userImage)
                    .placeholder(R.drawable.ic_launcher_background)
                    .fit().centerCrop()
                    .into( holder.attachedImageId);
        }
    }

    @Override
    public int getItemCount() {
        return newAttachmentList.size();
    }

    class AttachmentListViewHolder extends RecyclerView.ViewHolder {

        ImageView attachedImageId;
        TextView attachedImageName;
        ImageView cancelAttachment;

        public AttachmentListViewHolder(View view, final Activity activity, final ArrayList<AttachmentListData> attachmentList) {
            super(view);

            attachedImageId = view.findViewById(R.id.attachedImageId);
            attachedImageName = view.findViewById(R.id.attachedImageName);
            cancelAttachment = view.findViewById(R.id.cancelAttachment);

            cancelAttachment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    attachmentList.remove(pos);
                    notifyDataSetChanged();
                }
            });
        }

        /*public void setAttachedImageId(ImageView attachedImageId) {
            this.attachedImageId = attachedImageId;
        }*/
    }
}
