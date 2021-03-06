package com.sidhant.automata.core.cli.config;

import com.sidhant.automata.config.CommonConfig;
import com.sidhant.automata.core.cli.common.CliParameters;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FmwkConfig {

    private static FmwkConfig fmwkConfig = null;

    private FmwkConfig() {
    }

    private String firm;
    private List<String> users;
    private Map<String, String> usernames;

    public List<String> getUsers() {
        return users;
    }

    public Map<String, String> getUsernames() {
        return usernames;
    }

    public String getFirm() {
        return firm;
    }

    public static FmwkConfig getInstance() {
        if (Objects.isNull(fmwkConfig)) {
            fmwkConfig = new FmwkConfig();
        }
        return fmwkConfig;
    }

    public void createTheConfig(CliParameters parameters, CommonConfig commonConfig) {
        firm = parameters.firm;
        users = commonConfig.getAppConfig().users();
        usernames = commonConfig.getAppConfig().usernames();
    }

}
