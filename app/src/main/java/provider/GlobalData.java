package provider;

import android.app.Application;
import android.util.Log;

import java.util.List;

import model.Author;
import model.Book;
import model.Category;
import model.User;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sartikahasibuan on 7/11/2016.
 */
public class GlobalData extends Application {
    public List<Book> AllBooks;
    public List<Category> AllCategories;
    public List<Author> AllAuthors;
    public User user;

    private static GlobalData gd;

    public  GlobalData(){
        consumeAPIGetAllBook();
        consumeAPIGetAllCategories();
        consumeAPIGetAllAuthors();
    }

    public static  GlobalData getInstance(){
        if(gd == null){
            gd = new GlobalData();
        }
        return gd;
    }

    public void setAllBooks(List<Book> all_books){
        AllBooks = all_books;
    }

    public List<Book> getAllBooks(){
        return this.AllBooks;
    }

    public void setAllCategories(List<Category> all_categories){
        AllCategories = all_categories;
    }

    public List<Category> getAllCategories(){
        return this.getAllCategories();
    }

    public void setAllAuthors(List<Author> all_authors){
        AllAuthors = all_authors;
    }

    public List<Author> getAllAuthors(){
        return this.getAllAuthors();
    }

    public void consumeAPIGetAllBook(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Book>> call = apiService.getBooks();
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> books = response.body();
                AllBooks = books;
                Log.w("Success", "Number of book received: " + books.size());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                // Log error here since request failed
                Log.w("Failed ", t.toString());
            }
        });
    }

    public void consumeAPIGetAllCategories(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Category>> call = apiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categories = response.body();
                AllCategories = categories;
                Log.w("Success", "Number of categories received: " + categories.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Log error here since request failed
                Log.w("Failed ", t.toString());
            }
        });
    }

    public void consumeAPIGetAllAuthors(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Author>> call = apiService.getAuthors();
        call.enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                List<Author> authors = response.body();
                AllAuthors = authors;
                Log.w("Success", "Number of authors received: " + authors.size());
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                // Log error here since request failed
                Log.w("Failed ", t.toString());
            }
        });
    }




    public Book getDetailsBook(String book_name){
        Book result = new Book();
        for(Book model : AllBooks){
            if(model.getName().equals(book_name)){
                result = model;
                Log.d("getDetailsBook",""+result.getName());
            }
            else{
                Log.d("No match","no match book");
                return null;
            }
        }
        return  result;
    }

    public String getAuthorName(String id){
        String result = "";
        for(Author model : AllAuthors){
            if(model.getId().equals(id)){
                result =  model.getName();
            }
            else
            {
                return null;
            }
        }
        return result;
    }
}
