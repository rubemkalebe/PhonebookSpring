package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Contact;
import com.example.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final String MSG_SUCESS_INSERT = "Contact inserted successfully.";
	private static final String MSG_SUCESS_UPDATE = "Contact changed successfully.";
	private static final String MSG_SUCESS_DELETE = "Contact removed successfully.";
	private static final String MSG_ERROR = "Error.";
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping
	public String index(Model model) {
		List<Contact> contacts = this.contactService.findAll();
		model.addAttribute("listContact", contacts);
		return "contact/index";
	}
	
	@GetMapping("/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		if(id != null) {
			Contact c = this.contactService.findById(id).get();
			model.addAttribute("contact", c);
		}
		return "contact/show";
	}
	
	@GetMapping(value = "/new")
	public String create(Model model, @ModelAttribute Contact c) {
		model.addAttribute("contact", c);
		return "contact/form";
	}
	
	@PostMapping
	public String create(@Valid @ModelAttribute Contact contact, 
			BindingResult result, RedirectAttributes redirectAttributes) {
		Contact c = null;
		try {
			c = this.contactService.add(contact);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_INSERT);
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		
		return "redirect:/contacts/" + c.getId();
	}
	
	@GetMapping("/{id}/edit")
	public String update(Model model, @PathVariable("id") Integer id) {
		try {
			if(id != null) {
				Contact c = this.contactService.findById(id).get();
				model.addAttribute("contact", c);
			}
		} catch(Exception e) {
			throw new ServiceException(e.getMessage());
		}
		return "contact/form";
	}
	
	@PutMapping
	public String update(@Valid @ModelAttribute Contact contact,
			BindingResult result, RedirectAttributes redirectAttributes) {
		Contact c = null;
		try {
			c = this.contactService.add(contact);
			redirectAttributes.addFlashAttribute("success", MSG_SUCESS_UPDATE);
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			e.printStackTrace();
		}
		
		return "redirect:/contacts/" + c.getId();
	}
	
	@RequestMapping("/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		try {
			if(id != null) {
				Contact c = this.contactService.findById(id).get();
				this.contactService.remove(c);
				redirectAttributes.addFlashAttribute("success", MSG_SUCESS_DELETE);
			}
		} catch(Exception e) {
			redirectAttributes.addFlashAttribute("error", MSG_ERROR);
			throw new ServiceException(e.getMessage());
		}
		return "redirect:/contacts/";
	}
	
}
