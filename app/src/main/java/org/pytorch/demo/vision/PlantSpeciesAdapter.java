package org.pytorch.demo.vision;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.pytorch.demo.R;

import java.util.ArrayList;
import java.util.List;

public class PlantSpeciesAdapter extends ArrayAdapter<PlantSpecies> {
    Context context;
    int layoutResourceId;
    List<PlantSpecies> data;
    List<PlantSpecies> dataFiltered;

    public PlantSpeciesAdapter(@NonNull Context context, int resource, List data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
        this.dataFiltered=data;
        this.layoutResourceId = resource;
    }

    @Override
    public int getCount() {
        return dataFiltered.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        PlantSpeciesHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new PlantSpeciesHolder();
            holder.iv_background_left = row.findViewById(R.id.img_listview_plant_species_background_left);
            holder.iv_background_right = row.findViewById(R.id.img_listview_plant_species_background_right);
            holder.txt_name_left = row.findViewById(R.id.txt_listview_plant_species_name_left);
            holder.txt_name_right = row.findViewById(R.id.txt_listview_plant_species_name_right);
            row.setTag(holder);
        } else {
            holder = (PlantSpeciesHolder) row.getTag();
        }

//        PlantSpecies plantSpecies = data.get(position);
        PlantSpecies plantSpecies = dataFiltered.get(position);

        holder.iv_background_left.setImageResource(plantSpecies.backgroundLeft);
        holder.iv_background_right.setImageResource(plantSpecies.backgroundRight);
        holder.txt_name_left.setText(plantSpecies.nameLeft);
        holder.txt_name_right.setText(plantSpecies.nameRight);

        return row;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    dataFiltered = data;
                } else {
                    List<PlantSpecies> filteredList = new ArrayList<>();
                    for (PlantSpecies row : data) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    dataFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = dataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataFiltered = (ArrayList<PlantSpecies>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class PlantSpeciesHolder
    {
        ImageView iv_background_left;
        ImageView iv_background_right;
        TextView txt_name_left;
        TextView txt_name_right;
    }
}
