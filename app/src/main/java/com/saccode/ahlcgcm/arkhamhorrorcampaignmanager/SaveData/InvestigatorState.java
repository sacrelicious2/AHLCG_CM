package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public class InvestigatorState
{
    public InvestigatorState(CharSequence investigatorName, CharSequence playerName)
    {
        this.investigatorName = investigatorName;
        this.playerName = playerName;
    }

    public CharSequence getInvestigatorName() {
        return investigatorName;
    }

    public CharSequence getPlayerName() {
        return playerName;
    }

    public void setPlayerName(CharSequence playerName) {
        this.playerName = playerName;
    }

    private CharSequence investigatorName;

    private CharSequence playerName;
}
