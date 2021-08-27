package org.pytorch.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.pytorch.demo.vision.PlantFamily;
import org.pytorch.demo.vision.PlantFamilyAdapter;

import java.util.ArrayList;

public class PlantListGenus extends AppCompatActivity {
    SearchView searchview_genus_list;
    TextView txt_family_tab;
    TextView txt_genus_tab;
    TextView txt_species_tab;
    ListView custom_listview_plant_genus;
    PlantFamilyAdapter adapter;

    ArrayList<PlantFamily> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list_genus);
        createData();

        searchview_genus_list = findViewById(R.id.searchview_genus_list);
        txt_family_tab = findViewById(R.id.txt_genus_list_family_tab);
        txt_genus_tab = findViewById(R.id.txt_genus_list_genus_tab);
        txt_species_tab = findViewById(R.id.txt_genus_list_species_tab);
        custom_listview_plant_genus = findViewById(R.id.custom_listview_plant_genus);
        SearchView theFilter = (SearchView) findViewById(R.id.searchview_genus_list);

        SpannableString content = new SpannableString("CHI");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        txt_genus_tab.setText(content);

        adapter = new PlantFamilyAdapter(this, R.layout.custom_listview_plant_family, data);
        custom_listview_plant_genus.setAdapter(adapter);


        theFilter.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//              if (searchView.isExpanded() && TextUtils.isEmpty(newText)) {
                callSearch(newText);
//              }
                return true;
            }

            public void callSearch(String query) {
                (PlantListGenus.this).adapter.getFilter().filter(query);
            }
        });

        txt_family_tab.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(PlantListGenus.this, PlantListFamily.class));
        });

        txt_species_tab.setOnClickListener(v -> {
            finish();
            startActivity(new Intent(PlantListGenus.this, PlantListSpecies.class));
        });
    }

    private void createData(){
        data = new ArrayList<PlantFamily>();
        data.add(new PlantFamily(R.drawable.plant_chi_ven_ven,"CHI VÊN VÊN", "Anisoptera Korth", "1"));
        data.add(new PlantFamily(R.drawable.plant_chi_dau,"CHI DẦU", "Dipterocarpus Gaertn", "1"));
        data.add(new PlantFamily(R.drawable.plant_chi_sao,"CHI SAO", "Hopea L.", "1"));
        data.add(new PlantFamily(R.drawable.plant_chi_chai,"CHI CHAI", "Shorea Roxb. ex Gaertn", "1"));
        data.add(new PlantFamily(R.drawable.plant_chi_tau,"CHI TÁU", "Vatica L.", "1"));
    }
}