package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class CampaignState {

    public interface InvestigatorListListener
    {
        public void onUpdateInvestigatorList();
    }

    public CampaignState(CharSequence campaignName, CharSequence customName)
    {
        this.campaignName = campaignName.toString();
        this.customName = customName.toString();
        investigatorStates = new ArrayList<InvestigatorState>();
        campaignLog = new HashMap<CharSequence, List<CharSequence>>();
    }

    public void addInvestigator(CharSequence investigatorName, CharSequence playerName)
    {
        investigatorStates.add(new InvestigatorState(investigatorName, playerName));
        investigatorListListener.onUpdateInvestigatorList();
    }

    public ArrayList<InvestigatorState> getInvestigatorStates(){
        return investigatorStates;
    }

    public CharSequence getCampaignName() {
        return campaignName;
    }

    public CharSequence getCustomName() {
        return customName;
    }

    public void setCustomName(CharSequence customName) {
        this.customName = customName.toString();
    }

    private String customName;
    private String campaignName;
    private ArrayList<InvestigatorState> investigatorStates;
    private Map<CharSequence, List<CharSequence>> campaignLog;

    public void setInvestigatorListListener(InvestigatorListListener investigatorListListener) {
        this.investigatorListListener = investigatorListListener;
    }

    InvestigatorListListener investigatorListListener;

}
