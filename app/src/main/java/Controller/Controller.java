package Controller;

/**
 * Created by yanti on 19/07/2016.
 */
import java.util.ArrayList;
import android.app.Application;

import model.Book;
import model.ShoppingCartEntry;

public class Controller {

    private static ArrayList<Book> myBooks = new ArrayList<Book>();
    private static ShoppingCartEntry myCart = new ShoppingCartEntry();


    public static Book getBooks(int pPosition) {

        return myBooks.get(pPosition);
    }

    public static void setBooks(Book Books) {

        myBooks.add(Books);

    }

    public static ShoppingCartEntry getCart() {

        return myCart;

    }

    public static int getBooksArraylistSize() {

        return myBooks.size();
    }

}
