/*******************************************************************************
 * @Copyright (c) 2023 DotEvolve, All rights reserved
 * @author DotEvolve
 * @since 11/02/23, 12:46 am
 *
 *
 ******************************************************************************/

package net.dotevolve.base.utils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.dotevolve.base.data.ServiceEnum;

@Component
public class ServiceUrlRegistry {
    private final Map<ServiceEnum, List<String>> serviceToUrlsMap = new HashMap<>();

    private final List<String> top5SubscriptionUrls = Arrays.asList("https://tech.dotevolve.net/clrApp/top5/login",
            "https://app.dotevolve.net/clrApp/top5/login", "https://app.dotevolve.net/");

    private final List<String> top5Urls = Arrays.asList("https://tech.dotevolve.net/mse2/top5/home/dashboard",
            "https://app.dotevolve.net/mse2/top5/home/dashboard", "https://app.dotevolve.net/");

    @PostConstruct
    void populateServiceToUrlsMap() {
        serviceToUrlsMap.put(ServiceEnum.TOP5_SUBSCRIPTION, new ArrayList<>());
        serviceToUrlsMap.get(ServiceEnum.TOP5_SUBSCRIPTION).addAll(top5SubscriptionUrls);

        serviceToUrlsMap.put(ServiceEnum.TOP5, new ArrayList<>());
        serviceToUrlsMap.get(ServiceEnum.TOP5).addAll(top5Urls);
    }

    public ServiceEnum getServiceTypeByUrl(String url) {
        for (Map.Entry<ServiceEnum, List<String>> entry : serviceToUrlsMap.entrySet()) {
            if (entry.getValue().contains(url)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
