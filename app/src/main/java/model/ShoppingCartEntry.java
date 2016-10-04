package model;

import java.util.ArrayList;

/**
 * Created by yanti on 19/07/2016.
 */
public class ShoppingCartEntry {

    private ArrayList<Book> cartBooks = new ArrayList<Book>();


    public Book getBooks(int pPosition) {

        return cartBooks.get(pPosition);
    }

    public void setProducts(Book Books) {

        cartBooks.add(Books);

    }

    public int getCartSize() {

        return cartBooks.size();

    }

    public boolean checkProductInCart(Book aBook) {

        return cartBooks.contains(aBook);

    }

}

