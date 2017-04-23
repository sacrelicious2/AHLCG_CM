package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class GameData {
    public ArrayList<String> investigatorNames;
    public ArrayList<CampaignInfo> campaigns;
    public ArrayList<ScenarioInfo> standaloneScenarios;
    public ArrayList<String> globalCampaignLogOptions;

    private GameData()
    {
        investigatorNames = new ArrayList<String>();
        investigatorNames.add("\"Ashcan\" Pete");
        investigatorNames.add("\"Skids\" O'Toole");
        investigatorNames.add("Agnes Baker");
        investigatorNames.add("Daisy Walker");
        investigatorNames.add("Jenny Barnes");
        investigatorNames.add("Jim Culver");
        investigatorNames.add("Marie Lambeau");
        investigatorNames.add("Rex Murphy");
        investigatorNames.add("Roland Banks");
        investigatorNames.add("Wendy Adams");
        investigatorNames.add("Zoey Samaras");
        campaigns = new ArrayList<CampaignInfo>();
        CampaignInfo NotZ = new CampaignInfo();
        CampaignInfo TDL = new CampaignInfo();
        CampaignInfo TPtC = new CampaignInfo();
        NotZ.name = "Night of the Zealot";
        TDL.name = "The Dunwich Legacy";
        TPtC.name = "The Path to Carcosa";
        campaigns.add(NotZ);
        campaigns.add(TDL);
        campaigns.add(TPtC);
        standaloneScenarios = new ArrayList<ScenarioInfo>();
        globalCampaignLogOptions = new ArrayList<String>();
    }

    private static GameData instance = null;

    static public GameData getInstance()
    {
        if (instance == null)
        {
            instance = new GameData();
        }
        return instance;
    }

    public List<CharSequence> getCampaignNames()
    {
        List<CharSequence> names = new ArrayList<CharSequence>();
        for (CampaignInfo info : campaigns)
        {
            names.add(info.name);
        }
        return names;
    }

    public CampaignInfo getCampaignInfo(CharSequence campaignName)
    {
        for (CampaignInfo info : campaigns)
        {
            if (info.name == campaignName)
            {
                return info;
            }
        }
        return null;
    }

    public CharSequence getCampaignName(int iCampaign)
    {
        if (iCampaign < campaigns.size())
        {
            return campaigns.get(iCampaign).name;
        }
        return "**Invalid**";
    }

    public List<String> getInvestigatorNames()
    {
        return investigatorNames;
    }

    public CharSequence getInvestigatorName(int index)
    {
        if (index < investigatorNames.size())
        {
            return investigatorNames.get(index);
        }
        return "**INVALID INVESTIGATOR**";
    }
}
