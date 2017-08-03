package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        //Create an array of Miwok words from one to ten
        ArrayList<String> miwokWords =new ArrayList<>(Arrays.asList(
                "lutti","otikko","tolookosu","oyyiisa", "massokka",
                "temmokka", "tenekaku", "kawinta", "wo'e", "na'aacha"
        ));

        //Create an array of English words from one to ten
        ArrayList<String> englishWords = new ArrayList<>(Arrays.asList(
                "one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten"));
        ArrayList<Word> words = new ArrayList<>();
        for (int i =0; i < miwokWords.size();i++) {
            words.add(new Word(miwokWords.get(i), englishWords.get(i)));
            Log.i("NumbersActivity: ",englishWords.get(i));
        }


        WordAdapter itemsAdapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);


    }
}
