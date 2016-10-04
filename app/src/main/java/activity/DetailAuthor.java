package activity;

/**
 * Created by zxcvbn on 15/07/2016.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import com.example.zxcvbn.bookstore.R;

import model.Author;

public class DetailAuthor extends AppCompatActivity {

    private TextView headerText;
    private ListView listView;
    private View headerView;
    private View headerSpace;
    private ArrayAdapter<String> adapter;
    private ArrayList<String>list;
    private TextView itemNama;
    private TextView itemBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_author);

        listView = (ListView) findViewById(R.id.list_view);
        headerView = findViewById(R.id.header_image_view);
        headerText = (TextView) findViewById(R.id.header_text);

        Intent intent = getIntent();
        String aname = intent.getStringExtra("aut_name");
        headerText.setText(aname);
        setListViewHeader();
        setListViewData();

        // Handle list View scroll events
        listView.setOnScrollListener(onScrollListener());
    }

    private void setListViewHeader() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View listHeader = inflater.inflate(R.layout.detail_author_header, null, false);
        headerSpace = listHeader.findViewById(R.id.header_space);

        listView.addHeaderView(listHeader);
    }

    private void setListViewData() {


        list = new ArrayList<String>();
     /*   itemNama = (TextView) findViewById(R.id.itemNama);
        itemBio = (TextView) findViewById(R.id.itemBio);*/
        Intent intent = getIntent();
        String bio = intent.getStringExtra("aut_bio");
        list.add(bio);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.detail_item_listview, R.id.itemBio, list);
        listView.setAdapter(adapter);

      /*  list=new ArrayList<String>();


        adapter = new ArrayAdapter<String>(this, R.layout.detail_item_listview, list);
        listView.setAdapter(adapter);

        itemNama = (TextView) findViewById(R.id.itemNama);
        itemBio = (TextView) findViewById(R.id.itemBio);


        Intent intent = getIntent();
        String aname = intent.getStringExtra("aut_name");
        String bio = intent.getStringExtra("aut_bio");
        Toast.makeText(DetailAuthor.this, "Happy Sunday "+aname, Toast.LENGTH_LONG).show();
        list = intent.getStringArrayListExtra("aname");

        adapter.notifyDataSetChanged();*/
    }

    private AbsListView.OnScrollListener onScrollListener () {
        return new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                // Check if the first item is already reached to top
                if (listView.getFirstVisiblePosition() == 0) {
                    View firstChild = listView.getChildAt(0);
                    int topY = 0;
                    if (firstChild != null) {
                        topY = firstChild.getTop();
                    }

                    int headerTopY = headerSpace.getTop();
                    headerText.setY(Math.max(0, headerTopY + topY));

                    // Set the image to scroll half of the amount that of ListView
                    headerView.setY(topY * 0.5f);
                }
            }
        };
    }

}