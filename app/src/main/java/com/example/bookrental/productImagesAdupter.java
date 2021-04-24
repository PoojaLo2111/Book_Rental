package com.example.bookrental;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class productImagesAdupter extends PagerAdapter {

    private List<String> ProductImages;

    public productImagesAdupter(List<String> productImages) {
        ProductImages = productImages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView productImage = new ImageView(container.getContext());
        //productImage.setImageResource(ProductImages.get(position));
        Glide.with(container.getContext()).load(ProductImages.get(position)).apply(new RequestOptions().placeholder(R.drawable.pic)).into(productImage);
        container.addView(productImage,0);
        return productImage;
        //return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
        //super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return ProductImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
