package com.chriswcg.coronavirustracker.controllers;

import com.chriswcg.coronavirustracker.models.LocationStats;
import com.chriswcg.coronavirustracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//@RestController means all method in this class need to return rest response(JSON format)
// @Controller here makes all method returning names of templates
@Controller
public class HomeController {

    @Autowired
    CoronavirusDataService coronavirusDataService;
    //return a name which pointed to a template in templates/ folder
    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronavirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
