package com.example.android.miwok;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    public MediaPlayer.OnCompletionListener cleanSound = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mMediaPlayer) {
            cleanMediaPlayer();
        }};

    MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }catch(NullPointerException e){
        }

        final ArrayList<Word> colorsInWords = new ArrayList<Word>();

        colorsInWords.add(new Word("red" ,"weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        colorsInWords.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        colorsInWords.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        colorsInWords.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        colorsInWords.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        colorsInWords.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        colorsInWords.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsInWords.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));


        WordAdapter adapter =
                new WordAdapter(this,  colorsInWords, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Word word = colorsInWords.get(position);
                
                cleanMediaPlayer();
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(cleanSound);
                Toast.makeText(ColorsActivity.this, word.getWordInMiwok(), Toast.LENGTH_SHORT).show();
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
        }
    }
}
