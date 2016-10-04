package model;

/**
 * Created by sartikahasibuan on 7/8/2016.
 */
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Book {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("summary")
    private String summary;
    @SerializedName("author_id")
    private String author_id;
    @SerializedName("category_id")
    private String category_id;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("price")
    private String price;
    @SerializedName("cover")
    private Cover cover;

    public Book(){

    }
    public Book(String title, String price){
        this.name = title;
        this.price = price;
    }
    public Book(String id, String name, String summary, String author_id, String category_id, String created_at, String updated_at, String price){
        this.id = id;
        this.name = name;
        this.summary = summary;
        this.author_id = author_id;
        this.category_id = category_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.price = price;
    }

    public String getId(){
        return  id;
    }

    public  String getName(){
        return  name;
    }

    public String getSummary(){
        return summary;
    }

    public String getAuthor_id(){
        return  author_id;
    }

    public  String getCategory_id(){
        return category_id;
    }

    public String getCreated_at(){
        return created_at;
    }

    public String getUpdated_at(){
        return updated_at;
    }

    public String getPrice(){
        return price;
    }

    public void SetId(String id){
        this.id = id;
    }

    public void SetName(String name){
        this.name = name;
    }

    public void SetSummary(String summary){
        this.summary = summary;
    }

    public void SetAuthorId(String author_id){
        this.author_id = author_id;
    }

    public void SetCreatedAt(String created_at){
        this.created_at = created_at;
    }

    public void SetUpdatedAt(String updated_at){
        this.updated_at = updated_at;
    }

    public void SetPrice(String price){
        this.price = price;
    }
    /**
     *
     * @return
     * The cover
     */
    public Cover getCover() {
        return cover;
    }

    /**
     *
     * @param cover
     * The cover
     */
    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public class Cover {

        @SerializedName("url")
        private String url;

        /**
         *
         * @return
         * The url
         */
        public String getUrl() {
            return url;
        }

        /**
         *
         * @param url
         * The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

    }

    //Dummy data
    public String nama;

    public static final String[] data = {"Author", "Summary", "Price"};

    Book(String nama){
        this.nama=nama;
    }


}
