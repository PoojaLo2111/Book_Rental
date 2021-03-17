package com.example.bookrental;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class productImagesAdupter extends PagerAdapter {

    private List<Integer> ProductImages;

    public productImagesAdupter(List<Integer> productImages) {
        ProductImages = productImages;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView productImage = new ImageView(container.getContext());
        productImage.setImageResource(ProductImages.get(position));
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
