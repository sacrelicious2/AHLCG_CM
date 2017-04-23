package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/22/2017.
 */

public class SaveData {

    static private SaveData instance;
    private UpdateCampaignListListener updateCampaignListListener;
    private ArrayList<CampaignState> campaigns;

    private SaveData() {
        campaigns = new ArrayList<CampaignState>();
    }

    static public SaveData getInstance() {
        if (instance == null) {
            instance = new SaveData();
        }
        return instance;
    }

    public void setUpdateCampaignListListener(UpdateCampaignListListener updateCampaignListListener) {
        this.updateCampaignListListener = updateCampaignListListener;
    }

    public ArrayList<CampaignState> getCampaigns() {
        return campaigns;
    }

    public int addCampaign(CampaignState campaign) {
        campaigns.add(campaign);
        updateCampaignListListener.OnUpdateCampaignList();
        return campaigns.size() - 1;
    }

    public CampaignState getCampaignState(int index) {
        if (index < campaigns.size()) {
            return campaigns.get(index);
        }
        return null;
    }

    public void deleteCampaign(int index) {
        if (index < campaigns.size()) {
            campaigns.remove(index);
            updateCampaignListListener.OnUpdateCampaignList();
        }
    }

    public interface UpdateCampaignListListener {
        void OnUpdateCampaignList();
    }
}
