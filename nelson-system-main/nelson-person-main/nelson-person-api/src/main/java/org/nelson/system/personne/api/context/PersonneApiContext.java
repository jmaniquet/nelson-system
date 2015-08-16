package org.nelson.system.personne.api.context;

import org.nelson.system.personne.api.PersonneApiScannable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PersonneApiScannable.class)
public class PersonneApiContext {

}
