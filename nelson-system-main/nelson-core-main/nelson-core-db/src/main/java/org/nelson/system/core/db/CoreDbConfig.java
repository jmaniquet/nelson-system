package org.nelson.system.core.db;

import org.nelson.system.core.api.CoreApiConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreApiConfig.class)
@ComponentScan(basePackageClasses = CoreDbScannable.class)
public class CoreDbConfig {
}
