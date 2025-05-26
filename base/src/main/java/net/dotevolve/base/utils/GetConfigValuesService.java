/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 10/02/23, 2:25 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
public class GetConfigValuesService {

    protected static final Logger logger = LogManager.getLogger(GetConfigValuesService.class);

    @Autowired
    RestClient restClient;

    Map<ConfigEnum, Map<String, Object>> valueMap = new HashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    private void initValues() {
        try {
            String countriesResp = restClient.get("https://tech.dotevolve.net/config/countries.json");
            valueMap.put(ConfigEnum.COUNTRY,
                    getMap(((ArrayList<Object>) CodeHelp.getMapFromJson(countriesResp).get("data"))));
            String stateResp = restClient.get("https://tech.dotevolve.net/config/countries.json");
            valueMap.put(ConfigEnum.US_STATES,
                    getMap(((ArrayList<Object>) CodeHelp.getMapFromJson(stateResp).get("data"))));
            logger.info("Config values loaded");
        } catch (Exception e) {
            logger.error("Error while getting config values", e);
        }

    }

    public Map<String, Object> getMap(ConfigEnum configEnum) {
        return valueMap.get(configEnum);
    }

    private Map<String, Object> getMap(List<Object> countriesRespMap) {
        Map<String, Object> map = new HashMap<>();
        for (Object objectElement : countriesRespMap) {
            Map<String, Object> objectMap = (Map) objectElement;
            map.put(objectMap.get("key").toString(), objectMap.get("value"));
        }
        return map;
    }

    public String getValue(ConfigEnum config, String value) {
        return valueMap.get(config).get(value).toString();
    }

    public String getKey(ConfigEnum config, String value) {
        if (CodeHelp.isEmpty(value)) {
            return "";
        }
        for (Entry<String, Object> entry : valueMap.get(config).entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return "";
    }

    public enum ConfigEnum {
        COUNTRY,
        US_STATES
    }
}
