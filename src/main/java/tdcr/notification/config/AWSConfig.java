package tdcr.notification.config;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AWSConfig {

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() throws Exception {
        if(System.getenv("AWS_ACCESS_KEY_ID") == null || System.getenv("AWS_SECRET_ACCESS_KEY") == null ){
            throw new Exception("invalid credentials");
        }
        return new AWSCredentialsProviderChain(new EnvironmentVariableCredentialsProvider());
    }

    @Bean
    public AmazonSNS amazonSNS(AWSCredentialsProvider awsCredentialsProvider){
        return new AmazonSNSClient(awsCredentialsProvider);
    }
}
