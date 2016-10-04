package util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sartikahasibuan on 7/14/2016.
 */
public class Util {
    protected Context context;
    private static Util Util;

    protected Util(Context context) {
        this.context = context;
    }

    public static Util getInstance(Context context) {
        if(Util == null) {
            Util = new Util(context.getApplicationContext());
        }
        return Util;
    }

    public Context getContext() {
        return context;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        return networkinfo!=null && networkinfo.isConnected();
    }

    public static String md5(String s) {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")),0,s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public void ToastMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    ProgressDialog progressDialog;

    public void showProgressDialog(String title, String msg, boolean cancelable) {
        if(context == null) return;
        try {
            progressDialog = ProgressDialog.show(context, title, msg, true, false);
            progressDialog.setCancelable(cancelable);
        } catch(Exception e) {
            Log.e("Progress Dialog", "Progress Dialog Exception " + e.getMessage());
        }
    }
    public void hideProgressDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
