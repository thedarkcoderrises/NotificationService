package tdcr.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;

/**
 * Hello world!
 *
 */
//@SpringBootApplication(exclude = ContextRegionProviderAutoConfiguration.class)
@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {

        SpringApplication.run(App.class, args);
    }


}
