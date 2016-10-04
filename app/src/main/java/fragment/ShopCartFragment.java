package fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import Controller.Controller;

import com.example.zxcvbn.bookstore.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import activity.MainActivity;
import adapter.BooksAdapter;
import adapter.CustomList;
import model.Book;
import model.DataContentProvider;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zxcvbn on 06/07/2016.
 */
public class ShopCartFragment extends Fragment{
    ListView mListView;
    List<Book> list_book;
    private static final String TAG = MainActivity.class.getSimpleName();
    String totPrice;
    double ttlPrcBook;
    CustomList adapter;
    ImageView ivShopCart;
    String totalPrice = "";
    String price = "";
    String name="";
    int total=1;
    String[] arrayName;
    String[] arrayPrice;
    String[] arrayTotal;
    ImageButton IBminus, IBplus;
    public ShopCartFragment(){    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.w(TAG, "Call onActivityCreated");
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w(TAG, "Call onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.w(TAG, "Call onCreateView");
        View view = inflater.inflate(R.layout.shopping_cart_list, container, false);
        final TextView txtPrice=(TextView)view.findViewById(R.id.txtPrice);
        final TextView txtName=(TextView)view.findViewById(R.id.txtNameBook);
        final TextView txtTotal=(TextView)view.findViewById(R.id.txtTotal);
        final TextView txtTotalPrice=(TextView)view.findViewById(R.id.txtTotalPrice);
        IBminus =(ImageButton) view.findViewById(R.id.IBminus);
        IBplus =(ImageButton) view.findViewById(R.id.IBPlus);

        // Get Cart Size
        final int cartSize = Controller.getCart().getCartSize();
         GridView list;

       /* Resources res = getResources();
        TypedArray icons = res.obtainTypedArray(R.array.your_imgs);
        Drawable drawable = icons.getDrawable(0);*/


        final Integer[] imageId  = {
                R.drawable.ex_book3,
                R.drawable.ex_book4,
                R.drawable.ex_book3,
                R.drawable.ex_book4};



        List<String> listTotal = new ArrayList<>();
        List<String> listPrice = new ArrayList<>();
        List<String> listName = new ArrayList<>();

/******** Show Cart Products on screen - Start ********/

        if(cartSize >0)
        {

            for(int i=0;i<cartSize;i++)
            {
                //Get product details
                String pName    = Controller.getCart().getBooks(i).getName();
                double pPrice      = Double.parseDouble(Controller.getCart().getBooks(i).getPrice());
                String pDisc    = Controller.getCart().getBooks(i).getSummary();

                ttlPrcBook += pPrice;
                name = pName;
                price = pPrice+"";

                IBplus.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        total +=1;
                        txtTotal.setText(""+total);
                    }
                });

                IBminus.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        total -=1;
                        txtTotal.setText(""+total);
                    }
                });

                totalPrice=ttlPrcBook+"";
                listName.add(name);
                listPrice.add(price);
                listTotal.add(total+"");
            }


             arrayName = listName.toArray(new String[listName.size()]);
             arrayPrice = listPrice.toArray(new String[listPrice.size()]);
             arrayTotal = listTotal.toArray(new String[listTotal.size()]);

            List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();
                adapter = new CustomList(getActivity(), arrayName, imageId);

            list=(GridView)view.findViewById(R.id.listShopCart);
            list.setAdapter(adapter);
            list.setNumColumns(imageId.length);
            txtTotalPrice.setText("Total Price: "+totalPrice+"");
            txtTotal.setText(total+"");

            txtName.setText(arrayName[0].toString());
            txtPrice.setText(arrayPrice[0].toString());

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    //ivShopCart.setImageDrawable(getResources().getDrawable(imageId[position]));
                    txtTotalPrice.setText("Total Price: "+totalPrice+"");
                    txtTotal.setText(arrayTotal[position].toString());
                    txtName.setText(arrayName[position].toString());
                    txtPrice.setText(arrayPrice[position].toString());
                }
            });
        }
        else
            totalPrice = "Shopping cart is empty.";

       /* txtTotalPrice.setText("Total Price: "+100+"");
        txtTotal.setText(total);
        txtName.setText(name);
        txtPrice.setText(price);*/

/******** Show Cart Products on screen - End ********//*

        thirdBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if(cartSize >0)
                {
                    Intent i = new Intent(getBaseContext(), ThirdScreen.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "Shopping cart is empty.",
                            Toast.LENGTH_LONG).show();
            }
        });*/
    return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
