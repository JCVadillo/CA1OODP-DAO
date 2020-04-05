package dataInfo;

//Enum continent to restrict the variables, 
//String associated with the values to pass to the data base
public enum Continent {
	
	ASIA("Asia"),
	EUROPE("Europe"),
	NORTH_AMERICA("North America"),
	AFRICA("Africa"),
	OCEANIA("Oceania"),
	ANTARCTICA("Antarctica"),
	SOUTH_AMERICA("South America");
	
	private String continent = "";
	
	private Continent (String continent) {
		this.continent = continent;
	}
	
	@Override
	public String toString() {
		return this.continent;
	}

}
