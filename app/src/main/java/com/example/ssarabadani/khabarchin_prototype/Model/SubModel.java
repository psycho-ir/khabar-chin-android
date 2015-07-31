package com.example.ssarabadani.khabarchin_prototype.Model;

/**
 * Created by Admin on 7/31/2015.
 */
public class SubModel {

    String sub_title;
    String agency_name;
    String news_abstract;
    int like_counter;
    String news_img_address;
    int agency_logo;

    public SubModel() {
    }

    public SubModel(String sub_title, String agency_name, String news_abstract, int like_counter, String news_img_address, int agency_logo) {

        this.agency_logo = agency_logo;
        this.agency_name = agency_name;
        this.like_counter = like_counter;
        this.news_abstract = news_abstract;
        this.news_img_address = news_img_address;
        this.sub_title = sub_title;

    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getNews_abstract() {
        return news_abstract;
    }

    public void setNews_abstract(String news_abstract) {
        this.news_abstract = news_abstract;
    }

    public int getLike_counter() {
        return like_counter;
    }

    public void setLike_counter(int like_counter) {
        this.like_counter = like_counter;
    }

    public String getNews_img_address() {
        return news_img_address;
    }

    public void setNews_img_address(String news_img_address) {
        this.news_img_address = news_img_address;
    }

    public int getAgency_logo() {
        return agency_logo;
    }

    public void setAgency_logo(int agency_logo) {
        this.agency_logo = agency_logo;
    }
}
