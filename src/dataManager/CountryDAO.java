package dataManager;

import java.util.ArrayList;
import dataInfo.*;

public interface CountryDAO {
	
	public ArrayList<Country> getCustomers();
	public Country findCountryByName();
	public Country findCountryByCode();
	public boolean saveCustomer(Country country);
}
