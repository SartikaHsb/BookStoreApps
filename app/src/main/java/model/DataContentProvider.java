package model;

import android.content.Context;
import android.util.Log;

import com.example.zxcvbn.bookstore.R;

import java.util.ArrayList;
import java.util.List;

import model.Book;
import provider.GlobalData;

/**
 * Created by yanti on 16/07/2016.
 */
public class DataContentProvider {
    static GlobalData global_data = new GlobalData();

    public static int[] getCover(Context c){
        global_data = (GlobalData) c.getApplicationContext();
        int[] arr;
        List<Book> books = global_data.AllBooks;
        arr = new int[books.size()];
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] = R.drawable.ex_book3;
            } else {
                arr[i] = R.drawable.ex_book4;
            }
        }
        return  arr;
    }

    public static String[] getBookTitle(Context c, String idCat){
        global_data = (GlobalData) c.getApplicationContext();
        List<Book> books = global_data.AllBooks;
        String[] title = new String[books.size()];
        String[] titleBook = new String[books.size()];
        String[] catId = new String[books.size()];
        List<String> jj = new ArrayList<>();
        int i=0,l=0;
        for(Book model : books){
            title[i] = model.getName();
            i++;
        }
        for(Book model : books){
            catId[l] = model.getCategory_id();
            l++;
        }
        for(int k=0;k<catId.length;k++)
        {
            if(catId[k].equals(idCat))
            {
                jj.add(title[k]);
            }
        }
        String[] array = jj.toArray(new String[jj.size()]);
        return  array;
    }

    public static String[] getBookTit(Context c){

        List<Book> books = global_data.AllBooks;
        String[] title = new String[books.size()];
        int i=0;
        for(Book model : books){
            title[i] = model.getName();
            i++;
        }
        return  title;
    }

    public static String[] getCoverBook(Context c){

        List<Book> books = global_data.AllBooks;
        String[] cover = new String[books.size()];
        int i=0;
        for(Book model : books){
            cover[i] = model.getCover().getUrl();
            i++;
        }
        return  cover;
    }

    public static String[] getBookPr(Context c){

        List<Book> books = global_data.AllBooks;
        String[] title = new String[books.size()];
        int i=0;
        for(Book model : books){
            title[i] = model.getPrice();
            i++;
        }
        return  title;
    }

    public static String getBookCatbyId(Context c, String id){

        List<Category> books = global_data.AllCategories;
        String[] title = new String[books.size()];
        String[] idCat = new String[books.size()];
        String cate="";
        int i=0, j=0;
        for(Category model : books){
            title[i] = model.getName();
            i++;
        }
        for(Category model : books){
            idCat[j] = Integer.toString(model.getId());
            j++;
        }
        for(int k=0;k<title.length;k++)
        {
            if(idCat[k].equals(id))
            {
                cate = title[k];
            }
        }
        return  cate;
    }

    public static String[] getBookDesc(Context c){

        List<Book> books = global_data.AllBooks;
        String[] title = new String[books.size()];
        int i=0;
        for(Book model : books){
            title[i] = model.getSummary();
            i++;
        }
        return  title;
    }

    public static String[] getBookPrice(Context c, String idCat){
        global_data = (GlobalData) c.getApplicationContext();
        List<Book> books = global_data.AllBooks;
        String[] title = new String[books.size()];
        String[] titleBook = new String[books.size()];
        String[] catId = new String[books.size()];
        List<String> jj = new ArrayList<>();
        int i=0,l=0;
        for(Book model : books){
            title[i] = model.getPrice();
            i++;
        }
        for(Book model : books){
            catId[l] = model.getCategory_id();
            l++;
        }
        for(int k=0;k<catId.length;k++)
        {
            if(catId[k].equals(idCat))
            {
                jj.add(title[k]);
            }
        }
        String[] array = jj.toArray(new String[jj.size()]);
        return  array;
    }

    //category
    public static String[] getBookCategory(Context c){
        global_data = (GlobalData) c.getApplicationContext();
        List<Category> categories = global_data.AllCategories;
        String[] cat = new String[categories.size()];
        int i=0;
        for(Category model : categories){
            cat[i] = Integer.toString(model.getId());
            i++;

        }
        Log.w("Success", "iniiiiii: " + cat);
        return  cat;
    }

    //category
    public static String[] getBookAut(Context c){
        global_data = (GlobalData) c.getApplicationContext();
        List<Author> categories = global_data.AllAuthors;
        String[] cat = new String[categories.size()];
        int i=0;
        for(Author model : categories){
            cat[i] = model.getName();
            i++;
        }
        Log.w("Success", "iniiiiii: " + cat);
        return  cat;
    }

    /*//category
    public static List<String> getDetCart(Context c,String name){
        global_data = (GlobalData) c.getApplicationContext();
        List<Book> books = global_data.AllBooks;

        String[] id = new String[books.size()];
        String[] title = new String[books.size()];
        String[] summary = new String[books.size()];
        String[] author_id = new String[books.size()];
        String[] category_id = new String[books.size()];
        String[] created_at = new String[books.size()];
        String[] updated_at = new String[books.size()];
        String[] price = new String[books.size()];

        List<String> detCart = new ArrayList<>();
        String idBook="",titleBook="",summaryBook="",author_idBook="",category_idBook="",created_atBook="",updated_atBook="",priceBook="";
        int i=0,j=0,k=0,l=0,m=0,n=0,o=0,p=0;
        for(Book model : books){
            id[i] = model.getId();
            i++;
        }

        for(Book model : books){
            title[j] = model.getName();
            j++;
        }

        for(Book model : books){
            summary[k] = model.getSummary();
            k++;
        }

        for(Book model : books){
            author_id[l] = model.getAuthor_id();
            l++;
        }
        for(Book model : books){
            category_id[m] = model.getCategory_id();
            m++;
        }
        for(Book model : books){
            created_at[n] = model.getCreated_at();
            n++;
        }
        for(Book model : books){
            updated_at[o] = model.getUpdated_at();
            o++;
        }

        for(Book model : books){
            price[p] = model.getPrice();
            p++;
        }

        for(int q=0;q<title.length;q++)
        {
            if(title[q].equals(name))
            {
                idBook=id[q];
                titleBook=title[q];
                summaryBook=summary[q];
                author_idBook=author_id[q];
                category_idBook=category_id[q];
                created_atBook=created_at[q];
                updated_atBook=updated_at[q];
                priceBook=price[q];
            }
        }
        detCart.add(idBook);
        detCart.add(titleBook);
        detCart.add(summaryBook);
        detCart.add(author_idBook);
        detCart.add(category_idBook);
        detCart.add(created_atBook);
        detCart.add(updated_atBook);
        detCart.add(priceBook);

        return  detCart;
    }*/

    //category
    public static String getAuthor(Context c, String Name){
        global_data = (GlobalData) c.getApplicationContext();
        List<Author> authors = global_data.AllAuthors;
        List<String> cat = new ArrayList<>();
        List<String> bio = new ArrayList<>();
        String bioo="";
        for(Author model : authors){
            cat.add(model.getName());
        }
        for(Author model : authors){
            bio.add(model.getBio());
        }
        for(int y=0;y<cat.size();y++)
        {
            if(cat.get(y).equals(Name))
            {
                bioo=bio.get(y);
            }
        }
        Log.w("Success", "iniiiiii: " + bioo);
        return  bioo;
    }

    //category
    public static List<String> getDetailBk(Context c, String name){
        global_data = (GlobalData) c.getApplicationContext();
        List<Author> authors = global_data.AllAuthors;
        List<Book> books = global_data.AllBooks;
        List<String> detBook = new ArrayList<>();

        String[] id = new String[books.size()];
        String[] title = new String[books.size()];
        String[] summary = new String[books.size()];
        String[] author_id = new String[books.size()];
        String[] category_id = new String[books.size()];
        String[] created_at = new String[books.size()];
        String[] updated_at = new String[books.size()];
        String[] price = new String[books.size()];

        String idBook="",titleBook="",summaryBook="",author_idBook="",category_idBook="",created_atBook="",updated_atBook="",priceBook="";
        int i=0,j=0,k=0,l=0,m=0,n=0,o=0,p=0;
        for(Book model : books){
            id[i] = model.getId();
            i++;
        }

        for(Book model : books){
            title[j] = model.getName();
            j++;
        }

        for(Book model : books){
            summary[k] = model.getSummary();
            k++;
        }

        for(Book model : books){
            author_id[l] = model.getAuthor_id();
            l++;
        }
        for(Book model : books){
            category_id[m] = model.getCategory_id();
            m++;
        }
        for(Book model : books){
            created_at[n] = model.getCreated_at();
            n++;
        }
        for(Book model : books){
            updated_at[o] = model.getUpdated_at();
            o++;
        }

        for(Book model : books){
            price[p] = model.getPrice();
            p++;
        }

        for(int q=0;q<title.length;q++)
        {
            if(title[q].equals(name))
            {
                idBook=id[q];
                titleBook=title[q];
                summaryBook=summary[q];
                author_idBook=author_id[q];
                category_idBook=category_id[q];
                created_atBook=created_at[q];
                updated_atBook=updated_at[q];
                priceBook=price[q];
            }
        }
        detBook.add(idBook);
        detBook.add(titleBook);
        detBook.add(summaryBook);
        detBook.add(author_idBook);
        detBook.add(category_idBook);
        detBook.add(created_atBook);
        detBook.add(updated_atBook);
        detBook.add(priceBook);

        return  detBook;
    }



    /*//category
    public static List<String> getDetailBk(Context c, String Name){
        global_data = (GlobalData) c.getApplicationContext();
        List<Author> authors = global_data.AllAuthors;
        List<Book> books = global_data.AllBooks;
        List<String> detBook = new ArrayList<>();
        String[] tit = new String[books.size()];
        String[] sum = new String[books.size()];
        String[] pri = new String[books.size()];
        String[] autName = new String[books.size()];
        String summary="", price="",author="",title="";
        int i=0,j=0,k=0,l=0,n=0;

        for(Book model : books){
            autName[n] = model.getId();
            n++;
        }
        for(Book model : books){
            tit[i] = model.getName();
            i++;
        }
        for(Book model : books){
            sum[j] = model.getSummary();
            j++;
        }
        for(Book model : books){
            pri[k] = model.getPrice();
            k++;
        }
        for (int p=0;p<tit.length;p++) {
            if (tit[p].equals(Name)) {
                summary = sum[p];
                price = pri[p];
                author = autName[p];
                title = tit[p];
            }
        }
        detBook.add(summary);
        detBook.add(price);
        detBook.add(author);
        detBook.add(title);
        return  detBook;
    }*/
}
