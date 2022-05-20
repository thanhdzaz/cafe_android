package com.exam.cafe.adapter;

import static android.widget.Toast.LENGTH_LONG;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.exam.cafe.R;
import com.exam.cafe.db.Database;
import com.exam.cafe.dto.Menu;
import com.exam.cafe.dto.Table;
import com.exam.cafe.ui.EditMenu;
import com.exam.cafe.ui.MenuCafe;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MenuListAdapter extends BaseAdapter {
    private List<Menu> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private MenuListAdapter self;


    public void setSelf(MenuListAdapter m){
        this.self = m;
    };

    public void refreshDB(){
        Log.e(context.getPackageName(), "refreshDB: hehe" );
            List<Menu> list = new ArrayList<Menu>();

            String sql = "select * from MenuList";

            Database db = new Database(context);

            Cursor cs = db.retrieveData(sql);

            while (cs.moveToNext()) {
                list.add(new Menu(cs.getInt(0),cs.getString(1),cs.getInt(2)));
            }
            this.listData = list;
            self.notifyDataSetChanged();

    }

    public void seach(String s){

        if(s.matches("")){
            refreshDB();
        }else{


        List<Menu> list = new ArrayList<Menu>();

        String sql = "select * from MenuList where name like '%"+s+"%'";

        Database db = new Database(context);

        Cursor cs = db.retrieveData(sql);

        while (cs.moveToNext()) {
            list.add(new Menu(cs.getInt(0),cs.getString(1),cs.getInt(2)));
        }
        this.listData = list;
        self.notifyDataSetChanged();
    }
    }

    public MenuListAdapter(Context aContext,  List<Menu> listData) {
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
            convertView = layoutInflater.inflate(R.layout.menu_item_list, null);
            holder = new ViewHolder();
            holder.name_menu = (TextView) convertView.findViewById(R.id.name_menu);
            holder.price_menu = (TextView) convertView.findViewById(R.id.price_menu);
            holder.delete = convertView.findViewById(R.id.menu_delete);
            holder.edit = convertView.findViewById(R.id.menu_edit);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Menu table = this.listData.get(position);

        int num = table.getPrice();

        holder.name_menu.setText("Tên: "+ table.getName());
        holder.price_menu.setText("Giá: " + num);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditMenu.class);
                intent.putExtra("name", table.getName());
                intent.putExtra("id", table.getId());
                intent.putExtra("price", table.getPrice());
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
                        builder.setTitle("Xác nhận");
                        builder.setMessage("Xóa món "+ table.getName());

                        builder.setNeutralButton(R.string.cancel,(dialog, which) -> {

                        });
                        builder.setPositiveButton(R.string.ok,(dialog, which) -> {
                            Database db = new Database(context);
                            String sql = String.format("DELETE FROM MenuList WHERE id = %s",table.getId());
                            if(db.executeSQL(sql)){
                                Toast.makeText(context,"Xóa thành công 🎉🎉",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context,"Xóa thất bại kiểm tra lại!!",Toast.LENGTH_SHORT).show();
                            }
                            refreshDB();

                        });

                        builder.show();
//                Toast.makeText(context,"Xoá bàn",Toast.LENGTH_SHORT).show();
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
        TextView name_menu,price_menu;
        MaterialButton delete,edit;
    }
}
