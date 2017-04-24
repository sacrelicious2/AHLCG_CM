package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class ConditionHasCompletedScenario extends BaseCondition {
    public static final String type = "ConditionHasCompletedScenario";
    public String scenarioId;

    public String getScenarioId() {
        return scenarioId;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean evaluate(CampaignState campaignState) {
        return campaignState.isScenarioCompleted(scenarioId);
    }
}
