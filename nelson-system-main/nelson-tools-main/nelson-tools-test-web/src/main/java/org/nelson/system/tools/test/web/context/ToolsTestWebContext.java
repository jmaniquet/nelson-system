package org.nelson.system.tools.test.web.context;

import org.nelson.system.tools.test.web.ToolsTestWebScannable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ToolsTestWebScannable.class)
public class ToolsTestWebContext {
}
