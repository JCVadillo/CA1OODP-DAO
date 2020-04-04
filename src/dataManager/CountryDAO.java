package dataManager;

import java.util.ArrayList;
import dataInfo.*;

public interface CountryDAO {
	
	public ArrayList<Country> getCustomers();
	public Country findCountryByName(String name);
	public Country findCountryByCode(String code);
	public boolean saveCustomer(Country country);
}
