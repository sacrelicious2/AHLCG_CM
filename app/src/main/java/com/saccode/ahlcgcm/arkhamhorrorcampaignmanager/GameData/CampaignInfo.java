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
    public ArrayList<String> campaignLogLists;
    public ArrayList<ScenarioInfo> scenarios;
    public ArrayList<ChaosBagDifficulty> initChaosBags;

    public List<String> getScenarioNames() {
        List<String> names = new ArrayList<String>();
        for (ScenarioInfo info : scenarios) {
            names.add(info.name);
        }
        return names;
    }

    public CharSequence getCampaignLogName(int index) {
        if (index < campaignLogLists.size()) {
            return campaignLogLists.get(index);
        }
        return "**INVALID CAMPAIGN LOG LIST**";
    }

    public ArrayList<CharSequence> getDifficultNames() {
        ArrayList<CharSequence> names = new ArrayList<>();
        for (ChaosBagDifficulty chaosBagDifficulty : initChaosBags) {
            names.add(chaosBagDifficulty.getDifficulty());
        }
        return names;
    }

    public ChaosBagDifficulty getChaosBagInfo(int difficultIndex) {
        if (difficultIndex < initChaosBags.size()) {
            return initChaosBags.get(difficultIndex);
        }
        return null;
    }
}
