package com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.GameData.Conditions;

import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.RuntimeTypeAdapterFactory;
import com.saccode.ahlcgcm.arkhamhorrorcampaignmanager.SaveData.CampaignState;

import java.util.HashMap;

/**
 * Created by Paul Burg on 4/21/2017.
 */

public abstract class BaseCondition {
    private static HashMap<String, Class> conditionTypeMap = new HashMap<>();

    static {
        registerClass(ConditionCampaignLogContains.type, ConditionCampaignLogContains.class);
        registerClass(ConditionHasCompletedScenario.type, ConditionHasCompletedScenario.class);
        registerClass(ConditionNot.type, ConditionNot.class);
        registerClass(ConditionAnd.type, ConditionAnd.class);
        registerClass(ConditionOr.type, ConditionOr.class);
    }

    protected static void registerClass(String className, Class classType) {
        conditionTypeMap.put(className, classType);
    }

    public static Class getClass(String typeName) {
        return conditionTypeMap.get(typeName);
    }

    public static RuntimeTypeAdapterFactory getFactory() {
        RuntimeTypeAdapterFactory<BaseCondition> factory = RuntimeTypeAdapterFactory.of(BaseCondition.class, "type");
        for (HashMap.Entry<String, Class> entry : conditionTypeMap.entrySet()) {
            factory.registerSubtype(entry.getValue(), entry.getKey());
        }
        return factory;
    }

    public abstract String getType();

    public abstract boolean evaluate(CampaignState campaignState);
}
