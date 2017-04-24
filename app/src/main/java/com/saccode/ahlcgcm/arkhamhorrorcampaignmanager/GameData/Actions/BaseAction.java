package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Actions;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.RuntimeTypeAdapterFactory;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;

import java.util.HashMap;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public abstract class BaseAction {
    private static HashMap<String, Class> actionTypeMap = new HashMap<>();

    protected static void registerClass(String className, Class classType) {
        actionTypeMap.put(className, classType);
    }

    public static Class getClass(String typeName) {
        return actionTypeMap.get(typeName);
    }

    public static RuntimeTypeAdapterFactory getFactory() {
        RuntimeTypeAdapterFactory<BaseAction> factory = RuntimeTypeAdapterFactory.of(BaseAction.class, "type");
        for (HashMap.Entry<String, Class> entry : actionTypeMap.entrySet()) {
            factory.registerSubtype(entry.getValue(), entry.getKey());
        }
        return factory;
    }

    public abstract String getType();

    public abstract boolean execute(CampaignState campaignState);

}
