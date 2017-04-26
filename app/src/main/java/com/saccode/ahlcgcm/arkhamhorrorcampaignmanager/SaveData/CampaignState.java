package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.CampaignInfo;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.ChaosBagDifficulty;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.ChaosBagEntry;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions.BaseCondition;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.GameData;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.ScenarioInfo;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class CampaignState {

    private transient InvestigatorListListener investigatorListListener;
    private String name;
    private String campaignId;
    private ArrayList<InvestigatorState> investigatorStates;
    private ArrayList<ArrayList<String>> campaignLogLists;
    private ArrayList<ChaosBagEntry> chaosBag;
    private ArrayList<ScenarioState> completedScenarios;
    private String difficulty;
    private ScenarioState activeScenario;

    public CampaignState(CharSequence campaignName, CharSequence campaignId, int difficultyIndex) {
        this.name = campaignName.toString();
        this.campaignId = campaignId.toString();
        investigatorStates = new ArrayList<>();
        campaignLogLists = new ArrayList<>();
        CampaignInfo campaignInfo = getCampaignInfo();
        for (int iCampaignLogList = 0; iCampaignLogList < campaignInfo.campaignLogLists.size(); ++iCampaignLogList) {
            campaignLogLists.add(new ArrayList<String>());
        }
        ChaosBagDifficulty initChaosBag = campaignInfo.getChaosBagInfo(difficultyIndex);
        difficulty = initChaosBag.getDifficulty();
        chaosBag = new ArrayList<>();
        for (ChaosBagEntry entry : initChaosBag.getContents()) {
            chaosBag.add(new ChaosBagEntry(entry));
        }
        completedScenarios = new ArrayList<>();
    }

    public void addInvestigator(CharSequence investigatorName, CharSequence playerName) {
        investigatorStates.add(new InvestigatorState(investigatorName, playerName));
        investigatorListListener.onUpdateInvestigatorList();
    }

    public ArrayList<InvestigatorState> getInvestigatorStates() {
        return investigatorStates;
    }

    public InvestigatorState getInvestigatorState(int index) {
        if (index < investigatorStates.size()) {
            return investigatorStates.get(index);
        }
        return null;
    }

    public CharSequence getName() {
        return name;
    }

    public CampaignInfo getCampaignInfo() {
        return GameData.getInstance().getCampaignInfo(campaignId);
    }

    public CharSequence getCampaignName() {
        return getCampaignInfo().name;
    }

    public void setInvestigatorListListener(InvestigatorListListener investigatorListListener) {
        this.investigatorListListener = investigatorListListener;
    }

    public int getCampaignLogListCount() {
        return getCampaignInfo().campaignLogLists.size();
    }

    public int getCampaignLogListSize(int index) {
        if (index < campaignLogLists.size()) {
            return campaignLogLists.get(index).size();
        }
        return 0;
    }

    public CharSequence getCampaignLogEvent(int logIndex, int eventIndex) {
        if (logIndex < campaignLogLists.size()) {
            ArrayList<String> log = campaignLogLists.get(logIndex);
            if (eventIndex < log.size()) {
                return log.get(eventIndex);
            }
        }
        return null;
    }

    public CharSequence getCampaignLogName(int logIndex) {
        CampaignInfo campaignInfo = getCampaignInfo();
        if (logIndex < campaignInfo.campaignLogLists.size()) {
            return campaignInfo.campaignLogLists.get(logIndex);
        }
        return "**INVALID CAMPAIGN LOG NAME**";
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getChaosBagListCount() {
        return chaosBag.size();
    }

    public ChaosBagEntry getChaosBagEntry(int index) {
        if (index < chaosBag.size()) {
            return chaosBag.get(index);
        }
        return null;
    }

    public int getInvestigatorCount() {
        return investigatorStates.size();
    }

    public boolean doesCampaignLogContain(String log, String entry) {
        CampaignInfo campaignInfo = getCampaignInfo();
        int logIndex = campaignInfo.campaignLogLists.indexOf(log);
        if (logIndex >= 0 && logIndex < campaignLogLists.size()) {
            return campaignLogLists.get(logIndex).contains(entry);
        } else {
            return false;
        }
    }

    public ArrayList<InvestigatorState> getAvailableInvestigators() {
        return investigatorStates;
    }

    public boolean isScenarioAvailable(ScenarioInfo scenarioInfo) {
        for (BaseCondition condition : scenarioInfo.prerequisites) {
            if (!condition.evaluate(this)) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<ScenarioInfo> getAvailableScenarios() {
        ArrayList<ScenarioInfo> availableScenarios = new ArrayList<>();
        for (ScenarioInfo scenarioInfo : getCampaignInfo().scenarios) {
            if (isScenarioAvailable(scenarioInfo)) {
                availableScenarios.add(scenarioInfo);
            }
        }
        for (ScenarioInfo scenarioInfo : GameData.getInstance().getStandaloneScenarios()) {
            if (isScenarioAvailable(scenarioInfo)) {
                availableScenarios.add(scenarioInfo);
            }
        }
        return availableScenarios;
    }

    public boolean isScenarioCompleted(String scenarioId) {
        for (ScenarioState scenarioState : completedScenarios) {
            if (scenarioState.getScenarioId().equals(scenarioId)) {
                return true;
            }
        }
        return false;
    }

    public interface InvestigatorListListener {
        void onUpdateInvestigatorList();
    }
}
