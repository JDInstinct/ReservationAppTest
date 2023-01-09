package com.ibm.cube.api;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.ObjectUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

@SpringBootApplication
@EnableMongoRepositories("com.ibm.cube.api.repository")
public class ApiApplication {

	public static void main(String[] args) {
		SSLContextHelper.setSslProperties();
		SpringApplication.run(ApiApplication.class, args);
	}

	@Value("${spring.data.mongodb.url}")
	private String uri;

	@Value("${spring.data.mongodb.database}")
	private String db;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public MongoClient mongo() throws Exception {
		final ConnectionString connectionString = new ConnectionString(uri);
		final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.applyToSslSettings(builder -> builder.enabled(true).invalidHostNameAllowed(true))
				.build();
		return MongoClients.create(mongoClientSettings);
	}
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), db);
	}

	protected static class SSLContextHelper {

		//private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		private static final String DEFAULT_SSL_CERTIFICATE = "5cb6eb86-ae1c-11e9-99c9-6a007ab2fc0b.pem";
		private static final String SSL_CERTIFICATE = "sslCertificate";
		private static final String KEY_STORE_TYPE = "JKS";
		private static final String KEY_STORE_PROVIDER = "SUN";
		private static final String KEY_STORE_FILE_PREFIX = "sys-connect-via-ssl-test-cacerts";
		private static final String KEY_STORE_FILE_SUFFIX = ".jks";
		private static final String DEFAULT_KEY_STORE_PASSWORD = "changeit";
		private static final String SSL_TRUST_STORE = "javax.net.ssl.trustStore";
		private static final String SSL_TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
		private static final String SSL_TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";

		private static void setSslProperties() {

			try {
				String sslCertificate= System.getProperty(SSL_CERTIFICATE);
				if(ObjectUtils.isEmpty(sslCertificate)) {
					sslCertificate= DEFAULT_SSL_CERTIFICATE;
				}
				//logger.info(" ssl certificate path {}",sslCertificate);
				System.setProperty(SSL_TRUST_STORE, createKeyStoreFile(sslCertificate));
				System.setProperty(SSL_TRUST_STORE_TYPE, KEY_STORE_TYPE);
				System.setProperty(SSL_TRUST_STORE_PASSWORD, DEFAULT_KEY_STORE_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		private static String createKeyStoreFile(String sslCertificate) throws Exception {
			return createKeyStoreFile(createCertificate(sslCertificate)).getPath();
		}

		private static X509Certificate createCertificate(String sslCertificate) throws Exception {
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");


			ClassPathResource cpr = new ClassPathResource(sslCertificate);

			try (InputStream certInputStream = cpr.getInputStream()) {
				return (X509Certificate) certFactory.generateCertificate(certInputStream);
			}
		}

		private static File createKeyStoreFile(X509Certificate rootX509Certificate) throws Exception {
			File keyStoreFile = File.createTempFile(KEY_STORE_FILE_PREFIX, KEY_STORE_FILE_SUFFIX);
			try (FileOutputStream fos = new FileOutputStream(keyStoreFile.getPath())) {
				KeyStore ks = KeyStore.getInstance(KEY_STORE_TYPE, KEY_STORE_PROVIDER);
				ks.load(null);
				ks.setCertificateEntry("rootCaCertificate", rootX509Certificate);
				ks.store(fos, DEFAULT_KEY_STORE_PASSWORD.toCharArray());
			}
			return keyStoreFile;
		}

	}
}
