package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
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

    //handles playback of all the soundfiles
    private MediaPlayer mMediaPlayer;

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    //handles audio focus when playing a sound file
    private AudioManager mAudioManager;


    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */

    private AudioManager.OnAudioFocusChangeListener  mOnAudioFocusChangeListener =  new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            switch (focusChange) {

                case AudioManager.AUDIOFOCUS_GAIN:
                    Log.i("Numbers Activity", "AUDIOFOCUS_GAIN");
                    //play sound
                    mMediaPlayer.start();
                    break;

                case AudioManager.AUDIOFOCUS_LOSS:
                    Log.i("Numbers Activity", "AUDIOFOCUS_LOSS");
                    //releasedMediaPlayer
                    mMediaPlayer.release();
                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    Log.i("Numbers Activity", "AUDIOFOCUS_LOSS_TRANSIENT");
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    //releasedMediaPlayer
                    break;

                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    Log.i("Numbers Activity", "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    //release Media Player
                    mMediaPlayer.pause();
                    mMediaPlayer.seekTo(0);
                    break;

            }
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);


        //Create an array of Miwok words from one to ten
        final ArrayList<String> miwokWords = new ArrayList<>(Arrays.asList(
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

        //create an ArrayList of integer to hold our audio files' ids
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
            words.add(new Word(miwokWords.get(i), englishWords.get(i), resourceDrawableId.get(i), resourceRawId.get(i)));
        }

        //instantiate WordAdapter type (which extends ArrayAdapter we extended it to modify the getView method)
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        //register our list view in our resource folder
        ListView listView = (ListView) findViewById(R.id.list);

        //setting the data behind or listview.
        listView.setAdapter(itemsAdapter);


        //a callback when we click an item on our listView

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * Request audio focus so in order to play the audio file. The app needs to play
                 * a short audio file, so we will request audio focus with a short amount of time
                 * with AUDIOFOCUS_GAIN_TRANSIENT
                 * */
                Word word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    //Create and setup the {@link MediaPlayer} for the audio resource associated
                    // with the current word.
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId() );

                    //Start the audio file
                    mMediaPlayer.start();

                    //Setup a listener on the media player, so that we can stop and release the
                    //media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                }

            }
        });


    }// closing of onCreate method

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

            //Regardless of whether or not we were granted audio focus, abandon it. This also
            //unregisterrs the AudioFocusChangeListener so we do not get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}// closing of NumbersActivity
