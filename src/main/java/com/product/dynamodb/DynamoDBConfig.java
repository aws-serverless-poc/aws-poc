package com.product.dynamodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {

	@Value("${aws.access.key}")
	private String awsAccessKey;
	@Value("${aws.secret.key}")
	private String awsSecretKey;
	@Value("${aws.region}")
	private String awsRegion;
	@Value("${aws.dynamodb.end-point.url}")
	private String awsDynamodbEndPoint;

	@Bean
	public DynamoDBMapper mapper() {
		return new DynamoDBMapper(dynamoDbConfiguration());
	}

	private AmazonDynamoDB dynamoDbConfiguration() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsDynamodbEndPoint, awsRegion))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
				.build();
	}

}
