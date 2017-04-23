package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.CampaignInfo;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.GameData;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class CampaignState {

    public interface InvestigatorListListener
    {
        public void onUpdateInvestigatorList();
    }

    public CampaignState(CharSequence campaignName, CharSequence campaignId)
    {
        this.name = campaignName.toString();
        this.campaignId = campaignId.toString();
        investigatorStates = new ArrayList<>();
        campaignLogLists = new ArrayList<>();
        CampaignInfo campaignInfo = getCampaignInfo();
        for (int iCampaignLogList = 0; iCampaignLogList < campaignInfo.campaignLogLists.size(); ++iCampaignLogList)
        {
            campaignLogLists.add(new ArrayList<String>());
        }
    }

    public void addInvestigator(CharSequence investigatorName, CharSequence playerName)
    {
        investigatorStates.add(new InvestigatorState(investigatorName, playerName));
        investigatorListListener.onUpdateInvestigatorList();
    }

    public ArrayList<InvestigatorState> getInvestigatorStates(){
        return investigatorStates;
    }
    public InvestigatorState getInvestigatorState(int index)
    {
        if (index < investigatorStates.size())
        {
            return investigatorStates.get(index);
        }
        return null;
    }

    public CharSequence getName() {
        return name;
    }

    public CampaignInfo getCampaignInfo() {return GameData.getInstance().getCampaignInfo(campaignId);}

    public CharSequence getCampaignName() {return getCampaignInfo().name;}

    private String name;
    private String campaignId;
    private ArrayList<InvestigatorState> investigatorStates;
    private ArrayList<ArrayList<String>> campaignLogLists;

    public void setInvestigatorListListener(InvestigatorListListener investigatorListListener) {
        this.investigatorListListener = investigatorListListener;
    }

    public int getCampaignLogListCount()
    {
        return getCampaignInfo().campaignLogLists.size();
    }

    public int getCampaignLogListSize(int index)
    {
        if (index < campaignLogLists.size())
        {
            return campaignLogLists.get(index).size();
        }
        return 0;
    }

    public CharSequence getCampaignLogEvent(int logIndex, int eventIndex)
    {
        if (logIndex < campaignLogLists.size())
        {
            ArrayList<String> log = campaignLogLists.get(logIndex);
            if (eventIndex < log.size())
            {
                return log.get(eventIndex);
            }
        }
        return null;
    }

    public CharSequence getCampaignLogName(int logIndex)
    {
        CampaignInfo campaignInfo = getCampaignInfo();
        if (logIndex < campaignInfo.campaignLogLists.size())
        {
            return campaignInfo.campaignLogLists.get(logIndex);
        }
        return "**INVALID CAMPAIGN LOG NAME**";
    }

    public int getInvestigatorCount()
    {
        return investigatorStates.size();
    }

    InvestigatorListListener investigatorListListener;

}
