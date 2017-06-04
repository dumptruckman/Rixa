package me.savvy.rixa.data.filemanager;

import me.savvy.rixa.data.thunderbolt.Thunderbolt;
import me.savvy.rixa.data.thunderbolt.exceptions.FileLoadException;
import me.savvy.rixa.data.thunderbolt.io.ThunderFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Timber on 6/4/2017.
 */
public class ConfigManager {
    private ThunderFile tf;

    public ConfigManager() {
        tf = null;
        try {
            if(!Thunderbolt.load("config", "Rixa")) {
                try {
                    tf = Thunderbolt.get("config");
                    addDefaults();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            tf = Thunderbolt.get("config");
        } catch (FileLoadException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addDefaults() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("hostName", "localhost");
        map.put("password", "password");
        map.put("databaseName", "rixa");
        map.put("userName", "rixa_users");
        map.put("portNumber", "3306");
        tf.set("sql", map);

        tf.set("secretToken", "YOUR_TOKEN_HERE");
        tf.set("botGame", "Rixa 2.0 | http://rixa.io/invite");
        tf.save();
    }

    public ThunderFile getConfig() {
        return tf;
    }
}