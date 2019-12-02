package com.androidavanzado.capitalsocial.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidavanzado.capitalsocial.R;
import com.androidavanzado.capitalsocial.common.Functions;
import com.androidavanzado.capitalsocial.common.SpacesItemDecoration;
import com.androidavanzado.capitalsocial.model.Promotion;

import java.util.ArrayList;
import java.util.List;

public class PromotionsActivity extends AppCompatActivity {

    private RecyclerView recyclerPromotions;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //Var list
    private List<Promotion> lstPromotions = new ArrayList<>();
    private Functions functions = new Functions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);

        functions.centerTitle(this);
        loadPromotions();
        initViews();
    }

    private void loadPromotions() {

        lstPromotions.add(new Promotion(R.drawable.imagen_pizza, "Papa Jonh's", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.placeholder, "Idea Interior", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.promo_burguer_king, "Burger Kings", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.promo_benavides, "Farmacia Benavides", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.promo_tizoncito, "El tizoncito", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.promo_chilis, "Chills's", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.promo_zona_fitness, "Zona Fitness", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.promo_cinepolis, "Cinepolis", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.promo_idea, "Idea", getString(R.string.lorem_ipsum)));
        lstPromotions.add(new Promotion(R.drawable.promo_wingstop, "Wingstop", getString(R.string.lorem_ipsum)));

    }

    private void initViews(){
        if(adapter == null){
            adapter = new PromotionsAdapter(lstPromotions, this);
        }

        recyclerPromotions = (RecyclerView) findViewById(R.id.recyclerPromotions);
        //layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this, 2);

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
        recyclerPromotions.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        recyclerPromotions.setHasFixedSize(true);
        recyclerPromotions.setItemAnimator(new DefaultItemAnimator());
        recyclerPromotions.setAdapter(adapter);
        recyclerPromotions.setLayoutManager(layoutManager);
        //recyclerPromotions.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //inflate menu
        getMenuInflater().inflate(R.menu.menu_maps, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.map){
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
