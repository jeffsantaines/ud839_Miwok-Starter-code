package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        //Create an array of Miwok words from one to ten
        ArrayList<String> miwokWords = new ArrayList<>(Arrays.asList(
                "wetetti", "chokokki", "takaakki", "topoppi", "kululli",
                "kelelli", "topiise", "chiwiite"));

        //Create an array of English words from one to ten
        ArrayList<String> englishWords = new ArrayList<>(Arrays.asList(
                "red", "green", "brown", "gray", "black", "white",
                "dusty yellow", "mustard yellow"));

        //ArrayList<Integer> for our resourceDrawable Id
        ArrayList<Integer> resourceDrawableId = new ArrayList<>();
        resourceDrawableId.add(R.drawable.color_red);
        resourceDrawableId.add(R.drawable.color_green);
        resourceDrawableId.add(R.drawable.color_brown);
        resourceDrawableId.add(R.drawable.color_gray);
        resourceDrawableId.add(R.drawable.color_black);
        resourceDrawableId.add(R.drawable.color_white);
        resourceDrawableId.add(R.drawable.color_dusty_yellow);
        resourceDrawableId.add(R.drawable.color_mustard_yellow);



        //instantiate our ArrayList of the class "Word"
        ArrayList<Word> words = new ArrayList<>();
        for (int i = 0; i < miwokWords.size(); i++) {
            words.add(new Word(miwokWords.get(i), englishWords.get(i), resourceDrawableId.get(i)));
        }

        WordAdapter itemsAdapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);


    }
}
