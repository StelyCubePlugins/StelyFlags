package fr.flowsqy.stelyflags;

import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;

public class StelyFlags {

    public static StateFlag SPAWN_SPAWNER;
    public static StateFlag INTERACT_VILLAGER;

    public static void load(FlagRegistry flagRegistry) {
        if (SPAWN_SPAWNER == null)
            SPAWN_SPAWNER = registerFlag(flagRegistry, "spawn-spawner");
        if (INTERACT_VILLAGER == null)
            INTERACT_VILLAGER = registerFlag(flagRegistry, "interact-villager");
    }

    private static StateFlag registerFlag(FlagRegistry flagRegistry, String flagName) {
        final Flag<?> flag = flagRegistry.get(flagName);
        if (flag == null) {
            final StateFlag finalFlag = new StateFlag(flagName, false);
            flagRegistry.register(finalFlag);
            return finalFlag;
        }
        if (flag instanceof StateFlag)
            return (StateFlag) flag;

        throw new RuntimeException("Can not register the flag '" + flagName + "', another exist with the same name but can not be used");
    }

}
