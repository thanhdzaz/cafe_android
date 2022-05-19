package com.exam.cafe.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.exam.cafe.R;
import com.exam.cafe.db.Database;
import com.exam.cafe.dto.Table;

public class CustomDialog2 extends Dialog {

    public interface EditListener {
        public void editClick(boolean click);
    }

    public Context context;

    private EditText editTextFullName;
    private Button buttonOK;
    private Button buttonCancel;
    private Table table;

    public CustomDialog2.EditListener listener;

    public CustomDialog2(Context context, CustomDialog2.EditListener listener, Table table) {
        super(context);
        this.context = context;
        this.listener = listener;
        this.table = table;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_table);

        this.buttonOK = (Button) findViewById(R.id.accept_edit);
        this.buttonCancel  = (Button) findViewById(R.id.cancel_edit);



        this.buttonOK .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOKClick();
            }
        });
        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });
    }

    // User click "OK" button.
    private void buttonOKClick()  {
        if(this.listener!= null)  {
            this.listener.editClick(true);
        }
        EditText number = findViewById(R.id.editSobanTxt);
        EditText floor = findViewById(R.id.editFloorTxt);
        EditText status = findViewById(R.id.editStatusTxt);

        String txtNum = number.getText().toString();
        String txtFloor = floor.getText().toString();
        String txtStatus = status.getText().toString();

        if(txtNum.matches("") || txtFloor.matches("") || txtStatus.matches("")){
            TextView err = findViewById(R.id.err_edit);
            err.setText("Không được bỏ trống thông tin");
//            Toast.makeText(this.getContext(),"Không được bỏ trống thông tin", Toast.LENGTH_LONG);
        }else{
            Database db = new Database(getContext());
            db.executeSQL("UPDATE TableList SET number = " + txtNum + ", floor = " + txtFloor + ", status = \"" + txtStatus + "\" WHERE id =" + table.getId());
            this.dismiss(); // Close Dialog
        }
    }

    // User click "Cancel" button.
    private void buttonCancelClick()  {
        this.dismiss();
    }
    @Override
    public void onStart(){
        super.onStart();
        EditText number = findViewById(R.id.editSobanTxt);
        EditText floor = findViewById(R.id.editFloorTxt);
        EditText status = findViewById(R.id.editStatusTxt);
        number.setText(""+table.getNum());
        floor.setText(""+table.getFloor());
        status.setText(table.getStatus());

    }
}