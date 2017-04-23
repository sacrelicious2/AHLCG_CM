package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.SaveData;

public class MainActivity extends AppCompatActivity
        implements CreateCampaignDialogFragment.CreateCampaignDialogListener
{
    public void showCreateCampaignDialog() {
        CreateCampaignDialogFragment dialog = new CreateCampaignDialogFragment();
        dialog.show(getFragmentManager(), "CreateCampaignDialogFragment");
    }

    private void startCampaignActivity(int campaignIndex)
    {
        Intent intent = new Intent(this, CampaignActivity.class);
        intent.putExtra(CampaignActivity.CAMPAIGN_INDEX, campaignIndex);
        startActivity(intent);
    }

    @Override
    public void onCreateCampaignDialogPositiveClick(DialogFragment dialog, CharSequence campaignName, CharSequence customName)
    {
        int campaignIndex = SaveData.getInstance().addCampaign(new CampaignState(campaignName, customName));
        startCampaignActivity(campaignIndex);
    }

    @Override
    public void onCreateCampaignDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabCreateCampaign = (FloatingActionButton) findViewById(R.id.fabCreateCampaign);
        fabCreateCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateCampaignDialog();
            }
        });

        ListView campaignListView = (ListView) findViewById(R.id.campaign_list);
        campaignListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startCampaignActivity(position);
            }
        });
        campaignListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder deleteDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                deleteDialogBuilder.setTitle(R.string.delete_campaign);
                deleteDialogBuilder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                deleteDialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SaveData.getInstance().deleteCampaign(position);
                    }
                });

                AlertDialog deleteDialog = deleteDialogBuilder.create();

                deleteDialog.show();

                return true;
            }
        });
        campaignListAdapter = new CampaignListAdapter(this, SaveData.getInstance().getCampaigns());
        SaveData.getInstance().setUpdateCampaignListListener(campaignListAdapter);
        campaignListView.setAdapter(campaignListAdapter);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private CampaignListAdapter campaignListAdapter;
}
