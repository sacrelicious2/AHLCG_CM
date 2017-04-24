package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class ConditionAnd extends BaseCondition {
    public static final String type = "ConditionAnd";
    private ArrayList<BaseCondition> conditions;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean evaluate(CampaignState campaignState) {
        for (BaseCondition condition : conditions) {
            if (!condition.evaluate(campaignState)) {
                return false;
            }
        }
        return true;
    }
}