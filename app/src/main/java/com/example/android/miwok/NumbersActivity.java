package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        //Create an array of English words from one to ten
        ArrayList<String> englishWords = new ArrayList<String>(Arrays.asList(
                "one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten"));

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, englishWords);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);



        /*for ( int index = 0; index < englishWords.size(); index++) {
            TextView newTextView = new TextView(this);
            newTextView.setText(englishWords.get(index));
           *//* newTextView.setHeight(50);
            newTextView.setWidth(200);
            newTextView.setBackgroundColor(123);*//*
            rootView.addView(newTextView);
            textViewArrayList.add(newTextView);

        }*/
    }
}
