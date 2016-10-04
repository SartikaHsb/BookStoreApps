package adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.zxcvbn.bookstore.R;
import android.widget.Toast;
import android.content.Intent;
import java.util.List;

import activity.DetailAuthor;
import model.Author;
import model.DataContentProvider;
import provider.GlobalData;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
/**
 * Created by zxcvbn on 14/07/2016.
 */
public class AuthorsAdapter extends BaseAdapter{

    Context context;
    String [] result;
    String [] authors;
    int [] imageId;
    GlobalData global_data = new GlobalData();
    public Author DetailAllAuthors;
    private static LayoutInflater inflater=null;
    private List<Author> abc;

    public AuthorsAdapter(Context c) {
        // TODO Auto-generated constructor stub

        context= c;
        authors = getAuthors(c);
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public String[] getAuthors(Context c){
        global_data = (GlobalData) c.getApplicationContext();
        List<Author> auths = global_data.AllAuthors;
        String[] title = new String[auths.size()];
        int i=0;
        for(Author model : auths){
            title[i] = model.getName();
            i++;
        }
        return  title;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return authors.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

 /*   private class Holder {
        ImageView imageView;
        TextView friendName;

        public Holder(View v) {
            imageView = (ImageView) v.findViewById(R.id.image_view);
            friendName = (TextView) v.findViewById(R.id.text);
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;


        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new Holder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (Holder) convertView.getTag();
        }



        holder.friendName.setText(authors[position]);

        String firstLetter = String.valueOf(authors[position].charAt(0));

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getColor(getItem(position));
        //int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, color); // radius in px

        holder.imageView.setImageDrawable(drawable);



        return convertView;


    }
*/

    public class Holder
    {
        ImageView imageView;
        TextView friendName;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.item_listview, null);
        holder.friendName=(TextView) rowView.findViewById(R.id.text);
        holder.imageView=(ImageView) rowView.findViewById(R.id.image_view);

        holder.friendName.setText(authors[position]);
        String firstLetter = String.valueOf(authors[position].charAt(0));

        ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
        // generate random color
        int color = generator.getColor(getItem(position));
        //int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, color); // radius in px

        holder.imageView.setImageDrawable(drawable);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+authors[position], Toast.LENGTH_LONG).show();
                //context.startActivity(new Intent(context, AnimateToolbar.class));

//                String name = DetailAllAuthors.getName();
                String x = authors[position];
                String getBio = DataContentProvider.getAuthor(context,x );

                    Intent intent = new Intent(context, DetailAuthor.class);
                    intent.putExtra("aut_name",""+authors[position]);
                    intent.putExtra("aut_bio",""+getBio);
                    context.startActivity(intent);


            }
        });

        return rowView;

    }


}
