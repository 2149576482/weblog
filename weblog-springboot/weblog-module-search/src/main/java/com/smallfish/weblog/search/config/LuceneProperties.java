package com.smallfish.weblog.search.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@ConfigurationProperties(prefix = "lucene")
@Component
@Data
public class LuceneProperties {

    private String indexDir;

}
