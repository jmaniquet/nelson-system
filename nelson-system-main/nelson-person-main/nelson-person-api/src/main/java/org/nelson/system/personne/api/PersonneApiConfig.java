package org.nelson.system.personne.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PersonneApiScannable.class)
public class PersonneApiConfig {

}
