package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.GameData;

import junit.framework.Assert;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class CreateCampaignDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    private CreateCampaignDialogListener listener;
    private int selectedCampaign;
    private int selectedDifficulty;
    private Spinner difficultySelectSpinner;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent.getId() == R.id.campaign_select_spinner) {
            selectedCampaign = pos;

            difficultySelectSpinner.setAdapter(new ArrayAdapter<CharSequence>(difficultySelectSpinner.getContext(), android.R.layout.simple_spinner_item, GameData.getInstance().getCampaignInfo(selectedCampaign).getDifficultNames()));
        } else if (parent.getId() == R.id.difficulty_select_spinner) {
            selectedDifficulty = pos;
        } else {
            Assert.fail("Unhandled view in onItemSelected");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CreateCampaignDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement CreateCampaignDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedCampaign = 0;
        selectedDifficulty = 0;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.create_campaign);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.create_campaign_dialog, null);
        TextView textView = (TextView) view.findViewById(R.id.campaign_name_field);

        Spinner campaignSelectSpinner = (Spinner) view.findViewById(R.id.campaign_select_spinner);
        campaignSelectSpinner.setAdapter(new ArrayAdapter<CharSequence>(view.getContext(), android.R.layout.simple_spinner_item, GameData.getInstance().getCampaignNames()));
        campaignSelectSpinner.setOnItemSelectedListener(this);

        difficultySelectSpinner = (Spinner) view.findViewById(R.id.difficulty_select_spinner);
        difficultySelectSpinner.setAdapter(new ArrayAdapter<CharSequence>(view.getContext(), android.R.layout.simple_spinner_item, GameData.getInstance().getCampaignInfo(selectedCampaign).getDifficultNames()));
        difficultySelectSpinner.setOnItemSelectedListener(this);

        builder.setView(view);

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onCreateCampaignDialogNegativeClick(CreateCampaignDialogFragment.this);
            }
        });
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) ((AlertDialog) dialog).findViewById(R.id.campaign_name_field);
                listener.onCreateCampaignDialogPositiveClick(CreateCampaignDialogFragment.this, textView.getText(), GameData.getInstance().getCampaignInfo(selectedCampaign).id, selectedDifficulty);
            }
        });
        return builder.create();
    }

    public interface CreateCampaignDialogListener {
        void onCreateCampaignDialogPositiveClick(DialogFragment dialog, CharSequence name, CharSequence campaignId, int difficultyIndex);

        void onCreateCampaignDialogNegativeClick(DialogFragment dialog);
    }
}
