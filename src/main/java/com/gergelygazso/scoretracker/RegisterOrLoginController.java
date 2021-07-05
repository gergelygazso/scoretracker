package com.gergelygazso.scoretracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gergelygazso.scoretracker.registration.RegistrationRequest;
import com.gergelygazso.scoretracker.registration.RegistrationService;


@Controller
@RequestMapping("/")
public class RegisterOrLoginController {
	
	RegistrationService registrationService;
	
	@Autowired
	public RegisterOrLoginController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@GetMapping
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping("registerorlogin")
	public String showRegisterOrLoginPage() {
		return "registerorlogin";
	}
	
	@GetMapping("showregistrationform")
	public String showRegistrationForm(Model model) {
		
		model.addAttribute("registrationrequest", new RegistrationRequest());
		
		return "registrationform";
	}
	
	@PostMapping("/processregistrationform")
	public String processRegistrationForm(@ModelAttribute("registrationrequest") RegistrationRequest request) {
		if(request==null) {
			System.out.println("NULL OBJECT");
		}else {
			System.out.println(request.getFirstName());
		}
		
		registrationService.register(request);
		return "redirect:/registerorlogin";
	}
	
}
