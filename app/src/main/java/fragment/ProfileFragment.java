package fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.zxcvbn.bookstore.R;

import java.util.HashMap;

import activity.MainActivity;
import model.GeneralResponse;
import model.UpdateUser;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import provider.SessionManager;
import util.RoundedImage;
/**
 * Created by zxcvbn on 06/07/2016.
 */
public class ProfileFragment extends Fragment{

    SessionManager session;

    EditText et_username,et_ln,et_fn,et_dob;
    TextView tv_username,tv_ln, tv_fn, tv_dob;

    TextView username_profile;

    Button save;
    UpdateUser u_user;
    HashMap<String, String> user;
    ImageView imageView1;
    RoundedImage roundedImage;
    private Animator mCurrentAnimator;

   public ProfileFragment(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        session = new SessionManager(getActivity());
        user = session.getUserDetails();

        et_username = (EditText) rootView.findViewById(R.id.et_username);
        et_fn = (EditText) rootView.findViewById(R.id.et_fn);
        et_ln = (EditText) rootView.findViewById(R.id.et_ln);
        et_dob = (EditText) rootView.findViewById(R.id.et_dob);

        tv_username = (TextView)  rootView.findViewById(R.id.profile_name);
        tv_fn = (TextView)  rootView.findViewById(R.id.et_fn);
        tv_ln = (TextView)  rootView.findViewById(R.id.et_ln);
        tv_dob = (TextView)  rootView.findViewById(R.id.et_dob);
        username_profile = (TextView) rootView.findViewById(R.id.username_profile);


        //profile pictures
        imageView1 = (ImageView) rootView.findViewById(R.id.imageView1);
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.person_ex);
        roundedImage = new RoundedImage(bm);
        imageView1.setImageDrawable(roundedImage);

        setDummyData();

        //save
        save = (Button) rootView.findViewById(R.id.btn_save_prop);

        //set dummy data
        username_profile.setText("Siti Nurbaya");


       /* username_prof.setText(user.get("siti"));
        fn_prof.setText(user.get("siti"));
        ln_prof.setText(user.get("nurbaya"));
        dob.setText(user.get("27-10-1997"));
*/
        /*Data dummy*/
        u_user = new UpdateUser();
        u_user.setUsername("siti");
        u_user.setFirstName("nurbaya");
        u_user.setLastName("leong1");
        u_user.setDob("27-10-1997");

        /*u_user.setUsername(username_prof.getText().toString());
        u_user.setFirstName(fn_prof.getText().toString());
        u_user.setLastName(ln_prof.getText().toString());
        u_user.setDob(dob.getText().toString());*/


        save.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<GeneralResponse> call = apiService.update_user(user.get("token"),"36",u_user);

                call.enqueue(new Callback<GeneralResponse>() {
                    @Override
                    public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                        if(response.isSuccessful()){
                            Log.d("ResponseCode",""+response.code());
                            Log.d("ResponseMessage",""+response.message());
                            GeneralResponse result = response.body();
                            Log.d("Result",""+result.getMessage());
                            Toast.makeText(getContext(), "Successfull "+response.code()+" "+response.message()+" "+result.getMessage() , Toast.LENGTH_LONG).show();

                            //back to MainActivity
                            MainActivity.startMainActivity(getActivity());

                        }else{
                            Log.d("ResponseCode",""+response.code());
                            Log.d("ResponseMessage",""+response.message());
                            Log.w("Not Success", "Response Body" + response.errorBody().toString());
                            Toast.makeText(getContext(), "Unsuccessfull, "+response.code()+" "+response.message()+" "+response.errorBody().toString() , Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GeneralResponse> call, Throwable t) {
                        Log.w("Failed ", t.toString());
                        Toast.makeText(getContext(), "Failed to Update User" , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return rootView;
    }

    public void setDummyData(){
        tv_username.setText("siti");
        tv_fn.setText("Siti");
        tv_ln.setText("Nurbaya");
        tv_dob.setText("27-09-1998");

        et_username.setText("siti");
        et_fn.setText("Siti");
        et_ln.setText("Nurbaya");
        et_dob.setText("27-09-1998");

    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // If there's an animation in progress, cancel it immediately and
        // proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) ((Activity) context)
                .findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();


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
    }*/

}
