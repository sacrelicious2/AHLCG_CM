package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.SaveData;

public class CampaignActivity extends AppCompatActivity
        implements AddInvestigatorDialogFragment.AddInvestigatorDialogListener {
    public static final String CAMPAIGN_INDEX = "com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.CAMPAIGN_INDEX";

    private CampaignState campaignState;

    @Override
    public void onAddInvestigatorDialogPositiveClick(DialogFragment dialog, CharSequence playerName, CharSequence characterName) {
        campaignState.addInvestigator(characterName, playerName);
    }

    @Override
    public void onAddInvestigatorDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign);

        Intent intent = getIntent();
        int campaignIndex = intent.getIntExtra(CAMPAIGN_INDEX, -1);
        campaignState = SaveData.getInstance().getCampaignState(campaignIndex);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (campaignState != null) {
            toolbar.setTitle(campaignState.getName());
            toolbar.setSubtitle(String.format("%1$s - %2$s", campaignState.getCampaignName(), campaignState.getDifficulty()));
        }

        setSupportActionBar(toolbar);

        ExpandableListView investigatorsListView = (ExpandableListView) findViewById(R.id.campaign_list_view);
        CampaignStateAdapter investigatorListAdapter = new CampaignStateAdapter(this, campaignState);
        campaignState.setInvestigatorListListener(investigatorListAdapter);
        investigatorsListView.setAdapter(investigatorListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_campaign, menu);
        MenuItem addInvestigatorMenuItem = menu.findItem(R.id.add_investigator_menu_item);
        addInvestigatorMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AddInvestigatorDialogFragment dialogFragment = new AddInvestigatorDialogFragment();
                dialogFragment.show(getFragmentManager(), "AddInvestigatorDialogFragment");
                return true;
            }
        });
        MenuItem startScenarioMenuItem = menu.findItem(R.id.start_scenario_menu_item);
        startScenarioMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                StartScenarioDialogFragment dialogFragment = new StartScenarioDialogFragment();
                dialogFragment.setCampaignState(campaignState);
                dialogFragment.show(getFragmentManager(), "StartScenarioDialogFragment");
                return true;
            }
        });
        return true;
    }


}
