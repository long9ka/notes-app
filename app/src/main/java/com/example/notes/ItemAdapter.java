package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Model> {
    private Context context;
    private int resource;
    private List<Model> objects;

    private class ViewHolder {
        TextView title;
        TextView tag;
        TextView time;
    }

    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Model> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        // handle ViewHolder

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.title = convertView.findViewById(R.id.title);
        viewHolder.tag = convertView.findViewById(R.id.tag);
        viewHolder.time = convertView.findViewById(R.id.time);

        // set text View holder
        viewHolder.title.setText(objects.get(position).getTitle());
        viewHolder.tag.setText(objects.get(position).getTag());
        viewHolder.time.setText(DateFormat.getDateInstance().format(objects.get(position).getTime()));

        return convertView;
    }
}
