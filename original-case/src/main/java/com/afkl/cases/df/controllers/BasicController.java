package com.afkl.cases.df.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.afkl.cases.df.model.Fare;
import com.afkl.cases.df.model.GetFareDetails;
import com.afkl.cases.df.service.MyService;

@Controller
@RequestMapping(value="/home")
public class BasicController {
	
	@Autowired
	private MyService service; 

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mv) {
		
		mv.setViewName("home");
		
		return mv;
	}
	
	@RequestMapping(value="/getdetails",method=RequestMethod.GET)
	 public ModelAndView getFareDetails(@RequestParam("from") String from,@RequestParam("to") String to)
	 {
		Map<String, String> map = service.getFare(from, to);
		
		for (Map.Entry<?,?> entry : map.entrySet()) 
		{
            System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue());
            
            
		}
		
		Fare myFare = new Fare();
		//myFare.setAmount(Double.parseDouble(map.get("amount")));
		myFare.setTotalamount(String.valueOf(map.get("amount")));
		myFare.setCurrency(map.get("currency"));
		myFare.setOrigin(map.get("origin"));
		myFare.setDestination(map.get("destination"));
	    
		//ModelAndView mv = new ModelAndView();
		/*for (Map.Entry<?,?> entry : map.entrySet())  
		{
			model.addAttribute(String.valueOf(entry.getKey()), entry.getValue());
		}*/
		
		
		ModelAndView modelAndView = new ModelAndView("faredetails");
	    modelAndView.addObject("myFare", myFare);
	    return modelAndView;
	 }

}
