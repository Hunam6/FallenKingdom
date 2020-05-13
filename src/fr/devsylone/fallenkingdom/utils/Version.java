package fr.devsylone.fallenkingdom.utils;

import org.bukkit.Bukkit;

public class Version {

    public static final String PACKAGE_VERSION;
    public static final VersionType VERSION_TYPE;

    static {
        PACKAGE_VERSION = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
        if (classExists("org.bukkit.block.data.BlockData")) {
            if (classExists("org.bukkit.event.inventory.TradeSelectEvent")) {
                VERSION_TYPE = VersionType.V1_14_PLUS;
            } else {
                VERSION_TYPE = VersionType.V1_13;
            }
        } else if (Bukkit.getVersion().contains("1.8")) {
            VERSION_TYPE = VersionType.V1_8;
        } else {
            VERSION_TYPE = VersionType.V1_9_V1_12;
        }
    }

    public static boolean hasSpigotApi() {
        return classExists("org.spigotmc.SpigotConfig");
    }

    public static boolean isTooOldApi() {
        return !NMSUtils.nmsOptionalClass("IScoreboardCriteria$EnumScoreboardHealthDisplay").isPresent();
    }

    public static boolean isBrigadierSupported() {
        return classExists("com.mojang.brigadier.CommandDispatcher");
    }

    public static boolean isAsyncTabCompleteSupported() {
        return classExists("com.destroystokyo.paper.event.server.AsyncTabCompleteEvent");
    }

    public static boolean isAsyncPlayerSendCommandsEventSupported() {
        return classExists("com.destroystokyo.paper.event.brigadier.CommandRegisteredEvent");
    }

    public static boolean classExists(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException notFound) {
            return false;
        }
    }

    public enum VersionType {
        V1_8,
        V1_9_V1_12,
        V1_13,
        V1_14_PLUS;

        public boolean isHigherOrEqual() {
            return VERSION_TYPE.ordinal() >= ordinal();
        }
    }
}