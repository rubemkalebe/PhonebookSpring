package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Contact;
import com.example.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional(readOnly = false)
	public Contact add(Contact c) {
		return this.contactRepository.save(c);
	}
	
	@Transactional(readOnly = false)
	public void remove(Contact c) {
		this.contactRepository.delete(c);
	}
	
	@Transactional(readOnly = true)
	public List<Contact> findAll() {
		return this.contactRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Contact> findById(Integer id) {
		return this.contactRepository.findById(id); 
	}
	
}