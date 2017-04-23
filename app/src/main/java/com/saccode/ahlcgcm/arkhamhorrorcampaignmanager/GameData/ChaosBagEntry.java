package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class ChaosBagEntry {
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


    public void setCount(int count) {
        this.count = count;
    }

    private String token;

    @Override
    public ChaosBagEntry clone() {
        ChaosBagEntry result = new ChaosBagEntry();
        result.setCount(getCount());
        result.setToken(getToken());
        return result;
    }

    private int count;
}
