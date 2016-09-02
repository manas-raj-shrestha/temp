package com.android.csit.csit.dashboard;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.csit.csit.R;

public class MenuListAdapter extends BaseAdapter {
    String[] menuItems = new String[]{"Profile", "My Notes", "Library"};
    int[] menuIcons = new int[]{R.drawable.notes, R.drawable.notes, R.drawable.filesi};
    Context context;

    public MenuListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return menuItems.length;
    }

    @Override
    public Object getItem(int i) {
        return menuItems[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.lv_item, viewGroup, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_menu_name);
        textView.setText(menuItems[i]);
        textView.setTextColor(Color.BLACK);

        ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);
        imageView.setImageDrawable(context.getResources().getDrawable(menuIcons[i]));

        return view;
    }
}
