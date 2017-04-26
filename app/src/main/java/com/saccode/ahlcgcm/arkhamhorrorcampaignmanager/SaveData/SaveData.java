package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/22/2017.
 */

public class SaveData {
    static private final String SAVE_FILE_NAME = "SaveData.json";

    static private SaveData instance;
    static private Context appContext;

    private transient UpdateCampaignListListener updateCampaignListListener;
    private ArrayList<CampaignState> campaigns;

    private SaveData() {
        campaigns = new ArrayList<CampaignState>();
    }

    static public void createInstance(Context context)
    {
        appContext = context;
        try {
            FileInputStream inputStream = appContext.openFileInput(SAVE_FILE_NAME);
            Gson gson = new GsonBuilder().create();
            instance = gson.fromJson(new InputStreamReader(inputStream), SaveData.class);
        } catch (Exception e)
        {
        }
        if (instance == null)
        {
            instance = new SaveData();
        }
    }

    static public SaveData getInstance() {
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
        saveData();
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
            saveData();
        }
    }

    public boolean saveData(){
        try {
            FileOutputStream outputStream = appContext.openFileOutput(SAVE_FILE_NAME, Context.MODE_PRIVATE);
            Gson builder = new GsonBuilder().create();
            String json = builder.toJson(this);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
         return false;
        }
        return true;
    }

    public interface UpdateCampaignListListener {
        void OnUpdateCampaignList();
    }
}
