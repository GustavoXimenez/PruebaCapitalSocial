package com.androidavanzado.capitalsocial.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidavanzado.capitalsocial.R;
import com.androidavanzado.capitalsocial.common.Functions;

public class DetallePromocionActivity extends AppCompatActivity {

    private Bundle bundle;

    //Var
    int imagePromotion;
    String titlePromotion;
    String descriptionPromotion;
    private Functions functions = new Functions();

    //View
    private ImageView imgDescription;
    private TextView txtTitle;
    private TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_promocion);

        //Add back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        functions.centerTitle(this);

        bundle = getIntent().getExtras();
        getExtras();
        initViews();
    }

    private void getExtras() {

        if (bundle != null) {
            imagePromotion = bundle.getInt("IdImage");
            titlePromotion = bundle.getString("Title");
            descriptionPromotion = bundle.getString("Description");
        }
    }

    private void initViews(){
        imgDescription = (ImageView) findViewById(R.id.imageViewPromotion);
        txtTitle = (TextView) findViewById(R.id.textViewTitle);
        txtDescription = (TextView) findViewById(R.id.textViewDescription);

        imgDescription.setImageResource(imagePromotion);
        txtTitle.setText(titlePromotion);
        txtDescription.setText(descriptionPromotion);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            //return the activity
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
