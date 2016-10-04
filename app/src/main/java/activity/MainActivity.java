package activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.app.Activity;

import com.example.zxcvbn.bookstore.R;

import java.util.HashMap;
import java.util.List;

import adapter.SimpleRecyclerAdapter;
import fragment.AuthorsFragment;
import fragment.BooksFragment;
import fragment.CategoriesFragment;
import fragment.ProfileFragment;
import fragment.ShopCartFragment;
import model.Author;
import model.Book;
import model.DataContentProvider;
import provider.GlobalData;
import provider.SessionManager;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.RoundedImage;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment,drawerFragment1;
    Fragment fragmentBook = null;
    Bitmap bm;
    SessionManager session;
    SharedPreferences pref;
    SharedPreferences.Editor e_pref ;

    RoundedImage roundedImage;
//    String titleBook = getString(R.string.app_name);
    String[] category;
    ImageButton btn_filter;
    Fragment fragment = null;
    TextView username_app;
    DrawerLayout drawerlayout;
    ImageView imageView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment = new BooksFragment();
        username_app = (TextView) findViewById(R.id.username_app);
        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, drawerlayout, mToolbar);
        drawerFragment.setDrawerListener(this);

        imageView1 = (ImageView) findViewById(R.id.pp);
        bm = BitmapFactory.decodeResource(getResources(),R.drawable.person_ex);
        roundedImage = new RoundedImage(bm);
        imageView1.setImageDrawable(roundedImage);

        session = new SessionManager(getApplicationContext());

        //getting the session key from shpref
        pref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        e_pref = pref.edit();

        String session_key = pref.getString("session_key","");
        Log.d("Sessionkey",session_key);
        if(session.isLoggedIn()){
            Toast.makeText(MainActivity.this, "Session TRUE", Toast.LENGTH_SHORT).show();
            HashMap<String, String> user = session.getUserDetails();
            username_app.setText(user.get("username"));
        }
        Toast.makeText(MainActivity.this, "Sessionkey"+session_key, Toast.LENGTH_SHORT).show();

        //onclick header nav_bar
        RelativeLayout profile =(RelativeLayout)findViewById(R.id.nav_header_container);
        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                displayView(3);
                drawerlayout.closeDrawer(GravityCompat.START);
                //this.drawerlayout.closeDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_search) {
            return true;
        }*/

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            fragment = new ShopCartFragment();

            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {

        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new BooksFragment();
                title = getString(R.string.title_books);
                break;
            case 1:
                fragment = new AuthorsFragment();
                title = getString(R.string.title_authors);
                break;
            case 2:
                fragment = new CategoriesFragment();
                title = getString(R.string.title_categories);
                break;
            case 3:
                fragment = new ProfileFragment();
                title = getString(R.string.title_profile);
                break;
            case 4:
                session.logoutUser();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public static void startMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }
}