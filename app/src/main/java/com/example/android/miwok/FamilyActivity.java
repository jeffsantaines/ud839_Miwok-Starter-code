package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create an array of Miwok words from one to ten
        ArrayList<String> miwokWords = new ArrayList<>(Arrays.asList(
                "әpә", "әṭa", "angsi", "tune", "taachi",
                "chalitti", "tete", "kolliti", "ama", "paapa"));

        //Create an array of English words from one to ten
        ArrayList<String> englishWords = new ArrayList<>(Arrays.asList(
                "father", "mother", "son", "daughter", "older brother", "younger brother",
                "older sister", "younger sister", "grandmother", "grandfather"));

        //ArrayList<Integer> for the family images resource Id
        ArrayList<Integer> resourceDrawableId = new ArrayList<>();
        resourceDrawableId.add(R.drawable.family_father);
        resourceDrawableId.add(R.drawable.family_mother);
        resourceDrawableId.add(R.drawable.family_son);
        resourceDrawableId.add(R.drawable.family_daughter);
        resourceDrawableId.add(R.drawable.family_older_brother);
        resourceDrawableId.add(R.drawable.family_younger_brother);
        resourceDrawableId.add(R.drawable.family_older_sister);
        resourceDrawableId.add(R.drawable.family_younger_sister);
        resourceDrawableId.add(R.drawable.family_grandmother);
        resourceDrawableId.add(R.drawable.family_grandfather);

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
