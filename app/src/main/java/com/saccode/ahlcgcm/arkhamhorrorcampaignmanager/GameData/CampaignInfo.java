package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class CampaignInfo {
    public String id;
    public String name;
    public String introText;
    public ArrayList<ScenarioInfo> scenarios;

    public List<String> getScenarioNames()
    {
        List<String> names = new ArrayList<String>();
        for (ScenarioInfo info : scenarios)
        {
            names.add(info.name);
        }
        return names;
    }
}
