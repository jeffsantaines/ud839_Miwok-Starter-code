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


    MediaPlayer mMediaPlayer;

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
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

                // Release the media player if it currently exists because we are about to
                // play a different sound file.
                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, resourceRawId.get(position));
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }

        });

    }//end of onCreate Method

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }

}// end of Activity
