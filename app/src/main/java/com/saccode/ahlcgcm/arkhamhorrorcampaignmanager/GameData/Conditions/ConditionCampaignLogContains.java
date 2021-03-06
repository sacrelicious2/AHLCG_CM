package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;

/**
 * Created by Paul Burg on 4/23/2017.
 */

class ConditionCampaignLogContains extends BaseCondition {

    public static final String type = "ConditionCampaignLogContains";

    private String log;
    private String entry;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean evaluate(CampaignState campaignState) {
        return campaignState.doesCampaignLogContain(log, entry);
    }
}
