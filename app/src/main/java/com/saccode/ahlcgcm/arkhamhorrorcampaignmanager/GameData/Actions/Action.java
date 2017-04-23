package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Actions;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public interface Action {
    public abstract boolean Execute(CampaignState campaignState);
}
