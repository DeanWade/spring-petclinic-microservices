package org.springframework.samples.petclinic.portal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@ImportResource({ "classpath:dubbo.xml" })
@Profile("dubbo")
public class DubboConfig {

}
