package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Actions.BaseAction;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions.BaseCondition;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class ConditionalAction {
    public ArrayList<BaseCondition> condition;
    public BaseAction action;
}
