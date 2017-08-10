package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.annotation.IntegerRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an array of Miwok words from one to ten
        ArrayList<String> miwokWords = new ArrayList<>(Arrays.asList(
                "lutti", "otikko", "tolookosu", "oyyiisa", "massokka",
                "temmokka", "tenekaku", "kawinta", "wo'e", "na'aacha"
        ));

        //Create an array of English words from one to ten
        ArrayList<String> englishWords = new ArrayList<>(Arrays.asList(
                "one", "two", "three", "four", "five", "six",
                "seven", "eight", "nine", "ten"));

        ArrayList<Integer> resourceDrawableId = new ArrayList<>();
        resourceDrawableId.add(R.drawable.number_one);
        resourceDrawableId.add(R.drawable.number_two);
        resourceDrawableId.add(R.drawable.number_three);
        resourceDrawableId.add(R.drawable.number_four);
        resourceDrawableId.add(R.drawable.number_five);
        resourceDrawableId.add(R.drawable.number_six);
        resourceDrawableId.add(R.drawable.number_seven);
        resourceDrawableId.add(R.drawable.number_eight);
        resourceDrawableId.add(R.drawable.number_nine);
        resourceDrawableId.add(R.drawable.number_ten);

        final ArrayList<Integer> resourceRawId = new ArrayList<>();
        resourceRawId.add(R.raw.number_one);
        resourceRawId.add(R.raw.number_two);
        resourceRawId.add(R.raw.number_three);
        resourceRawId.add(R.raw.number_four);
        resourceRawId.add(R.raw.number_five);
        resourceRawId.add(R.raw.number_six);
        resourceRawId.add(R.raw.number_seven);
        resourceRawId.add(R.raw.number_eight);
        resourceRawId.add(R.raw.number_nine);
        resourceRawId.add(R.raw.number_ten);



        //instantiate our ArrayList of the class "Word"
        final ArrayList<Word> words = new ArrayList<>();
        for (int i = 0; i < miwokWords.size(); i++) {
            words.add(new Word(miwokWords.get(i), englishWords.get(i), resourceDrawableId.get(i)));
        }

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaPlayer mMediaPlayer = MediaPlayer.create(getApplicationContext(),resourceRawId.get(position));
                mMediaPlayer.start();
            }
        });
    }
}
