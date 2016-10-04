package adapter;

/**
 * Created by sartikahasibuan on 7/8/2016.
 */
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zxcvbn.bookstore.R;
import com.squareup.picasso.Picasso;

import activity.Detail_Book;
import provider.GlobalData;
import model.DataContentProvider;

public class BooksAdapter extends BaseAdapter {

    String [] result;
    Context context;
    String [] imageCover;
    String[] price;
    String[] getBookTitle;
    String[] getBookPrice;
    String[] getBookCover;
    private static LayoutInflater inflater=null;
    GlobalData global_data = new GlobalData();
    DataContentProvider dataContentProvider;
    private Animator mCurrentAnimator;

    private int mShortAnimationDuration;

    private String URL = "http://www.g-i.com.my:3002";

    public BooksAdapter(Context c, String id) {
        // TODO Auto-generated constructor stub
        context= c;

        if(id!=null) {
           getBookTitle = DataContentProvider.getBookTitle(context, id);
           getBookPrice = DataContentProvider.getBookPrice(context, id);

        }
        else
        {
            getBookTitle = DataContentProvider.getBookTit(context);
            getBookPrice = DataContentProvider.getBookPr(context);
            getBookCover = DataContentProvider.getCoverBook(context);
        }
        int[] getCover = DataContentProvider.getCover(context);
        result=getBookTitle;
        price = getBookPrice;
        imageCover = getBookCover;
        //imageId=getCover;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
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

    public class Holder
    {
        TextView tvTitle;
        TextView tvPrice;
        ImageView img;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;
        ImageView imageView = new ImageView(this.context);

        rowView = inflater.inflate(R.layout.book_list, null);
        holder.tvTitle=(TextView) rowView.findViewById(R.id.textView1);
        holder.tvPrice=(TextView) rowView.findViewById(R.id.textView2);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);


        holder.tvTitle.setText(result[position]);
        holder.tvPrice.setText("RM "+price[position]);
        Picasso.with(context).load(URL+imageCover[position]).into(holder.img);
        Log.d("URL Images",""+URL+imageCover[position]);

        //holder.img.setImageResource(imageId[position]);
        imageView.setLayoutParams(new GridView.LayoutParams(200,150));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_LONG).show();
                String res=result[position];
//                int id = (Integer) v.getTag();
                //zoomImageFromThumb(v, imageId[position]);
                Intent intent = new Intent(context, Detail_Book.class);
                intent.putExtra("book_name",res);
                context.startActivity(intent);
            }
        });

        return rowView;
    }

    private void zoomImageFromThumb(final View thumbView, int imageResId) {
            // If there's an animation in progress, cancel it immediately and
            // proceed with this one.
            if (mCurrentAnimator != null) {
                mCurrentAnimator.cancel();
            }

            // Load the high-resolution "zoomed-in" image.
            final ImageView expandedImageView = (ImageView) ((Activity) context)
                    .findViewById(R.id.expanded_image);
            expandedImageView.setImageResource(imageResId);

            // Calculate the starting and ending bounds for the zoomed-in image.
            // This step
            // involves lots of math. Yay, math.
            final Rect startBounds = new Rect();
            final Rect finalBounds = new Rect();
            final Point globalOffset = new Point();

            // The start bounds are the global visible rectangle of the thumbnail,
            // and the
            // final bounds are the global visible rectangle of the container view.
            // Also
            // set the container view's offset as the origin for the bounds, since
            // that's
            // the origin for the positioning animation properties (X, Y).
            thumbView.getGlobalVisibleRect(startBounds);
            ((Activity) context).findViewById(R.id.container)
                    .getGlobalVisibleRect(finalBounds, globalOffset);
            startBounds.offset(-globalOffset.x, -globalOffset.y);
            finalBounds.offset(-globalOffset.x, -globalOffset.y);

            // Adjust the start bounds to be the same aspect ratio as the final
            // bounds using the
            // "center crop" technique. This prevents undesirable stretching during
            // the animation.
            // Also calculate the start scaling factor (the end scaling factor is
            // always 1.0).
            float startScale;
            if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds
                    .width() / startBounds.height()) {
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
            // begins,
            // it will position the zoomed-in view in the place of the thumbnail.
            thumbView.setAlpha(0f);
            expandedImageView.setVisibility(View.VISIBLE);

            // Set the pivot point for SCALE_X and SCALE_Y transformations to the
            // top-left corner of
            // the zoomed-in view (the default is the center of the view).
            expandedImageView.setPivotX(0f);
            expandedImageView.setPivotY(0f);

            // Construct and run the parallel animation of the four translation and
            // scale properties
            // (X, Y, SCALE_X, and SCALE_Y).
            AnimatorSet set = new AnimatorSet();
            set.play(
                    ObjectAnimator.ofFloat(expandedImageView, View.X,
                            startBounds.left, finalBounds.left))
                    .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                            startBounds.top, finalBounds.top))
                    .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                            startScale, 1f))
                    .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y,
                            startScale, 1f));
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

            // Upon clicking the zoomed-in image, it should zoom back down to the
            // original bounds
            // and show the thumbnail instead of the expanded image.
            final float startScaleFinal = startScale;
            expandedImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mCurrentAnimator != null) {
                        mCurrentAnimator.cancel();
                    }

                    // Animate the four positioning/sizing properties in parallel,
                    // back to their
                    // original values.
                    AnimatorSet set = new AnimatorSet();
                    set.play(
                            ObjectAnimator.ofFloat(expandedImageView, View.X,
                                    startBounds.left))
                            .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                                    startBounds.top))
                            .with(ObjectAnimator.ofFloat(expandedImageView,
                                    View.SCALE_X, startScaleFinal))
                            .with(ObjectAnimator.ofFloat(expandedImageView,
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
