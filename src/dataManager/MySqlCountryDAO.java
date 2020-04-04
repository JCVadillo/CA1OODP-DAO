package dataManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataInfo.Continent;
import dataInfo.Country;
import DBConn.*;

public class MySqlCountryDAO implements CountryDAO{

	private DataSource dSource = DataSource.getInstance();
	
	
	@Override
	public ArrayList<Country> getCustomers() {
		ArrayList<Country> countries = new ArrayList<Country>();
		//Mandatory variables
		String code = "";
		String name = "";
		Continent continent = null; 
		float surfaceArea = 0;
		//optional variable
		String headOfState = "";

		String query = "SELECT * FROM country;";

		ResultSet rs = dSource.select(query);

		try {
			while(rs.next()) {
				code = rs.getString(1);
				name = rs.getString(2);
				continent = Continent.valueOf(rs.getString(3));
				surfaceArea = rs.getFloat(4);
				Country c = new Country.Builder(code, name, continent, surfaceArea).headOfState(headOfState).countryBuilder();

				countries.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return countries;
	}

	@Override
	public Country findCountryByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country findCountryByCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveCustomer(Country country) {
		// TODO Auto-generated method stub
		return false;
	}

}
