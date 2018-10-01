package com.armstech.easycommerce.mvc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.armstech.easycommerce.mvc.model.Contact;
import com.armstech.easycommerce.mvc.model.User;

public interface UserRepository extends MongoRepository<User, String> {

	Contact findByName(String name);

	Contact findByEmail(String email);

	@Query("{'users.id': ?0}")
	List<Contact> findContactsById(String idUser);

}
