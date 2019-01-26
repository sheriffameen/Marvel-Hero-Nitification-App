package com.example.notificationlistapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SuperHeroViewHolder extends RecyclerView.ViewHolder {
    private TextView superHeroTitleText;
    private ImageView superHerImage;

    public SuperHeroViewHolder(@NonNull final View itemView) {
        super(itemView);

        superHeroTitleText = itemView.findViewById(R.id.superHeroName_textview);
        superHerImage = itemView.findViewById(R.id.superHeroImageView);


    }

    public void onBind(final SuperHero superHero){
        superHeroTitleText.setText(superHero.getName());
        Glide.with(itemView.getContext()).load(superHero.getImage()).into(superHerImage);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String superHeroName = superHero.getName();
                int superHeroImage = superHero.getImage();

                Intent intent;
                intent = new Intent(itemView.getContext(),DisplayActivity.class);
                intent.putExtra("superHeroName", superHeroName);
                intent.putExtra("superHeroImage", superHeroImage);

                itemView.getContext().startActivity(intent);


            }
        });


    }
}
