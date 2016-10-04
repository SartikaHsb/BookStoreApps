package activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import Controller.Controller;

import com.example.zxcvbn.bookstore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.BooksAdapter;
import model.Book;
import model.DataContentProvider;
import model.ShoppingCartEntry;

public class Detail_Book extends AppCompatActivity{

    TextView author_,summary_,price_,nameBookDet;
    Context context;
    FloatingActionButton fab;
    List<String> getBookAut = new ArrayList<>();
    ListView list;
    private View headerView;
    private View headerSpace;
    TextView notif;
    private String URL = "http://www.g-i.com.my:3002";
    private Animator mCurrentAnimator;
    private int mShortAnimationDuration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_detail__book);*/
        setContentView(R.layout.book_detail);
        headerView = findViewById(R.id.header);


     /*  *//* author_=(TextView) findViewById(R.id.txtAuthor);
        summary_=(TextView) findViewById(R.id.txtSummary);
        price_=(TextView) findViewById(R.id.txtPrice);*//*

        nameBookDet = (TextView) findViewById(R.id.nameBookDet);
        Intent intent = getIntent();
        String book_name_ = intent.getStringExtra("book_name");
        getBookAut = DataContentProvider.getDetailBk(this,book_name_);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        List<String> getBookDes = new ArrayList<>();
        getBookDes.add(getBookAut.get(3));
        getBookDes.add(getBookAut.get(2));
        getBookDes.add(getBookAut.get(7));
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        int[] imageId  = {
                R.drawable.business,
                R.drawable.art,
                R.drawable.fantasy
        };




        String[] stockArr = new String[getBookDes.size()];
        stockArr = getBookDes.toArray(stockArr);


        for(int i=0;i<imageId.length;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("cat", stockArr[i]);
            hm.put("img", imageId[i]+"");
            aList.add(hm);
        }
        String[] from={"cat","img"};//string array
        int[] to={R.id.nameBookDet,R.id.iconDetBook};//int array of views id's

        SimpleAdapter adapter = new SimpleAdapter(this, aList, R.layout.mylist_det_book, from, to);

        setListViewHeader();
        list=(ListView)findViewById(R.id.listDetBook);
        list.setAdapter(adapter);
        list.setDivider(new ColorDrawable(0x99F10529));   //0xAARRGGBB
        list.setDividerHeight(1);

        list.setOnScrollListener(onScrollListener());
        *//*list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                gridview.setAdapter(new BooksAdapter(getActivity(),Integer.toString(position+1)));
                String kl = model.DataContentProvider.getBookCatbyId(getActivity(),Integer.toString(position+1));
                textView1.setVisibility(View.VISIBLE);
                textView1.setText("Filter by: "+kl);
            }
        });*//*


        //Get Global Controller Class object (see application tag in AndroidManifest.xml)

        *//******************  Create Dummy Products Data  ***********//*

        Book productObject = null;
            // Create product model class object
        productObject = new Book("id"+getBookAut.get(0)
                ,getBookAut.get(1)
                ,getBookAut.get(2)
                ,getBookAut.get(3)
                ,getBookAut.get(4)
                ,getBookAut.get(5)
                ,getBookAut.get(6)
                ,getBookAut.get(7));
        //store product object to arraylist in controller
        Controller.setBooks(productObject);



        *//******************  Products Data Creation End   ***********//*


        *//******* Create view elements dynamically and show on activity ******//*

        //Product arraylist size
        int ProductsSize = Controller.getBooksArraylistSize();

        // create the layout params that will be used to define how your
        // button will be displayed

        *//******** Dynamically create view elements - Start **********//*

        for(int j=0;j< ProductsSize;j++)
        {
            // Get probuct data from product data arraylist
            String pName = Controller.getBooks(j).getName();
            String pPrice   = Controller.getBooks(j).getPrice();

            final int index = j;

            //Create click listener for dynamically created button
            fab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Clicked button index
                    Log.i("TAG", "index :" + index);

                    // Get product instance for index
                    Book tempProductObject = Controller.getBooks(index);

                    //Check Product already exist in Cart or Not
                    if(!Controller.getCart().checkProductInCart(tempProductObject))
                    {

                        // Product not Exist in cart so add product to
                        // Cart product arraylist
                        Controller.getCart().setProducts(tempProductObject);

                       *//* Toast.makeText(getApplicationContext(),
                                "Now Cart size: "+Controller.getCart().getCartSize(),
                                Toast.LENGTH_LONG).show();*//*

                    }
                    else
                    {
                        // Cart product arraylist contains Product
                        Toast.makeText(getApplicationContext(),
                                "Product "+(index+1)+" already added in cart.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }*/

}

    private AbsListView.OnScrollListener onScrollListener () {
        return new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                // Check if the first item is already reached to top
                if (list.getFirstVisiblePosition() == 0) {
                    View firstChild = list.getChildAt(0);
                    int topY = 0;
                    if (firstChild != null) {
                        topY = firstChild.getTop();
                    }

                   // int headerTopY = headerSpace.getTop();

                    // Set the image to scroll half of the amount that of ListView
                    headerView.setY(topY * 0.5f);
                }
            }
        };
    }

    private void setListViewHeader() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View listHeader = inflater.inflate(R.layout.header_det_book, null, false);
        //headerSpace = listHeader.findViewById(R.id.header_space);
//        list.addHeaderView(listHeader);
    }
    public void zoom(View v){
        final View thumb1View = findViewById(R.id.header);
        zoomImageFromThumb(thumb1View,R.drawable.ex_book4);
    }
    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container_).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f)).with(ObjectAnimator.ofFloat(expandedImageView,
                View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y,startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }

}
