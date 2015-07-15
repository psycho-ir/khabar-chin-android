package com.example.ssarabadani.khabarchin_prototype;

/**
 * Created by s.sarabadani on 7/15/2015.
 */
public class NewsCategory {

    String title;
    String pk;
    int categoryImage;


    public NewsCategory(String pk, String title, int categoryImage) {

        this.categoryImage = categoryImage;
        this.title = title;
        this.pk = pk;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }


    public int getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }
}
