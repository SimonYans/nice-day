package top.niceday.yan.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "url")
public class AllowUrlProperties {
    private List<String> allowList = new ArrayList<>();
}
