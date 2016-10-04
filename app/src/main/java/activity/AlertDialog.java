package activity;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.zxcvbn.bookstore.R;

/**
 * Created by sartikahasibuan on 7/19/2016.
 */
public class AlertDialog implements View.OnClickListener {
    private Context context = null  ;
    private Dialog dialog = null;
    private Button buttonOk = null;
    private TextView tvMessage = null;
    private OnDialogDismissListener dialogDismissListener = null;

    public AlertDialog(Context context) {
        this.context = context;

        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);
        dialog.setCancelable(false);

        buttonOk = (Button) dialog.findViewById(R.id.dialogButtonOK);
        buttonOk.setOnClickListener(this);

        tvMessage = (TextView) dialog.findViewById(R.id.dialog_text);
    }

    public void setMessage(int resourceId) {
        setMessage(context.getString(resourceId));
    }

    public void setMessage(CharSequence message) {
        tvMessage.setText(message);
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public void onClick(View v) {
        /*switch(v.getId()) {
            case R.id.button_ok:*/

                if(dialogDismissListener != null)
                    dialogDismissListener.onDismiss();
         /*       break;
        }*/
    }

    public void setOnDialogDismissListener(OnDialogDismissListener listener) {
        dialogDismissListener = listener;
    }

    public interface OnDialogDismissListener {
        public void onDismiss();
    }
}