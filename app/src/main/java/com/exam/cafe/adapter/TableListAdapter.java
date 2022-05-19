package com.exam.cafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.exam.cafe.R;
import com.exam.cafe.db.Database;
import com.exam.cafe.dialog.CustomDialog;
import com.exam.cafe.dialog.CustomDialog2;
import com.exam.cafe.dto.Table;

import java.util.List;

public class TableListAdapter extends BaseAdapter {
    private List<Table> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public TableListAdapter(Context aContext,  List<Table> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public Context getContext() {
        return context;
    }

    private void buttonOpenDialogClicked(Table table)  {
        CustomDialog.FullNameListener listener = new CustomDialog.FullNameListener() {
            @Override
            public void fullNameEntered(boolean click) {
                if (click == true){
                    Database db = new Database(getContext());
                    boolean dele = db.executeSQL("delete from TableList where id = "+ table.getId());
                    if (dele){
                        Toast.makeText(getContext(),"Xoá bàn thành công", Toast.LENGTH_LONG);
                    }else{
                        Toast.makeText(getContext(),"Xoá bàn thất bại", Toast.LENGTH_LONG);
                    }
                }else{
                    Toast.makeText(getContext(),"Xoá bàn thất bại !!", Toast.LENGTH_LONG);
                }
            }
        };
        final CustomDialog dialog = new CustomDialog(getContext(), listener);

        dialog.show();
    }

    private void buttonOpenDialogClicked2(Table table)  {
        CustomDialog2.EditListener listener = new CustomDialog2.EditListener() {
            @Override
            public void editClick(boolean click) {
            }
        };
        final CustomDialog2 dialog = new CustomDialog2(getContext(), listener, table);

        dialog.show();
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_table_layout, null);
            holder = new ViewHolder();
            holder.table_code = (TextView) convertView.findViewById(R.id.table_code);
            holder.floor_code = (TextView) convertView.findViewById(R.id.floor_code);
            holder.status = (TextView) convertView.findViewById(R.id.table_status);
            holder.delete = (Button) convertView.findViewById(R.id.delete_table);
            holder.edit = (Button) convertView.findViewById(R.id.edit_table);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Table table = this.listData.get(position);

        int num = table.getNum();

        holder.table_code.setText(""+num);
        holder.floor_code.setText("Tầng: " + table.getFloor());
        holder.status.setText("Trạng thái bàn: " + table.getStatus());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOpenDialogClicked(table);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonOpenDialogClicked2(table);
            }
        });
        return convertView;
    }

    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        TextView table_code;
        TextView floor_code;
        TextView status;
        Button delete;
        Button edit;
        TextView table_number_edit;
        TextView table_floor_dit;
        TextView table_status_edit;
    }
}
