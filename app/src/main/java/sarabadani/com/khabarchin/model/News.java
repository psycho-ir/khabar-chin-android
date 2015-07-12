package sarabadani.com.khabarchin.model;

/**
 * Created by SOROOSH on 7/12/15.
 */
public class News {
    private String id;
    private String title;
    private String date;


    private String imgAddress;

    public News(String id, String title, String imgAddress, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.imgAddress = imgAddress;
    }

    public News() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    @Override
    public String toString() {
        return title;
    }
}
