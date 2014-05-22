package ru.terra.tboard.constants;

import ru.terra.server.config.Config;

/**
 * Date: 27.02.14
 * Time: 16:09
 */
public class FilePatchConstants {
    private static Config config = Config.getConfig();

    public static String getMainFolder() {
        return config.getValue("main.dir", "web/html/");
    }

    public static String getResFolder() {
        return config.getValue("resources.dir", "web/resources/");
    }

    public static String getPiczFolder() {
        return getResFolder() + "/picz/";
    }
}
