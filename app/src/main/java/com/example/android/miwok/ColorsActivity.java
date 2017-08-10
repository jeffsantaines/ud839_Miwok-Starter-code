package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

        final ArrayList<Integer> resourceRawId = new ArrayList<>();
        resourceRawId.add(R.raw.color_red);
        resourceRawId.add(R.raw.color_green);
        resourceRawId.add(R.raw.color_brown);
        resourceRawId.add(R.raw.color_gray);
        resourceRawId.add(R.raw.color_black);
        resourceRawId.add(R.raw.color_white);
        resourceRawId.add(R.raw.color_dusty_yellow);
        resourceRawId.add(R.raw.color_mustard_yellow);



        //instantiate our ArrayList of the class "Word"
        ArrayList<Word> words = new ArrayList<>();
        for (int i = 0; i < miwokWords.size(); i++) {
            words.add(new Word(miwokWords.get(i), englishWords.get(i), resourceDrawableId.get(i)));
        }

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MediaPlayer mMediaPlayer = MediaPlayer.create(ColorsActivity.this, resourceRawId.get(position));
                mMediaPlayer.start();

            }
        });



    }
}
