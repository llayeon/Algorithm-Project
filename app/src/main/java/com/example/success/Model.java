package com.example.success;
import android.widget.ImageView;


public class Model  {
    //position -> image로 바꿈
    int image;
    boolean isSelected = false;

    public Model(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public boolean isSelected() {
        return isSelected;
    }

}
