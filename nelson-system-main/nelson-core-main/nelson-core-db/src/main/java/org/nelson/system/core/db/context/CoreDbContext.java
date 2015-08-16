package org.nelson.system.core.db.context;

import org.nelson.system.core.api.context.CoreApiContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(CoreApiContext.class)
@ComponentScan(basePackages = "org.nelson.system.core.db")
public class CoreDbContext {
}
