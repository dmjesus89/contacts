package com.armstech.easycommerce.mvc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.armstech.easycommerce.mvc.model.Contact;
import com.armstech.easycommerce.mvc.model.User;
import com.armstech.easycommerce.mvc.repository.ContactRepository;
import com.armstech.easycommerce.mvc.repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContactRepository contactRepository;

	public void delete(String id) {
		User user = getUserById(id);
		userRepository.delete(user);
	}

	public User getUserById(String id) {
		Optional<User> option = userRepository.findById(id);
		if (null != option && option.get() != null) {
			return option.get();
		}
		throw new EmptyResultDataAccessException(1);
	}

	public List<User> getUsers() {
		List<User> listUser = (List<User>) userRepository.findAll();
		return listUser;
	}

	public User update(String id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	public User save(User user) {
		// TODO
		// VERIFICAR SE EMAIL JA EXISTE
		// VERIFICAR SENHA
		// CRIPTORRAGAR SENHA

		user.setActive(true);
		user.setDtInsert(LocalDateTime.now());

		return userRepository.save(user);
	}

	public Contact detailContact(String id) {
		Contact contact = (Contact) contactRepository.getDetailContactById(id);
		return contact;
	}

}
