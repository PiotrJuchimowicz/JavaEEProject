package com.company.project.Controllers;

import com.company.project.registration.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserDetailsManager userDetailsManager;


	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder //to żeby walidacja działała
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/registration")
	public String showMyLoginPage(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());
		
		return "registration-form";
		
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
				BindingResult theBindingResult,
				Model theModel) {
						
		String userName = theCrmUser.getUserName();
		
		logger.info("Rejestracja użytkownika: " + userName);


		if (theBindingResult.hasErrors()) {

			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "Pole login/hasło nie moze być puste.");

			logger.warning("Pole login/hasło nie moze być puste.");
			return "registration-form";	
		}
		
		// check the database if user already exists
		boolean userExists = doesUserExist(userName);
		
		if (userExists) {
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "Użytkownik o podanym id istnieje");

			logger.warning("Użytkownik o podanym id istnieje.");
			return "registration-form";			
		}
		

        String encodedPassword = passwordEncoder.encode(theCrmUser.getPassword());

		// domyslna rola
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CLIENT");


        User tempUser = new User(userName, encodedPassword, authorities);
        //zapisanie w bazie
        userDetailsManager.createUser(tempUser);		
		
        logger.info("Udało sie stworzyc użytkownika o id: " + userName);





        return "registration-confirmation";		
	}
	
	private boolean doesUserExist(String userName) {
		
		logger.info("Sprawdzenie czy istnieje użytkownik o id: " + userName);
		
		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);
		
		logger.info("Użytkownik o id: " + userName + ", istnieje: " + exists);
		
		return exists;
	}

}
