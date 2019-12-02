package com.androidavanzado.capitalsocial.model;

public class Promotion {

    public int ImagePromotion;
    public String TextTitle;
    public String TextDescription;

    public Promotion(int imagePromotion, String textTitle, String textDescription) {
        this.ImagePromotion = imagePromotion;
        this.TextTitle = textTitle;
        this.TextDescription = textDescription;
    }

    public int getImagePromotion() {
        return ImagePromotion;
    }

    public void setImagePromotion(int imagePromotion) {
        ImagePromotion = imagePromotion;
    }

    public String getTextTitle() {
        return TextTitle;
    }

    public void setTextTitle(String textTitle) {
        TextTitle = textTitle;
    }

    public String getTextDescription() {
        return TextDescription;
    }

    public void setTextDescription(String textDescription) {
        TextDescription = textDescription;
    }
}
