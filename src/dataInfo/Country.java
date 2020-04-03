package dataInfo;

public class Country {

	//Private variables
	//Mandatory variables
	private String code = "";
	private String name = "";
	private Continent continent = null; 
	private float surfaceArea = 0;
	//optional variable
	private String headOfState = "";

	//Private constructor so there is no instances of Country
	//without using the Builder class
	private Country(Builder builder) {
		this.code = builder.code;
		this.name = builder.name;
		this.continent = builder.continent;
		this.surfaceArea = builder.surfaceArea;
	}

	@Override
	public String toString() {
		return "Country code = "+code+ ", Name = "+name+",Continent =  "+continent+", Surface Area = "
				+surfaceArea+", Head of State = " +headOfState+"";
	}

	public static class Builder {
		//Private variables
		//Mandatory variables
		private String code = "";
		private String name = "";
		private Continent continent = null; 
		private float surfaceArea = 0;
		//optional variable
		private String headOfState = "";

		//builder constructor receiving mandatory variables
		public Builder (String code, String name, Continent continent, float srufaceArea) {
			this.code = code;
			this.name = name;
			this.continent = continent;
			this.surfaceArea = srufaceArea;
		}

		//method to populate the optional field
		// Which also returns an instance of builder
		public Builder headOfState(String headOfState) {
			return this;
		}

		//method to return an instance of Country using the builder
		public Country countryBuilder() {
			return new Country(this);
		}
	}

}
