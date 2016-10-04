package fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.zxcvbn.bookstore.R;

import java.util.ArrayList;
import java.util.List;

import activity.MainActivity;
import adapter.AuthorsAdapter;
import model.Author;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.ListView;

import com.example.zxcvbn.bookstore.R;

/**
 * Created by zxcvbn on 06/07/2016.
 */
public class AuthorsFragment extends Fragment{

    private ListView listView;
    List<Author> list_author;
    private static final String TAG = MainActivity.class.getSimpleName();
    public AuthorsFragment()
    {

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.w(TAG, "Call onActivityCreated");
        super.onActivityCreated(savedInstanceState);

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.w(TAG, "Call onCreate");
        showAllAuthors();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.w(TAG, "Call onCreateView");
        View view = inflater.inflate(R.layout.content_main, container, false);
        listView = (ListView) view.findViewById(R.id.list_item);

        listView.setAdapter(new AuthorsAdapter(getActivity()));

        // Inflate the layout for this fragment
        return view;
    }

    private void showAllAuthors(){
        Log.w(TAG, "Call showAllBooks");

        //call api
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Author>> call = apiService.getAuthors();
        call.enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                list_author=  response.body();
                Log.w(TAG, "Response to get Books ");
                Log.w(TAG, "Number of book received: " + list_author.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                Log.w(TAG, t.toString());
            }
        });
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
