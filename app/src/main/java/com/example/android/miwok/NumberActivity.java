package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumberActivity extends AppCompatActivity {



public MediaPlayer.OnCompletionListener cleanSound = new MediaPlayer.OnCompletionListener() {
    @Override
    public void onCompletion(MediaPlayer mMediaPlayer) {
        cleanMediaPlayer();
    }};

    private AudioManager audioManager;
    private MediaPlayer mMediaPlayer;

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        cleanMediaPlayer();
                        audioManager.abandonAudioFocus(afChangeListener);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();

                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(NullPointerException e){
        }

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);



        final ArrayList<Word> numbersInWords = new ArrayList<Word>();

        numbersInWords.add(new Word("one" ,"lutti", R.drawable.number_one, R.raw.number_one));
        numbersInWords.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbersInWords.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbersInWords.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbersInWords.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbersInWords.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbersInWords.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbersInWords.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbersInWords.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        numbersInWords.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter adapter =
                new WordAdapter(this,  numbersInWords, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = numbersInWords.get(position);

                cleanMediaPlayer();

                int result = audioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                   // audioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);


                    mMediaPlayer = MediaPlayer.create(NumberActivity.this, word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(cleanSound);

                    Toast.makeText(NumberActivity.this, word.getWordInMiwok(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onStop(){
        super.onStop();
        cleanMediaPlayer();
    }

    public void cleanMediaPlayer(){

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }
}
