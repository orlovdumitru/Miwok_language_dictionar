package com.example.android.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.media.AudioManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dima on 2/4/17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();
    private int backGround = R.color.tan_background;


    public WordAdapter(Activity context, ArrayList<Word> wordAdapter, int backGround){
        super(context, 0, wordAdapter);
        this.backGround = backGround;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWordAdapter = getItem(position);

        TextView textViewMiok = (TextView) listItemView.findViewById(R.id.miok_text_view);
        textViewMiok.setText(currentWordAdapter.getWordInMiwok());

        TextView textViewDefault = (TextView) listItemView.findViewById(R.id.default_text_view);
        textViewDefault.setText(currentWordAdapter.getWordInEnglish());


        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageText);

        if (currentWordAdapter.hasImage()) {
                imageView.setImageResource(currentWordAdapter.getImageName());
        }else{
            imageView.setVisibility(View.GONE);
        }

         View textContainer = listItemView.findViewById(R.id.text_container);
                 // Find the color that the resource ID maps to
         int color = ContextCompat.getColor(getContext(), this.backGround);
                // Set the background color of the text container View
         textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
