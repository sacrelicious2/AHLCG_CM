package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Actions.BaseAction;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions.BaseCondition;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.R;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class GameData {
    private static GameData instance = null;
    private ArrayList<InvestigatorInfo> investigators;
    private ArrayList<CampaignInfo> campaigns;
    private ArrayList<ScenarioInfo> standaloneScenarios;
    private ArrayList<String> globalCampaignLogOptions;

    private GameData() {
        investigators = new ArrayList<>();
        campaigns = new ArrayList<>();
        standaloneScenarios = new ArrayList<>();
        globalCampaignLogOptions = new ArrayList<>();
    }

    static public void createInstance(Resources resources) {
        InputStreamReader reader = new InputStreamReader(resources.openRawResource(R.raw.game_data));
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(BaseCondition.getFactory())
                .registerTypeAdapterFactory(BaseAction.getFactory())
                .create();
        instance = gson.fromJson(reader, GameData.class);
    }

    static public GameData getInstance() {
        return instance;
    }

    public ArrayList<InvestigatorInfo> getInvestigators() {
        return investigators;
    }

    public void setInvestigators(ArrayList<InvestigatorInfo> investigators) {
        this.investigators = investigators;
    }

    public ArrayList<CampaignInfo> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(ArrayList<CampaignInfo> campaigns) {
        this.campaigns = campaigns;
    }

    public ArrayList<ScenarioInfo> getStandaloneScenarios() {
        return standaloneScenarios;
    }

    public void setStandaloneScenarios(ArrayList<ScenarioInfo> standaloneScenarios) {
        this.standaloneScenarios = standaloneScenarios;
    }

    public ArrayList<String> getGlobalCampaignLogOptions() {
        return globalCampaignLogOptions;
    }

    public void setGlobalCampaignLogOptions(ArrayList<String> globalCampaignLogOptions) {
        this.globalCampaignLogOptions = globalCampaignLogOptions;
    }

    public List<CharSequence> getCampaignNames() {
        List<CharSequence> names = new ArrayList<CharSequence>();
        for (CampaignInfo info : campaigns) {
            names.add(info.name);
        }
        return names;
    }

    public CampaignInfo getCampaignInfo(CharSequence campaignId) {
        for (CampaignInfo info : campaigns) {
            if (Objects.equals(info.id, campaignId)) {
                return info;
            }
        }
        return null;
    }

    public CampaignInfo getCampaignInfo(int index) {
        if (index < campaigns.size()) {
            return campaigns.get(index);
        }
        return null;
    }

    public CharSequence getCampaignName(int iCampaign) {
        if (iCampaign < campaigns.size()) {
            return campaigns.get(iCampaign).name;
        }
        return "**INVALID CAMPAIGN**";
    }

    public List<CharSequence> getInvestigatorNames() {
        List<CharSequence> names = new ArrayList<CharSequence>();
        for (InvestigatorInfo info : investigators) {
            names.add(info.name);
        }
        return names;
    }

    public CharSequence getInvestigatorName(int index) {
        if (index < investigators.size()) {
            return investigators.get(index).name;
        }
        return "**INVALID INVESTIGATOR**";
    }
}
