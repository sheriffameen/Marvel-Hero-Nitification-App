package com.example.notificationlistapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SuperHeroAdapter extends RecyclerView.Adapter<SuperHeroViewHolder> {
    private ArrayList<SuperHero> mSuperHeroList;
    private Context mContext;


    public SuperHeroAdapter(ArrayList<SuperHero> mSuperHeroList, Context mContext) {
        this.mSuperHeroList = mSuperHeroList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public SuperHeroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.superhero_itemview,viewGroup,false);
        return new SuperHeroViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperHeroViewHolder superHeroViewHolder, int i) {
        SuperHero superHero = mSuperHeroList.get(i);
        superHeroViewHolder.onBind(superHero);

    }

    @Override
    public int getItemCount() {
        return mSuperHeroList.size();
    }
}


