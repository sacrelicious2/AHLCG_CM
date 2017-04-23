package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.InvestigatorState;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/22/2017.
 */

public class CampaignStateAdapter extends BaseExpandableListAdapter
    implements CampaignState.InvestigatorListListener
{
    private static final int INVESTIGATOR_GROUP = 0;
    private static final int COMPLETED_SCENARIO_GROUP = 1;
    private static final int CAMPAIGN_LOG_START_GROUP = 2;

    private CampaignState campaignState;
    private Context context;

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == INVESTIGATOR_GROUP)
        {
            return campaignState.getInvestigatorCount();
        }
        else if (groupPosition == COMPLETED_SCENARIO_GROUP)
        {
            return 0; //TODO : Implement
        }
        else
        {
            return campaignState.getCampaignLogListSize(groupPosition - CAMPAIGN_LOG_START_GROUP);
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (groupPosition == INVESTIGATOR_GROUP)
        {
            return campaignState.getInvestigatorState(childPosition);
        }
        else if (groupPosition == COMPLETED_SCENARIO_GROUP)
        {
            return null;
        }
        else
        {
            return campaignState.getCampaignLogEvent(groupPosition - CAMPAIGN_LOG_START_GROUP, childPosition);
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (groupPosition == INVESTIGATOR_GROUP)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.investigator_list_header, parent, false);
            int numInvestigators = campaignState.getInvestigatorCount();
            String resourceString = parent.getResources().getString(R.string.investigators);
            String text = String.format("%1$s - %2$d", resourceString, numInvestigators);
            TextView investigatorHeaderView = (TextView) convertView.findViewById(R.id.investigator_list_header_textview);
            investigatorHeaderView.setText(text);
            return convertView;
        }
        else if (groupPosition == COMPLETED_SCENARIO_GROUP)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.completed_scenario_list_header, parent, false);
            return convertView;
        }
        else
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.campaign_log_list_header, parent, false);
            TextView campaignLogHeaderView = (TextView) convertView.findViewById(R.id.campaign_log_name);
            int logIndex = groupPosition - CAMPAIGN_LOG_START_GROUP;
            CharSequence campaignLogName = campaignState.getCampaignLogName(logIndex);
            int campaignLogSize = campaignState.getCampaignLogListSize(logIndex);
            String text = String.format("%1$s - %2$d", campaignLogName, campaignLogSize);
            campaignLogHeaderView.setText(text);
            return convertView;
        }
    }

    @Override
    public int getGroupCount() {
        return CAMPAIGN_LOG_START_GROUP + campaignState.getCampaignLogListCount();
    }

    public CampaignStateAdapter(Context context, CampaignState campaignState)
    {
        this.campaignState = campaignState;
        this.context = context;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (groupPosition == INVESTIGATOR_GROUP)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.investigator_list_item, parent, false);

            InvestigatorState investigatorState = (InvestigatorState) getChild(groupPosition, childPosition);
            TextView investigatorNameView = (TextView) convertView.findViewById(R.id.investigator_name);
            TextView playerNameView = (TextView) convertView.findViewById(R.id.player_name);
            investigatorNameView.setText(investigatorState.getInvestigatorName());
            playerNameView.setText(investigatorState.getPlayerName());

            return convertView;
        }
        else if (groupPosition == COMPLETED_SCENARIO_GROUP)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.completed_scenario_list_item, parent, false);
            return convertView;
        }
        else
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.campaign_log_list_item, parent, false);
            CharSequence eventText = (CharSequence) getChild(groupPosition, childPosition);
            return convertView;
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void onUpdateInvestigatorList() {
        notifyDataSetChanged();
    }
}
