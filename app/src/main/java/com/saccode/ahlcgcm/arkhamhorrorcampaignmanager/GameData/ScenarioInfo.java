package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions.BaseCondition;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class ScenarioInfo {
    public String id;
    public String name;
    public Integer xpCost;
    public ArrayList<BaseCondition> prerequisites;
    public ArrayList<ConditionalText> introText;
    public ArrayList<ScenarioResolution> resolutions;
}
