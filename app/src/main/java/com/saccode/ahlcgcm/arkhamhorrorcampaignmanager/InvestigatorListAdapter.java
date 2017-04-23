package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.InvestigatorState;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/22/2017.
 */

public class InvestigatorListAdapter extends ArrayAdapter<InvestigatorState>
    implements CampaignState.InvestigatorListListener
{
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        InvestigatorState investigatorState = getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.investigator_list_item, parent, false);
        }

        TextView investigatorNameView = (TextView) convertView.findViewById(R.id.investigator_name);
        TextView playerNameView = (TextView) convertView.findViewById(R.id.player_name);
        investigatorNameView.setText(investigatorState.getInvestigatorName());
        playerNameView.setText(investigatorState.getPlayerName());

        return convertView;
    }

    @Override
    public void onUpdateInvestigatorList() {
        notifyDataSetChanged();
    }

    public InvestigatorListAdapter(Context context, ArrayList<InvestigatorState> investigators)
    {
        super(context, 0, investigators);
    }
}
