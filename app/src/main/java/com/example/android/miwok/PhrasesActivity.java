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

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        //Create an array of Miwok words from one to ten
        ArrayList<String> miwokWords = new ArrayList<>(Arrays.asList(
                "minto wuksus", "tinnә oyaase'nә", "oyaaset...", "michәksәs?", "kuchi achit",
                "әәnәs'aa?", "hәә’ әәnәm", "әәnәm", "yoowutis", "әnni'nem"
        ));

        //Create an array of English words from one to ten
        ArrayList<String> englishWords = new ArrayList<>(Arrays.asList(
                "Where are you going?", "What is your name?", "My name is...", "How are you feeling?", "I’m feeling good.", "Are you coming?",
                "Yes, I’m coming.", "I'm coming", "Let's go.", "Come  here"));

        final ArrayList<Integer> resourceRawId = new ArrayList<>();
        resourceRawId.add(R.raw.phrase_where_are_you_going);
        resourceRawId.add(R.raw.phrase_what_is_your_name);
        resourceRawId.add(R.raw.phrase_my_name_is);
        resourceRawId.add(R.raw.phrase_how_are_you_feeling);
        resourceRawId.add(R.raw.phrase_im_feeling_good);
        resourceRawId.add(R.raw.phrase_are_you_coming);
        resourceRawId.add(R.raw.phrase_yes_im_coming);
        resourceRawId.add(R.raw.phrase_im_coming);
        resourceRawId.add(R.raw.phrase_lets_go);
        resourceRawId.add(R.raw.phrase_come_here);


        //instantiate our ArrayList of the class "Word"
        ArrayList<Word> words = new ArrayList<>();
        for (int i = 0; i < miwokWords.size(); i++) {
            words.add(new Word(miwokWords.get(i), englishWords.get(i), resourceRawId.get(i)));
        }

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Release the media player if it currently exists because we are about to
                // play a different sound file.
                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, resourceRawId.get(i));
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });


    }

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
}
