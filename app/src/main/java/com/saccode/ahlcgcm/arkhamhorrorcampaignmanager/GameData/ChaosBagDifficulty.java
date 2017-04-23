package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class ChaosBagDifficulty {
    private String difficulty;
    private ArrayList<ChaosBagEntry> contents;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<ChaosBagEntry> getContents() {
        return contents;
    }

    public void setContents(ArrayList<ChaosBagEntry> contents) {
        this.contents = contents;
    }
}
