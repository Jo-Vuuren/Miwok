package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    /** Resource ID for the background color for this list of words. */
    private int mColorResourceId;

    /** Create an new {@link WordAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param words is the list of {@link Word}s to be displayed
     */
    public WordAdapter(Activity context, ArrayList<Word> words, int ColorResourceId) {
        super(context, 0, words);
        mColorResourceId = ColorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        //Find the ImageView in the library_item.xml layout with the ID album_art_view.
        ImageView imageView = listItemView.findViewById(R.id.image_view);
        //Get the image for the song from the current Word object and
        //set this image on the ImageView.
        imageView.setImageResource(currentWord.getImageResourceId());

        if (currentWord.hasImage()){
            //Set the ImageView to the image resource specified in the current word.
            imageView.setImageResource(currentWord.getImageResourceId());

            //Make sure the view is visible.
            imageView.setVisibility(View.VISIBLE);

        } else {
            //Otherwise hide the ImageView (set visibility to GONE).
            imageView.setVisibility(View.GONE);
        }


        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        //Set the theme color for the list item.
        View textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that the resource ID maps to.
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //Set the background color of the text container View.
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}