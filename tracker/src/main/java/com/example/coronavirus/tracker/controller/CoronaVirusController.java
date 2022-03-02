package com.example.coronavirus.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.coronavirus.tracker.model.CoronaVirusModel;
import com.example.coronavirus.tracker.service.CoronaVirusService;

@Controller
public class CoronaVirusController {

	@Autowired
	CoronaVirusService coronaVirusService;

	@GetMapping("/")
	public String home(Model model) {
		List<CoronaVirusModel> allstatsCoronaVirusModels = coronaVirusService.getallstats();
		Integer totalcasestoday = allstatsCoronaVirusModels.stream().mapToInt(stat -> stat.getTotalnoofcases()).sum();
		model.addAttribute("casesData", allstatsCoronaVirusModels);
		model.addAttribute("Totalcasestoday", totalcasestoday);
		return "home";
	}
}
