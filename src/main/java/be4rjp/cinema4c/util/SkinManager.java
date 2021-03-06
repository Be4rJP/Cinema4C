package be4rjp.cinema4c.util;

import be4rjp.cinema4c.Cinema4C;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SkinManager {
    
    /**
     * Mojangのセッションサーバーからスキン情報を取得してくる
     * @param uuid
     * @return
     */
    public static String[] getSkin(String uuid){
        try {
            URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            JsonObject property = new JsonParser().parse(reader).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            String texture = property.get("value").getAsString();
            String signature = property.get("signature").getAsString();
            String[] skin = {texture, signature};
            return skin;
        } catch (Exception e) {
            Cinema4C.getPlugin().getLogger().warning("An error occurred when querying Mojang's session server for skin information.");
            Cinema4C.getPlugin().getLogger().warning(e.getMessage());
            return null;
        }
    }
}
