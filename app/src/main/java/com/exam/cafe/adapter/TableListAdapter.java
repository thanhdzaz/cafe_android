package com.exam.cafe.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.exam.cafe.R;
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
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Table table = this.listData.get(position);

        int num = table.getNum();

        holder.table_code.setText(""+num);
        holder.floor_code.setText("Tầng: " + table.getFloor());

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
    }
}
