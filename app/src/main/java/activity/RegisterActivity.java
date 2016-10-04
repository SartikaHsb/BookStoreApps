package activity;

/**
 * Created by zxcvbn on 08/07/2016.
 */
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zxcvbn.bookstore.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import model.RequestUser;
import model.User;
import model.RegisterRespon;
import rest.ApiClient;
import rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import util.Util;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, AlertDialog.OnDialogDismissListener {

    EditText first_name;
    EditText last_name;
    EditText dob;
    EditText username;
    EditText password;
    Util util_;
    RequestUser user;
    Button register;
    DialogManager alert = new DialogManager();
    ProgressBar progressBar;
    private DatePickerDialog dobPickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        username = (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        //dob
        dob = (EditText) findViewById(R.id.dob);
        dob.setInputType(InputType.TYPE_NULL);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();

        util_ = Util.getInstance(getApplicationContext());

        user = new RequestUser();
        //make validation of form
        //button onclick -> call api
        //redirect to login
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SaveRegisterData();
            }
        });
    }

    public void SaveRegisterData(){
        //send asyntask :)
        user.setFirstName(first_name.getText().toString());
        user.setLastName(last_name.getText().toString());
        user.setDob(dob.getText().toString());
        user.setUsername(username.getText().toString());
        String hash_pwd = util_.md5(password.getText().toString());
        user.setPassword(hash_pwd);

       /* user.setFirstName("Lorem");
        user.setLastName("Ipsum");
        user.setDob("23-09-1992");
        user.setUsername("whoamii");
        user.setPassword("900150983cd24fb0d6963f7d28e17f72");*/

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RegisterRespon> call = apiService.register(user);
        call.enqueue(new Callback<RegisterRespon>() {
            @Override
            public void onResponse(Call<RegisterRespon> call, Response<RegisterRespon> response) {
                //User response_user= response.body();
                if (response.isSuccessful())
                {
                    Log.d("ResponseCode",""+response.code());
                    Log.d("ResponseMessage",""+response.message());
                    RegisterRespon model = response.body();
                    Log.d("id user",""+model.getData().getUser().getId());

                    util_.ToastMessage(getApplicationContext(),"Congrats! Register Success!");

                    //startLoading(RegisterActivity.this);
                    /*Intent intent = new Intent(getApplicationContext(), LoadingView.class);
                    startActivity(intent);*/


                    /*AlertDialog dialog = new AlertDialog(RegisterActivity.this);
                    dialog.setMessage("Congrats!");
                    dialog.show();*/

                   /* final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this,
                            R.style.Base_MyTheme);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    progressDialog.show();*/

                    /*ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
                    pb.getIndeterminateDrawable().setColorFilter(Color.parseColor("#80DAEB"),
                        android.graphics.PorterDuff.Mode.MULTIPLY);*/

                    //LoadingView lv = new LoadingView(RegisterActivity.this,null);


                    //send intent to the layout login
                    startLoginActivity(RegisterActivity.this);
                    //dialog.dismiss();
                    finish();
                }
                else
                {
                    alert.showAlertDialog(RegisterActivity.this, "Failed to Register", ""+response.message(), false);
                    Log.d("ResponseCode",""+response.code());
                    Log.d("ResponseMessage",""+response.message());
                    Log.w("Null responseBody", "Response Body" + response.errorBody().toString());

                }

            }

            @Override
            public void onFailure(Call<RegisterRespon> call, Throwable t) {
                // Log error here since request failed
                Log.w("Failed ", t.toString());
            }
        });

    }

    public void regis(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static void startRegActivity(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }
   /* public static void startLoading(Activity activity) {
        Intent intent = new Intent(activity, LoadingView.class);
        activity.startActivity(intent);
    }
*/

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
       // moveTaskToBack(true);
    }


    public static void startLoginActivity(Activity activity) {
        Intent intent = new Intent(activity, LoginFormActivity.class);
        activity.startActivity(intent);

    }

    private void setDateTimeField()
    {
        dob.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        dobPickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dob.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(newCalendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /*case R.id.button_alert:
                AlertDialog dialog = new AlertDialog(this);
                dialog.setMessage("Congrats!");
                dialog.show();
                break;*/
            case R.id.dob:
                dobPickerDialog.show();
                break;
        }
    }

    @Override
    public void onDismiss() {
        Toast.makeText(this, "Dialog Closed", Toast.LENGTH_SHORT).show();
    }

}


