package dataManager;

import java.util.ArrayList;
import dataInfo.*;

public interface CountryDAO {
	
	public ArrayList<Country> getCountries();
	public Country findCountryByName(String name);
	public Country findCountryByCode(String code);
	public boolean saveCountry(Country country);
//	public void closingConnection();
	
}
