package cl.ecommerce.ecommerce.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class DynamicTNSConfig implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String osName = System.getProperty("os.name").toLowerCase();
        String tnsAdminPath;

        if (osName.contains("win")) {
            tnsAdminPath = "C:/oracle/ecommerce/";
        } else {
            tnsAdminPath = "/opt/oracle/ecommerce/";
        }

        Map<String, Object> props = new HashMap<>();
        props.put("spring.datasource.url", "jdbc:oracle:thin:@ECOMMERCE_HIGH?TNS_ADMIN=" + tnsAdminPath);

        environment.getPropertySources().addFirst(new MapPropertySource("osBasedProps", props));
    }
}
