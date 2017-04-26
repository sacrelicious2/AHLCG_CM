package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;

/**
 * Created by Paul Burg on 4/23/2017.
 */

class ConditionNot extends BaseCondition {
    public static final String type = "ConditionNot";
    private BaseCondition condition;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean evaluate(CampaignState campaignState) {
        return !condition.evaluate(campaignState);
    }
}
