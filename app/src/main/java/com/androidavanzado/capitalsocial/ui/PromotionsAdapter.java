package com.androidavanzado.capitalsocial.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androidavanzado.capitalsocial.R;
import com.androidavanzado.capitalsocial.model.Promotion;

import java.util.List;

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.ViewHolder> {

    private Activity activity;
    private List<Promotion> lstPromotion;


    public PromotionsAdapter(List<Promotion> lstPromotion, Activity activity) {
        this.lstPromotion = lstPromotion;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_item_promotion, viewGroup, false);
        return new ViewHolder(view, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Promotion promotion = lstPromotion.get(position);
        ImageView imgPromotion = holder.ImagePromotion;
        TextView txtTitlePromotion = holder.TitlePromotion;

        imgPromotion.setImageResource(promotion.ImagePromotion);
        txtTitlePromotion.setText(promotion.TextTitle);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getItemCount() {
        return lstPromotion.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ImagePromotion;
        public TextView TitlePromotion;

        public ViewHolder(@NonNull View itemView, final Activity activity){
            super(itemView);
            this.ImagePromotion = (ImageView) itemView.findViewById(R.id.imageViewPromotion);
            this.TitlePromotion = (TextView) itemView.findViewById(R.id.textViewTitlePromotion);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    //Log.d("ClickItem", "position: " + position);
                    Promotion promotion = lstPromotion.get(position);
                    Intent intent = new Intent(activity, DetallePromocionActivity.class);
                    intent.putExtra("IdImage", promotion.ImagePromotion);
                    intent.putExtra("Title", promotion.TextTitle);
                    intent.putExtra("Description", promotion.TextDescription);
                    activity.startActivity(intent);
                }
            });
        }
    }
}
