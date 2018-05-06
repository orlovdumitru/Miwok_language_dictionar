package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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

        final ArrayList<Word> phrasesInWords = new ArrayList<Word>();

        phrasesInWords.add(new Word("Where are you going?" ,"minto wuksus", R.raw.phrase_where_are_you_going));
        phrasesInWords.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        phrasesInWords.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        phrasesInWords.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        phrasesInWords.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        phrasesInWords.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        phrasesInWords.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        phrasesInWords.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        phrasesInWords.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        phrasesInWords.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter adapter =
                new WordAdapter(this,  phrasesInWords, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = phrasesInWords.get(position);

                cleanMediaPlayer();
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(cleanSound);
                Toast.makeText(PhrasesActivity.this, word.getWordInMiwok(), Toast.LENGTH_SHORT).show();
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
