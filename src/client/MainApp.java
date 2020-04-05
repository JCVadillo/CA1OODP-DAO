package client;

import dataInfo.*;
import dataManager.*;

import java.util.Scanner;

import DBConn.*;

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
	
	public MainApp() {
		dao = new MySqlCountryDAO();
		menu();
	}
	
	public void menu() {
		boolean connectionOpen = true;
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
	
	public boolean addCountry() {
		return dao.saveCountry(getCountryInfo());
	}
	
}
