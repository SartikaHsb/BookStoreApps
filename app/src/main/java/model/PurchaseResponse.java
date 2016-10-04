package model;

/**
 * Created by sartikahasibuan on 7/18/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PurchaseResponse {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("book_id")
    @Expose
    private Integer bookId;
    @SerializedName("purchased")
    @Expose
    private Object purchased;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The bookId
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     *
     * @param bookId
     * The book_id
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     *
     * @return
     * The purchased
     */
    public Object getPurchased() {
        return purchased;
    }

    /**
     *
     * @param purchased
     * The purchased
     */
    public void setPurchased(Object purchased) {
        this.purchased = purchased;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
