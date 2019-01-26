package com.example.notificationlistapp;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView superHero_recyclerView;
    private ArrayList<SuperHero> superHeroesData;
    private SuperHeroAdapter superHeroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superHero_recyclerView = findViewById(R.id.superHero_recyclerview);
        superHero_recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
        superHeroesData = new ArrayList<>();
        superHeroAdapter = new SuperHeroAdapter(superHeroesData,this);
        superHero_recyclerView.setAdapter(superHeroAdapter);

        initializeData();


    }

    private void initializeData(){
        String[] superHeroList = getResources().getStringArray(R.array.superhero_names);
        TypedArray superHeroImageResource = getResources().obtainTypedArray(R.array.superhero_images);

        superHeroesData.clear();

        for (int i = 0; i <superHeroList.length ; i++) {
            SuperHero superHero = new SuperHero(superHeroList[i],superHeroImageResource.getResourceId(i,0));
            superHeroesData.add(superHero);
        }

        superHeroImageResource.recycle();
        superHeroAdapter.notifyDataSetChanged();

    }
}
