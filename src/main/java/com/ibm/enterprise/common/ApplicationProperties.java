package com.ibm.enterprise.common;

import java.io.InputStream;
import java.util.Properties;


public final class ApplicationProperties {

    private String mongodbHost;
    private int mongodbPort;
    private String mongodbDatabase;
    private String mongodbCollection;
    private String parameter1;
    private String parameter2;

	private static final String PROPERTIES_FILE = "../../conf/config.properties";
    private static ApplicationProperties appProperties = new ApplicationProperties();

    private ApplicationProperties() {
       initializeApplicationProperties();
    }


    public static ApplicationProperties getInstance() {
        return appProperties;
    }


    private void initializeApplicationProperties() {
      //  FileInputStream inputStream = null;
        try {
        	
        	InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream(PROPERTIES_FILE);
      	
            final Properties properties = new Properties();
      
            properties.load(inputStream);
            mongodbHost = properties.getProperty("mongodbHost");
            mongodbPort = Integer.parseInt(properties.getProperty("mongodbPort"));
            mongodbDatabase = properties.getProperty("mongodbDatabase");
            mongodbCollection = properties.getProperty("mongodbCollection");
            parameter1 = properties.getProperty("parameter1");
            parameter2 = properties.getProperty("parameter2");
        } catch (Exception e) {
            
        } finally {

        }
    }


    public String getMongodbDatabase() {
        return mongodbDatabase;
    }

    public String getMongodbCollection() {
		return mongodbCollection;
	}

    public String getMongodbHost() {
        return mongodbHost;
    }

    public int getMongodbPort() {
        return mongodbPort;
    }


	public String getParameter1() {
		return parameter1;
	}


	public String getParameter2() {
		return parameter2;
	}
 }