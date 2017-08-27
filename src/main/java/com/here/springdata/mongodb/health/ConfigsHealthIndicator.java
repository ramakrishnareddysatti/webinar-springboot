package com.here.springdata.mongodb.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component("configsHealth")
public class ConfigsHealthIndicator implements HealthIndicator {

    @Value("${spring.data.mongodb.host}")
    private String hostName;

    @Override
    public Health health() {

        Health.Builder builder = Health.up();

        Map<String, String> propertiesMap = new HashMap<>();
        propertiesMap.put("hostName", hostName);

        Map<String, String> failedPropertiesMap = propertiesMap.entrySet().stream().filter(entry -> {
            boolean inValidHostName = true;
            String hostName = entry.getValue();
            if (hostName != null && hostName.length() > 0) {
                 //You can also validate Urls if any in your configurations
                //if (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://"))
                inValidHostName = false;
            }
            return inValidHostName;

        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (failedPropertiesMap.size()>0) {
            builder.down();
            failedPropertiesMap.entrySet().forEach(entry -> {
                builder.withDetail("Invalid HostName -> " + entry.getKey(), entry.getValue());
            });
        }
        return builder.build();
    }

}
