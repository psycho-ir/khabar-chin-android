package sarabadani.com.khabarchin.model;

/**
 * Created by SOROOSH on 7/12/15.
 */
public class NewsCategory {

    public NewsCategory(String pk,String localName){
        this.pk = pk;
        this.localName = localName;
    }
    private String pk;
    private String localName;

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    @Override
    public String toString() {
        return localName;
    }
}
