package client;

import dataInfo.*;
import dataManager.*;

import java.util.ArrayList;
import java.util.Scanner;

import DBConn.*;
 
//App client class where CLI user interface will be hosted
public class MainApp {
	CountryDAO dao;
	String code;
	String name;
	Continent continent;
	float surfaceArea;
	String headOfState;

	public static void main (String []args) {

		new MainApp();
	}
	
	/*Constructor. as soon as is called 
	 * Establish the connection instantiating the connection
	 * through the MySqlCountryDao class (DAO pattern)
	 * also printing the menu for user*/
	public MainApp() {
		dao = new MySqlCountryDAO();
		menu();
	}
	
	//Method to offer menu to the user
	public void menu() {
		boolean connectionOpen = true;

		do {
			System.out.println("Welcome to World DataBase");
			System.out.println("Please choose one of the following option");
			System.out.println("Press 1 to Save a new country");
			System.out.println("Press 2 to to see all contries");
			System.out.println("Press 3 to find a country by is code");
			System.out.println("Press 4 to find a country by is name");
			System.out.println("Press 5 to exit");


			String userChoise = userInput();

			if (userChoise.equals("1")) {
				addCountry();
			} else if(userChoise.equals("2")) {
				getAllCountries();
			} else if(userChoise.equals("3")) {
				getCountryByCode();
			} else if(userChoise.equals("4")) {
				getCountryByName();
			} else if(userChoise.equals("5")) {
				exitProgram();
				connectionOpen = false;
			} else {
				System.out.println("Print one of the available options");
			}
		}while (connectionOpen);

	}

	//Method to get input from the user
	public String userInput() {

		String input = "";
		Scanner sc = new Scanner(System.in);

		try {
			input = sc.nextLine();
		} catch (Exception e) {
			e.fillInStackTrace();
		}
		return input;
	}
	
	
	public Country getCountryInfo() {
		System.out.println("Type the Code of the country");
		this.code = userInput();
		System.out.println("Type the Name of the country");
		this.name = userInput();
		System.out.println("Type the Continent of the country");
		this.continent = Continent.valueOf(userInput().toUpperCase().replace(" ", "_"));
		System.out.println("Type the Surface Area of the country");
		this.surfaceArea = Float.valueOf(userInput());
		System.out.println("Type the Head of State of the country");
		this.headOfState = userInput();

		Country country = new Country.Builder(code, name, continent, surfaceArea)
				.headOfState(headOfState).countryBuilder();

		return country;

	}


	//Method to allow user to save a country
	public boolean addCountry() {
		getCountryInfo();
		try{
			return dao.saveCountry(getCountryInfo());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	//Method for the user get all countries
	public ArrayList<Country> getAllCountries(){

		try {
			return dao.getCountries();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return dao.getCountries();
	}

	//Method to allow the user find  country by its code
	public Country getCountryByCode() {
		System.out.println("Please enter country's code");
		code = userInput();
		return dao.findCountryByCode(code);
	}

	//Method to allow the user find  country by its name
	public Country getCountryByName() {
		System.out.println("Please enter country's name");
		name = userInput();
		return dao.findCountryByName(name);
	}
	
	//Methos to allow user close connection
	public void exitProgram() {
		dao.closingConnection();
	}
}
