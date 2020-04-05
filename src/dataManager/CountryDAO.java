package dataManager;

import java.util.ArrayList;
import dataInfo.*;

public interface CountryDAO {
	
	//Mandatory methods according requirements that need to be implemented
	public ArrayList<Country> getCountries();
	public Country findCountryByName(String name);
	public Country findCountryByCode(String code);
	public boolean saveCountry(Country country);
//	public void closingConnection();
	
}
