package com.example.android.miwok;

/**
 * Created by Jeffrey on 03-Aug-17.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;


    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mAudioResourceId;





    //default constructor
    public Word() {
        this.mMiwokTranslation = null;
        this.mDefaultTranslation = null;
        this.mImageResourceId = 0;
    }

    //constructor with 3 parameters
    public Word(String miwokTranslation, String defaultTranslation, int audioResourceId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mAudioResourceId = audioResourceId;
    }


    //constructor with 4 parameters
    public Word(String miwokTranslation, String defaultTranslation, int imageResourceId, int audioResourceId) {
        this.mMiwokTranslation = miwokTranslation;
        this.mDefaultTranslation = defaultTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceId = audioResourceId;
    }

    //getters and setters

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setDefaultTranslation(String defaultTranslation) {
        mDefaultTranslation = defaultTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        mImageResourceId = imageResourceId;
    }


    //returns true if the mImageResourceId is not equal to -1
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


    //returns the audio resource id
    public int getAudioResourceId() {
        return mAudioResourceId;
    }

    //set the audioResourceId
    public void setAudioResourceId(int audioResourceId) {
        mAudioResourceId = audioResourceId;
    }


}
