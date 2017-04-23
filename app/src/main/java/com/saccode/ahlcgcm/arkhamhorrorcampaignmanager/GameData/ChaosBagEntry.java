package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class ChaosBagEntry {
    private String token;
    private int count;

    public ChaosBagEntry(ChaosBagEntry entry) {
        token = entry.token;
        count = entry.count;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
