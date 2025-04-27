package lk.ac.pdn;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class coronaVirus {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final String url = "https://www.worldometers.info/coronavirus/";
		
		CovidData globalData = scrapeGlobalData(url);
		System.out.println("Global Covid-19 Data: ");
		System.out.println(globalData.toString());
		
		
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

}
