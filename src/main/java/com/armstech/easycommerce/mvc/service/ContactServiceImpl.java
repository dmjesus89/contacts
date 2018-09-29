package com.armstech.easycommerce.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.armstech.easycommerce.mvc.model.Contact;
import com.armstech.easycommerce.mvc.repository.ContactRepository;

@Service
public class ContactServiceImpl {

	@Autowired
	private ContactRepository contactRepository;

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

	public List<Contact> getContacts() {
		List<Contact> listUser = (List<Contact>) contactRepository.findAll();
		return listUser;
	}

	public Contact update(String id, Contact contact) {
		contact.setId(id);
		return contactRepository.save(contact);
	}

	public Contact save(Contact contact) {
		return contactRepository.save(contact);
	}

}
