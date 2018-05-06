package com.example.android.miwok;

/**
 * Created by dima on 2/4/17.
 */

public class Word {

    private String wordInEnglish;
    private String wordInMiwok;
    private int imageName = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED=-1;
    private int soundPronuntiation;
    
    /**
     *
     * @param wordInEnglish this parameter takes our existing word in english
     * @param wordInMiwok  this parameter takes ther translation in miok language
     */
    public Word(String wordInEnglish, String wordInMiwok, int soundPronuntiation){
        this.wordInEnglish = wordInEnglish;
        this.wordInMiwok = wordInMiwok;
        this.soundPronuntiation = soundPronuntiation;
    }

    /**
     *
     * @param wordInEnglish this parameter takes our existing word in english
     * @param wordInMiwok  this parameter takes ther translation in miok language
     * @param imageName this is for image resource id
     */
    public Word(String wordInEnglish, String wordInMiwok, int imageName, int soundPronuntiation){
        this.wordInEnglish = wordInEnglish;
        this.wordInMiwok = wordInMiwok;
        this.imageName = imageName;         //asta ii pintru atribuire imaginii
        this.soundPronuntiation = soundPronuntiation;
    }



    public String getWordInEnglish(){
        return this.wordInEnglish;
    }

    public String getWordInMiwok(){
        return this.wordInMiwok;
    }

    public int getImageName(){
        return this.imageName;
    }

    public boolean hasImage(){
        if (imageName!=-1)
            return true;
        else
            return false;
    }
    public int getAudioResourceId(){
        return this.soundPronuntiation;
    }
}
