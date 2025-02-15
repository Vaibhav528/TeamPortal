package com.vaibhav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vaibhav.binding.LoginForm;
import com.vaibhav.binding.SignUpForm;
import com.vaibhav.binding.UnlockForm;
import com.vaibhav.services.UserService;

@Controller
public class UserController 
{
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form, RedirectAttributes redirectAttributes) {
	    if (form.getEmail() == null || form.getEmail().isEmpty()) {
	        redirectAttributes.addFlashAttribute("errMsg", "Email cannot be empty.");
	        return "redirect:/signup";
	    }

	    boolean status = userService.signUp(form);
	    if (status) {
	        redirectAttributes.addFlashAttribute("successMsg", "Check your email.");
	    } else {
	        redirectAttributes.addFlashAttribute("errMsg", "Choose a unique email.");
	    }

	    return "redirect:/signup";
	}

//	
//	@GetMapping("/unlock")
//	public String unlockPage(@RequestParam("email") String email, Model model) {
//	    UnlockForm unlockForm = new UnlockForm();
//	    unlockForm.setEmail(email); // Set email from request param
//	    model.addAttribute("unlock", unlockForm); // Add object to model
//	    return "unlock"; // Return Thymeleaf template name
//	}

	@GetMapping("/signup")
	public String signUpPage(Model model)
	{
		
//		Checks if the Model already contains an attribute named "user".
//		This is useful when redirecting with flash attributes (like after form submission).
//		If "user" is already present (from a previous request), we don’t override it.

	    if (!model.containsAttribute("user"))
	    {
	        model.addAttribute("user", new SignUpForm());
	    }
	    return "signup";
	}

//	@PostMapping("/unlock")
//	public String unlockUserAccount(@ModelAttribute ("unlock") UnlockForm unlockForm , Model model,RedirectAttributes redirectAttributes) 
//	{
//		System.out.println(unlockForm);
//		if(unlockForm.getNewPwd().equals(unlockForm.getConfirmPwd()))
//		{
//			 boolean status = userService.unlockAccount(unlockForm);
//			 if(status)
//			 {
//				 redirectAttributes.addFlashAttribute("successMsg", "Your Account is Unlocked Sucessfully");
//				 
//			 }else {
//				 
//				 redirectAttributes.addFlashAttribute("errMsg", "Given Tem Pwd is incorrect ,check your email");
//			 }
//		}else
//			 redirectAttributes.addFlashAttribute("errMSg", "New PWd and Confirm should be same");
//		return "redirect:/unlock";
//	}
	
	
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlockForm, 
	                                RedirectAttributes redirectAttributes, Model model) 
	{
	    System.out.println("Unlock Form Data: " + unlockForm);  // Debugging

	    // Check if new and confirm password match
	    if (!unlockForm.getNewPwd().equals(unlockForm.getConfirmPwd())) {
	        model.addAttribute("errMsg", "New Password and Confirm Password must be the same.");
	        return "unlock";  // Stay on the page
	    }

	    // Call the service to unlock the account
	    boolean status = userService.unlockAccount(unlockForm);
	    
	    if (status) {
	        redirectAttributes.addFlashAttribute("successMsg", "Your account has been unlocked successfully.");
	        return "redirect:/login";  // Redirect to login after unlocking
	    } else {
	        model.addAttribute("errMsg", "Given Temporary Password is incorrect. Check your email.");
	        return "unlock";  // Stay on the same page
	    }
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam(value = "email", required = false) String email, Model model) 
	{
	    UnlockForm unlockForm = new UnlockForm();

	    if (email != null && !email.isEmpty()) {
	        unlockForm.setEmail(email);
	        System.out.println("Email passed to unlock page: " + email); // Debugging Log
	    } else {
	        System.out.println("Error: Email parameter is missing!");
	        model.addAttribute("error", "Email is required to unlock the account.");
	        return "error"; // Redirect to an error page if email is missing
	    }

	    model.addAttribute("unlock", unlockForm); // Add 'unlock' object to the model
	    return "unlock"; // Return the Thymeleaf template
	}
	
	

	@GetMapping("/login")
	public String loginPage(Model model) 
	{
		model.addAttribute("loginForm" , new LoginForm());
		return "login";
	}
	
	
	@PostMapping("/login")
	public String Login(@ModelAttribute("loginForm") LoginForm loginform , Model model) 
	{
		String status = userService.login(loginform);
		if(status .contains("sucess"))
		{
			return "redirect:/dashboard";
		}
		model.addAttribute(" " , status);
		
		return "login";
	}
	
	
	
	@GetMapping("/forgot")
	public String forgotPwdPage()
	{
		return "forgotPwd";
	}
	
	@PostMapping("/forgotPwd")
	public String forgotPwd(@RequestParam ("email") String email , Model model)
	{
		System.out.println(email);
		
	      boolean status =userService.forgotPwd(email);
	      
	      if(status)
	      {
	    	  model.addAttribute("successMsg", "Pwd Sent to your email");
	      }else {
	    	  model.addAttribute("errMsg", "Invalid email");
	      }
		
		return "forgotPwd";
	}
	
}
