package lk.ac.pdn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class coronaVirus {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final String url = "https://www.worldometers.info/coronavirus/";
		
		CovidData globalData = scrapeGlobalData(url);
		System.out.println("Global Covid-19 Data: ");
		System.out.println(globalData.toString());
		
		List<CountryData> countryData = scrapeCountryData(url);
		System.out.println("\nTop 20 Countries by cases: ");
		countryData.stream().limit(20).forEach(System.out::println);
		
		
	}
	
	private static CovidData scrapeGlobalData(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		
		CovidData data = new CovidData();
		data.setTotalCases(doc.select("div.maincounter-number > span").get(0).text());
		data.setTotalDeaths(doc.select("div.maincounter-number > span").get(1).text());
		data.setTotalRecovered(doc.select("div.maincounter-number > span").get(2).text());
		data.setActiveCases(doc.select("div.number-table-main").get(0).text());
		data.setClosedCases(doc.select("div.number-table-main").get(1).text());
		
		return data;
	}
	
	private static List<CountryData>  scrapeCountryData(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		
		List<CountryData> countries = new ArrayList<>();
		Elements rows = doc.select("table#main_table_countries_today tbody tr");
		
		for (Element row : rows) {
			Elements cols = row.select("td");
			if(cols.size()>1) {
				CountryData country = new CountryData();
				country.setName(cols.get(1).text());
				country.setTotalCases(cols.get(2).text());
				country.setNewCases(cols.get(3).text());
				country.setTotalDeaths(cols.get(4).text());
				country.setNewDeaths(cols.get(5).text());
				country.setTotalRecovered(cols.get(6).text());
				
				countries.add(country);
			}
		}
		return countries;
	}
	//structured our downloaded data
	static  class CovidData{
		private String totalCases;
		private String totalDeaths;
		private String totalRecovered;
		private String activeCases;
		private String closedCases;
		
		
		//getters and setters
		public String getTotalCases() {
			return totalCases;
		}
		public void setTotalCases(String totalCases) {
			this.totalCases = totalCases;
		}
		public String getTotalDeaths() {
			return totalDeaths;
		}
		public void setTotalDeaths(String totalDeaths) {
			this.totalDeaths = totalDeaths;
		}
		public String getTotalRecovered() {
			return totalRecovered;
		}
		public void setTotalRecovered(String totalRecovered) {
			this.totalRecovered = totalRecovered;
		}
		public String getActiveCases() {
			return activeCases;
		}
		public void setActiveCases(String activeCases) {
			this.activeCases = activeCases;
		}
		public String getClosedCases() {
			return closedCases;
		}
		public void setClosedCases(String closedCases) {
			this.closedCases = closedCases;
		}
		
		public String toString() {
			return String.format(
					"Total Cases: %s\nTotal Deaths: %s\nTotal Recovered: %s\n"+
			"Active Cases: %s\nClosed Cases:%s",totalCases,totalDeaths,totalRecovered,activeCases, closedCases);
		}
		
	}
	
	static class CountryData{
		private String name;
		private String totalCases;
		private String newCases;
		private String totalDeaths;
		private String newDeaths;
		private String totalRecovered;
		
		//getter and setters
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTotalCases() {
			return totalCases;
		}
		public void setTotalCases(String totalCases) {
			this.totalCases = totalCases;
		}
		public String getNewCases() {
			return newCases;
		}
		public void setNewCases(String newCases) {
			this.newCases = newCases;
		}
		public String getTotalDeaths() {
			return totalDeaths;
		}
		public void setTotalDeaths(String totalDeaths) {
			this.totalDeaths = totalDeaths;
		}
		public String getNewDeaths() {
			return newDeaths;
		}
		public void setNewDeaths(String newDeaths) {
			this.newDeaths = newDeaths;
		}
		public String getTotalRecovered() {
			return totalRecovered;
		}
		public void setTotalRecovered(String totalRecovered) {
			this.totalRecovered = totalRecovered;
		}
		
		public String toString() {
			return String.format("%-20s | Cases: %10s | New: %s | Deaths: %-10s | Recovered: %-10s",name,totalCases,newCases,totalDeaths,totalRecovered);
		}
		
		
	}
	
	

}
