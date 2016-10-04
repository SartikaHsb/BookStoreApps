package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.zxcvbn.bookstore.R;

import java.util.List;

import model.Author;
import provider.GlobalData;

/**
 * Created by zxcvbn on 14/07/2016.
 */
public class CategoryAdapter extends BaseAdapter{

    Context context;
    String [] authors;
    GlobalData global_data = new GlobalData();
    private static LayoutInflater inflater=null;

    public CategoryAdapter(Context c) {
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

    private class Holder {
        private ImageView imageView;
        private TextView friendName;

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

}
