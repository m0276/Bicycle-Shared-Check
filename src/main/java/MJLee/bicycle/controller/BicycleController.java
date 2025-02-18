package MJLee.bicycle.controller;

import MJLee.bicycle.dto.BicycleDto;

import MJLee.bicycle.service.BicycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/bicycle")
public class BicycleController {
    BicycleService bicycleService;

    @Autowired
    public BicycleController(BicycleService bicycleService) {
        this.bicycleService = bicycleService;
    }

    @GetMapping
    public ModelAndView findAll(Model model){
       model.addAttribute("stationsInformation",bicycleService.findAll());
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.setViewName("stations-information");

       return modelAndView;
    }

    @GetMapping("/{stationsName}")
    public ModelAndView findStationInfo(Model model, @PathVariable String stationsName){
        model.addAttribute("stationsInformation",bicycleService.findByName(stationsName));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("stations-information");

        return modelAndView;
    }
}
