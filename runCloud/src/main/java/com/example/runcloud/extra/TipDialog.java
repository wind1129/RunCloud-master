package com.example.runcloud.extra;

import com.example.runcloud.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Administrator on 2015/6/23.
 */
public class TipDialog extends Dialog {
    public TipDialog(Context context, int theme) {
        super(context, theme);
    }

    public TipDialog(Context context) {
        super(context);
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {

        private Context context;
        private String message;
        private View contentView;

        public Builder(Context context) {
            this.context = context;
        }

        /**
         * Set the Dialog message from String
         *
         * @return
         */
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }


        /**
         * Set a custom content view for the Dialog.
         * If a message is set, the contentView is not
         * added to the Dialog...
         *
         * @param v
         * @return
         */
        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Create the custom dialog
         */
        public TipDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final TipDialog dialog = new TipDialog(context, R.style.dialog);
            View layout = inflater.inflate(R.layout.warm_dialog, null);
            //dialog.addContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            // set the content message
            if (message != null) {
                ((TextView) layout.findViewById(R.id.tv_warmdialog)).setText(message);
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
