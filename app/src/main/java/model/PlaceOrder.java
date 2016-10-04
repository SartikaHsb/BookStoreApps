package model;

/**
 * Created by sartikahasibuan on 7/18/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaceOrder {
    @SerializedName("book_id")
    @Expose
    private Integer bookId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;

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


}
