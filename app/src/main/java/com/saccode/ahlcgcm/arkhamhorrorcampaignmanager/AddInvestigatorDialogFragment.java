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

/**
 * Created by Paul Burg on 4/22/2017.
 */

public class AddInvestigatorDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    public interface AddInvestigatorDialogListener {
        public void onAddInvestigatorDialogPositiveClick(DialogFragment dialog, CharSequence playerName, CharSequence characterName);

        public void onAddInvestigatorDialogNegativeClick(DialogFragment dialog);
    }

    private AddInvestigatorDialogListener addInvestigatorDialogListener;
    private int selectedInvestigator;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedInvestigator = pos;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            addInvestigatorDialogListener = (AddInvestigatorDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement AddInvestigatorDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        selectedInvestigator = 0;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_investigator);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.add_investigator_dialog, null);
        TextView textView = (TextView) view.findViewById(R.id.player_name);

        Spinner campaignSelectSpinner = (Spinner) view.findViewById(R.id.investigator_spinner);
        campaignSelectSpinner.setAdapter(new ArrayAdapter<CharSequence>(view.getContext(), android.R.layout.simple_spinner_item, GameData.getInstance().getInvestigatorNames()));
        campaignSelectSpinner.setOnItemSelectedListener(this);

        builder.setView(view);

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addInvestigatorDialogListener.onAddInvestigatorDialogNegativeClick(AddInvestigatorDialogFragment.this);
            }
        });
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView textView = (TextView) ((AlertDialog) dialog).findViewById(R.id.player_name);
                addInvestigatorDialogListener.onAddInvestigatorDialogPositiveClick(AddInvestigatorDialogFragment.this, textView.getText(), GameData.getInstance().getInvestigatorName(selectedInvestigator));
            }
        });
        return builder.create();

    }
}
