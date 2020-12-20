package com.example.bookreader;

import android.util.Log;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import fragments.PageFragment;

public class StackAdapter extends FragmentPagerAdapter {
    private int charMax;
    private String content;
    private List<String> parts;

    public StackAdapter(FragmentManager fragmentManager, int charMax, String content) {
        super(fragmentManager);
        this.charMax = charMax;
        this.content = content;
        this.parts = getParts(content, charMax);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return parts.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position, parts.get(position));
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }


    private List<String> getParts(String string, int partitionSize) {
        List<String> parts = new ArrayList<String>();
        int len = string.length();
        String verificator;
        int addPositions = 0;
        for (int i = 0; i < len; i += partitionSize) {
            verificator = string.substring(i, Math.min(len, i + partitionSize));
            addPositions = 0;

            while (!verificator.endsWith(" ")) {

                verificator = verificator.substring(0, verificator.length() - 1);
                addPositions++;
            }

            parts.add(verificator);
            i = i - addPositions;

        }
        return parts;
    }

}


