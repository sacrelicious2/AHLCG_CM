package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.SaveData;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/22/2017.
 */

public class CampaignListAdapter extends ArrayAdapter<CampaignState>
    implements SaveData.UpdateCampaignListListener
{
    public CampaignListAdapter(Context context, ArrayList<CampaignState> campaigns)
    {
        super(context, 0, campaigns);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CampaignState campaignState = getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.campaign_list_item, parent, false);
        }

        TextView customNameView = (TextView) convertView.findViewById(R.id.customName);
        TextView campaignNameView = (TextView) convertView.findViewById(R.id.campaignName);
        customNameView.setText(campaignState.getCustomName());
        campaignNameView.setText(campaignState.getCampaignName());
        return convertView;
    }

    @Override
    public void OnUpdateCampaignList() {
        notifyDataSetChanged();
    }
}
