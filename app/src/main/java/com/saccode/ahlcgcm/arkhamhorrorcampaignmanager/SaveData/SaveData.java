package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/22/2017.
 */

public class SaveData{

    public interface UpdateCampaignListListener
    {
        public void OnUpdateCampaignList();
    }

    private SaveData()
    {
        campaigns = new ArrayList<CampaignState>();
    }

    static private SaveData instance;

    public void setUpdateCampaignListListener(UpdateCampaignListListener updateCampaignListListener) {
        this.updateCampaignListListener = updateCampaignListListener;
    }

    private UpdateCampaignListListener updateCampaignListListener;

    static public SaveData getInstance()
    {
        if (instance == null)
        {
            instance = new SaveData();
        }
        return instance;
    }

    public ArrayList<CampaignState> getCampaigns() {
        return campaigns;
    }

    private ArrayList<CampaignState> campaigns;

    public int addCampaign(CampaignState campaign)
    {
        campaigns.add(campaign);
        updateCampaignListListener.OnUpdateCampaignList();
        return campaigns.size() - 1;
    }

    public CampaignState getCampaignState(int index)
    {
        if (index < campaigns.size())
        {
            return campaigns.get(index);
        }
        return null;
    }

    public void deleteCampaign(int index)
    {
        if (index < campaigns.size())
        {
            campaigns.remove(index);
            updateCampaignListListener.OnUpdateCampaignList();
        }
    }
}
