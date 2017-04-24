package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.ScenarioInfo;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.InvestigatorState;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class StartScenarioDialogFragment extends DialogFragment {
    private int selectedScenario;
    private CampaignState campaignState;

    public CampaignState getCampaignState() {
        return campaignState;
    }

    public void setCampaignState(CampaignState campaignState) {
        this.campaignState = campaignState;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedScenario = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.start_scenario);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.start_scenario_dialog, null);

        ListView investigatorSelectListView = (ListView) view.findViewById(R.id.investigator_select_listview);
        ArrayList<InvestigatorState> availableInvestigators = campaignState.getAvailableInvestigators();
        ArrayList<CharSequence> availableInvestigatorNames = new ArrayList<>();
        for (InvestigatorState investigatorState : availableInvestigators) {
            availableInvestigatorNames.add(investigatorState.getInvestigatorName());
        }
        ArrayAdapter<CharSequence> investigatorSelectAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_list_item_checked, availableInvestigatorNames);
        investigatorSelectListView.setAdapter(investigatorSelectAdapter);

        for (int iInvestigator = 0; iInvestigator < availableInvestigatorNames.size(); ++iInvestigator) {
            investigatorSelectListView.setItemChecked(iInvestigator, iInvestigator < 4);
        }

        Spinner selectScenarioSpinner = (Spinner) view.findViewById(R.id.select_scenario_spinner);
        ArrayList<ScenarioInfo> availableScenarios = campaignState.getAvailableScenarios();
        ArrayList<CharSequence> scenarioNames = new ArrayList<>();
        for (ScenarioInfo scenario : availableScenarios) {
            scenarioNames.add(scenario.name);
        }
        ArrayAdapter<CharSequence> selectScenarioAdapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, scenarioNames);
        selectScenarioSpinner.setAdapter(selectScenarioAdapter);
        builder.setView(view);

        return builder.create();
    }
}
