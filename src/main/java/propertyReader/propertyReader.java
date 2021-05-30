package propertyReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propertyReader {

	private static propertyReader instance = null;
	private Properties props = null;

	private propertyReader() throws FileNotFoundException, IOException {
		this.props = new Properties();
		File fileUserProp = new File(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Config\\config.properties");
		props.load(new FileInputStream(fileUserProp));
		
		/*
		 * Properties props1 = new Properties(); props1.load(new
		 * FileInputStream(System.getProperty("user.dir") +
		 * "\\src\\test\\resources\\Config\\log4j.properties"));
		 */
	}

	public static propertyReader getInstance() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new propertyReader();
		return instance;
	}

	public String getBaseUri() {
		return props.getProperty("baseuri");
	}

	public String getAddPetResource() {
		return props.getProperty("add-resource");
	}

	public String getAddPetJson() {
		return props.getProperty("addjson");
	}
	
	public String getStoreInventory() 
	{
		return props.getProperty("getInventory");
		
	}
	public String getUpdatePetResource() {
		return props.getProperty("updatePet");
	}

	public String getDeletePetResource() {
		return props.getProperty("DeletePet");
	}

	
}
