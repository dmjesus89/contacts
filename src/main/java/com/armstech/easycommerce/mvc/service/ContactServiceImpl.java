package com.armstech.easycommerce.mvc.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.armstech.easycommerce.mvc.model.Contact;
import com.armstech.easycommerce.mvc.model.User;
import com.armstech.easycommerce.mvc.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactServiceImpl {

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private UserServiceImpl userService;

	public Contact save(String idUser, Contact contact) {

		User user = userService.getUserById(idUser);
		contact.setDtInsert(LocalDateTime.now());

		contact = contactRepository.save(contact);
		if (user.getContacts() == null) {
			user.setContacts(new ArrayList<>());
		}
		user.getContacts().add(contact);
		userService.save(user);
		return contact;
	}

	public Contact update(String idUser, String idContact, Contact contact) {
		User user = userService.getUserById(idUser);
		contact.setId(idContact);
		contact = contactRepository.save(contact);
		user.getContacts().add(contact);
		userService.save(user);
		return contact;
	}

	public void delete(String id) {
		Contact contact = getContactById(id);
		contactRepository.delete(contact);
	}

	public Contact getContactById(String id) {
		Optional<Contact> option = contactRepository.findById(id);
		if (null != option && option.get() != null) {
			return option.get();
		}
		throw new EmptyResultDataAccessException(1);
	}

	public List<Contact> getContacts(String idUser) {
		// List<Contact> listUser = (List<Contact>) contactRepository.findByUser();
		return null;
	}

}
