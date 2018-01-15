package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

	public static Properties load(String fileName) throws IOException {
		try (InputStream input = new FileInputStream(fileName)) {
			Properties properties = new Properties();
			properties.load(input);
			return properties;
		}
	}

}
