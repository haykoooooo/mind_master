package com.manqaro.mindmaster.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.manqaro.mindmaster.R;

public class GridViewTableAdapter extends BaseAdapter {
    private Context context;
    private final int[] imageResource;

    public GridViewTableAdapter(Context context, int[] imageResource) {
        this.context = context;
        this.imageResource = imageResource;
    }

    @Override
    public int getCount() {
        return imageResource.length;
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
            grid = inflater.inflate(R.layout.pairs_game_cell_item, null);
            ImageView imageView = (ImageView) grid.findViewById(R.id.pairsGameContainerItem);
            imageView.setImageResource(imageResource[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}