package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData;

import java.util.ArrayList;

/**
 * Created by Paul Burg on 4/23/2017.
 */

public class InvestigatorScenarioProgress {
    private String investigatorId;

    private int experienceEarned;
    private int physicalTraumaGained;
    private int mentalTraumaGained;
    private ArrayList<String> cardsGained;

    public InvestigatorScenarioProgress(String investigatorId) {
        this.investigatorId = investigatorId;
        experienceEarned = 0;
        physicalTraumaGained = 0;
        mentalTraumaGained = 0;
        cardsGained = new ArrayList<>();
    }

    public String getInvestigatorId() {
        return investigatorId;
    }

    public int getExperienceEarned() {
        return experienceEarned;
    }

    public int getPhysicalTraumaGained() {
        return physicalTraumaGained;
    }

    public int getMentalTraumaGained() {
        return mentalTraumaGained;
    }

    public ArrayList<String> getCardsGained() {
        return cardsGained;
    }

    public void addExperience(int xp) {
        experienceEarned += xp;
    }

    public void addMentalTrauma(int trauma) {
        mentalTraumaGained += trauma;
    }

    public void addPhysicalTrauma(int trauma) {
        physicalTraumaGained += trauma;
    }
}
