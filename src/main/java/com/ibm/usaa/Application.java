package com.ibm.usaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationPidFileWriter;

/**
 * Main class to run the Spring Boot application. This eliminates the need of
 * web.xml file.
 * 
 * @author Peter Neil Cagatin (Yami)
 *
 */
@SpringBootApplication(scanBasePackages = { "com.ibm.usaa" })
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationPidFileWriter("internal-dashboard-services.pid"));
        springApplication.run(args);
    }

}
