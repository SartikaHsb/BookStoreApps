package rest;

/**
 * Created by sartikahasibuan on 7/8/2016.
 */

import java.util.List;

import model.Author;
import model.Book;
import model.Category;
import model.GeneralResponse;
import model.LoginRespon;
import model.Orders;
import model.PlaceOrder;
import model.PurchaseResponse;
import model.RequestUser;
import model.UpdateUser;
import model.User;
import model.UserLogin;
import model.RegisterRespon;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("books")
    Call<List<Book>> getBooks();

    @GET("authors")
    Call<List<Author>> getAuthors();

    @GET("categories")
    Call<List<Category>> getCategories();

    @POST("register")
    Call<RegisterRespon> register(@Body RequestUser user);

    @POST("login")
    Call<LoginRespon> login(@Body UserLogin login);

    @PUT("users/{id}")
    Call<GeneralResponse> update_user(@Header("session-token") String token, @Path("id") String id, @Body UpdateUser user);

    @GET("users/{id}")
    Call<User> getUserDetails(@Path("id") String id);

    @POST("orders")
    Call<GeneralResponse> place_order(@Header("session-token") String token, @Body PlaceOrder po);

    @GET("orders")
    Call<Orders> get_orders(@Header("session-token") String token);

    @GET("orders/purchase")
    Call<PurchaseResponse> purchase(@Header("session-token") String token);


}
