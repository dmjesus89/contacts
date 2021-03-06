package com.armstech.easycommerce.mvc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.armstech.easycommerce.mvc.model.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

	Contact findByName(String name);

	List<Contact> findByNameLikeOrderByNameAsc(String name);

	Contact getDetailContactById(String id);

	@Query("{'contact.notes': ?0}")
	List<Contact> findContactsByidUser(String idUser);

}
