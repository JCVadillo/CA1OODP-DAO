package dataManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dataInfo.Continent;
import dataInfo.Country;
import DBConn.*;

public class MySqlCountryDAO implements CountryDAO{

	/*calling the getInstance method from
	 *  DataSource to be able to initialized 
	 *  the data base connection*/
	
	
	DataSource dSource = DataSource.getInstance();


	/*Method to list all countries in the database*/
	@Override
	public ArrayList<Country> getCountries() {

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
				continent = Continent.valueOf(rs.getString(3).toUpperCase().replace(" ", "_"));
				surfaceArea = rs.getFloat(4);
				headOfState = rs.getString(5);
				//Creating Country by using the builder
				Country c = new Country.Builder(code, name, continent, surfaceArea).headOfState(headOfState).countryBuilder();

				countries.add(c);
				System.out.println(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return countries;
	}


	//method to find country in the database by passing its name as parameter
	@Override
	public Country findCountryByName(String name) {
		//Mandatory variables
		String code = "";
		Continent continent = null; // no need to create a variable for name because is the value we are passing 
		float surfaceArea = 0;
		//optional variable
		String headOfState = "";

		String query = "SELECT * FROM country WHERE name = " + name + ";";

		ResultSet rs = dSource.select(query);

		try {
			if(rs.next()) {

				code = rs.getString(1);
				name = rs.getString(2);
				continent = Continent.valueOf(rs.getString(3).toUpperCase().replace(" ", "_"));// still need to respect the columns according to the database
				surfaceArea = rs.getFloat(4);
				headOfState = rs.getString(5);

				//again using the builder to create the country to be retrieved 
				Country c = new Country.Builder(code, name, continent, surfaceArea).headOfState(headOfState).countryBuilder();
				
				System.out.println(c);
				return c;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	//method to find country in the database by passing its code as parameter
	@Override
	public Country findCountryByCode(String code) {
		//Mandatory variables
		String name = "";
		Continent continent = null; //same case but now we are passing code as parameter
		float surfaceArea = 0;
		//optional variable
		String headOfState = "";

		String query = "SELECT * FROM country WHERE code = " +code+ ";";

		ResultSet rs = dSource.select(query);

		try {
			if(rs.next()) {

				name = rs.getString(2);
				continent = Continent.valueOf(rs.getString(3).toUpperCase().replace(" ", "_"));
				surfaceArea = rs.getFloat(4);
				headOfState = rs.getString(5);
				Country c = new Country.Builder(code, name, continent, surfaceArea).headOfState(headOfState).countryBuilder();
				System.out.println(c);
				return c;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/*Method to add a new country to the data base, 
	 * it requires as parameter the country to be added
	 * and here is when the getters are going to be called*/
	@Override
	public boolean saveCountry(Country country) {

		String code  = country.getCode();
		String name = country.getName();
		Continent continent = country.getContinent();
		float surfaceArea = country.getSurfaceArea();
		String headOfState = country.getHeadOfState();

		String query = "INSERT INTO country (Code, Name, Continent, SurfaceArea, HeadOfState) VALUES ('"+code+"', '"+name+"', "
				+ "'"+continent+"', '"+surfaceArea+"','"+headOfState+"';";
		System.out.println(query);
		
		return dSource.save(query);
	}

//	@Override
////	public void closingConnection() {
////		dSource.closing();
////	}
	
	
}
