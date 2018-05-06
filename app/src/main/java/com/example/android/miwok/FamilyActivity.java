package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {


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


        final ArrayList<Word> familyInWords = new ArrayList<Word>();

        familyInWords.add(new Word("father" ,"әpә", R.drawable.family_father, R.raw.family_father));
        familyInWords.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        familyInWords.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        familyInWords.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        familyInWords.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        familyInWords.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyInWords.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyInWords.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyInWords.add(new Word("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyInWords.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter =
                new WordAdapter(this,  familyInWords, R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = familyInWords.get(position);

                cleanMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(cleanSound);
                Toast.makeText(FamilyActivity.this, word.getWordInMiwok(), Toast.LENGTH_SHORT).show();
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
