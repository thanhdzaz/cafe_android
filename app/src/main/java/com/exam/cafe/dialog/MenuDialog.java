package com.exam.cafe.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exam.cafe.R;

public class MenuDialog extends Dialog {

    public interface OnSubmit {
        public void onSubmit (boolean click);
    }

    public Context context;

    private EditText editTextFullName;
    private Button buttonOK;
    private Button buttonCancel;

    public MenuDialog.OnSubmit listener;

    public MenuDialog(Context context, OnSubmit listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_menu);

        this.buttonOK = (Button) findViewById(R.id.add_menu_hehe);


        this.buttonOK .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKClick();
            }
        });
    }

    // User click "OK" button.
    private void buttonOKClick()  {
        if(this.listener!= null)  {
            this.listener.onSubmit(true);
        }

        Toast.makeText(this.getContext(),"HEHEHEHHE",Toast.LENGTH_SHORT).show();

        this.dismiss(); // Close Dialog
    }

    // User click "Cancel" button.
    private void buttonCancelClick()  {
        this.dismiss();
    }
}