package com.example.coronavirus.tracker.service;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.coronavirus.tracker.model.CoronaVirusModel;

@Service
public class CoronaVirusService {

	private static String URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	
	private List<CoronaVirusModel> allstats = new ArrayList<CoronaVirusModel>() ;
	
	public List<CoronaVirusModel> getallstats() {
		return allstats;
	}

	
	@PostConstruct
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
	 List<CoronaVirusModel> newstats = new ArrayList<CoronaVirusModel>() ;
	 System.out.println(newstats);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
		System.out.println(request);
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response);
		StringReader csvBodyReader = new StringReader(response.body());
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			CoronaVirusModel coronaVirusModel = new CoronaVirusModel();
			coronaVirusModel.setState(record.get("Province/State"));
			coronaVirusModel.setCountry(record.get("Country/Region"));
			coronaVirusModel.setTotalnoofcases(Integer.parseInt(record.get(record.size()-1)));
			newstats.add(coronaVirusModel);
		}
		
		this.allstats = newstats;
		        
	}
}
