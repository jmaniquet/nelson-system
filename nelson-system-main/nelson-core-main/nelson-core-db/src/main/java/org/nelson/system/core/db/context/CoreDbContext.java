package org.nelson.system.core.db.context;

import org.nelson.system.core.api.context.CoreApiContext;
import org.nelson.system.core.db.CoreDbScannable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreApiContext.class)
@ComponentScan(basePackageClasses = CoreDbScannable.class)
public class CoreDbContext {
}
