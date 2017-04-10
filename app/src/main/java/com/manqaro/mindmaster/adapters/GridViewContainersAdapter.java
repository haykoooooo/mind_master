package com.manqaro.mindmaster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.manqaro.mindmaster.R;

import java.util.ArrayList;

public class GridViewContainersAdapter extends BaseAdapter {
    private Context context;
    private final ArrayList<Integer> imageResource;

    public GridViewContainersAdapter(Context context, ArrayList<Integer> imageResource) {
        this.context = context;
        this.imageResource = imageResource;
    }

    @Override
    public int getCount() {
        return imageResource.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.container_item, null);
            ImageView imageView = (ImageView) grid.findViewById(R.id.containerItem);
            imageView.setImageResource(imageResource.get(position));
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}