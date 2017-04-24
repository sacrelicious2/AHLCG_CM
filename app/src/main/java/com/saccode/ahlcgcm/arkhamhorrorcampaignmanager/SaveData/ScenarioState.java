package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.CampaignInfo;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.ChaosBagEntry;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.GameData;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class ScenarioState {
    private String campaignId;
    private String scenarioId;
    private String resolutionId;
    private int experienceEarned;
    private ArrayList<InvestigatorScenarioProgress> participatingInvestigators;
    private ArrayList<ArrayList<String>> campaignLogAdditions;
    private ArrayList<ChaosBagEntry> chaosBagAdditions;

    public ScenarioState(String campaignId, String scenarioId) {
        this.campaignId = campaignId;
        this.scenarioId = scenarioId;
        participatingInvestigators = new ArrayList<>();
        campaignLogAdditions = new ArrayList<>();
        CampaignInfo campaignInfo = GameData.getInstance().getCampaignInfo(campaignId);
        for (int iLog = 0; iLog < campaignInfo.campaignLogLists.size(); ++iLog) {
            campaignLogAdditions.add(new ArrayList<String>());
        }
        chaosBagAdditions = new ArrayList<>();
    }

    public String getScenarioId() {
        return scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        this.scenarioId = scenarioId;
    }

    public void addInvestigator(String investigatorId) {
        participatingInvestigators.add(new InvestigatorScenarioProgress(investigatorId));
    }
}
