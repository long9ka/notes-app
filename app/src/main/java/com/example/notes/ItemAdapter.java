package com.example.notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Model> {
    private Context context;
    private int resource;
    private List<Model> objects;
    private List<Model> getObjects;
    private List<Integer> realPos;

    public List<Integer> getRealPos() {
        return realPos;
    }

    private void initRealPos() {
        realPos = new ArrayList<>();
        for(int i = 0; i < getObjects.size(); i++) {
            realPos.add(i);
        }
    }

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
        this.getObjects = new ArrayList<>(objects);
        initRealPos();
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

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    initRealPos();
                    filterResults.values = getObjects;
                } else {
                    realPos.clear();
                    List<Model> list = new ArrayList<>();
                    String string = constraint.toString().toLowerCase().trim();
                    for (int i = 0; i < getObjects.size(); i++) {
                        if (getObjects.get(i).getTitle().toLowerCase().contains(string)|| getObjects.get(i).getTag().toLowerCase().contains(string)) {
                            list.add(getObjects.get(i));
                            realPos.add(i);
                        }
                    }
                    filterResults.values = list;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                objects.clear();
                objects.addAll((Collection<? extends Model>) results.values);
                notifyDataSetChanged();
            }
        };

    }

}
