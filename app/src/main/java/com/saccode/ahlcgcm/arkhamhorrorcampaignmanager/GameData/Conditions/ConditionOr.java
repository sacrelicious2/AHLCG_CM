package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class ConditionOr extends BaseCondition {

    public static final String type = "ConditionOr";
    private ArrayList<BaseCondition> conditions;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean evaluate(CampaignState campaignState) {
        for (BaseCondition condition : conditions) {
            if (condition.evaluate(campaignState)) {
                return true;
            }
        }
        return false;
    }
}
