package com.example.android.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.id;


/**
 * Created by Jeffrey on 03-Aug-17.
 * This concrete class extends an ArrayAdapter is created so we can override the getView function
 * so we can display a complex view rather than a single textView.
 */

public class WordAdapter extends ArrayAdapter<Word> {


    public WordAdapter(Activity context, ArrayList<Word> wordArrayList) {
        super(context, 0, wordArrayList);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        //do not inflate a view from an XML resource if the listItem is null
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView englishWordTextView = (TextView) listItemView.findViewById(R.id.englishNumbers_textView);
        englishWordTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokWordTextView = (TextView) listItemView.findViewById(R.id.miwokNumbers_textView);
        miwokWordTextView.setText(currentWord.getMiwokTranslation());

        ImageView iconImageView = (ImageView) listItemView.findViewById(R.id.myIcon);

        if (currentWord.hasImage()) {
            iconImageView.setImageResource(currentWord.getImageResourceId());
            iconImageView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.tan_background));
            iconImageView.setVisibility(View.VISIBLE);
        } else {
            iconImageView.setVisibility(View.GONE);
        }
        return listItemView;
    }
}
