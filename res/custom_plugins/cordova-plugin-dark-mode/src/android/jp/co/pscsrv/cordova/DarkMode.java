package jp.co.pscsrv.cordova;

import android.content.res.Configuration;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DarkMode extends CordovaPlugin {

    private static final String TAG = "DarkMode";

    private CallbackContext callbackContext;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("init")) {
            this.callbackContext = callbackContext;
        } else if (action.equals("getTheme")) {
            getTheme(args, callbackContext);
        }

        return true;
    }

    private void getTheme(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String theme = getTheme(this.cordova.getActivity().getResources().getConfiguration());
        PluginResult pluginResult = new  PluginResult(PluginResult.Status.OK, theme);
        callbackContext.sendPluginResult(pluginResult);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (callbackContext != null) {
            String theme = getTheme(newConfig);
            if (theme != null) {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, theme);
                pluginResult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginResult);
            }
        }
    }

    private String getTheme(Configuration config) {
        int currentNightMode = config.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                return "light";
            case Configuration.UI_MODE_NIGHT_YES:
                return "dark";
            default:
                return null;
        }
    }
}
