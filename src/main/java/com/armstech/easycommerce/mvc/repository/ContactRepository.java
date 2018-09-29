package com.armstech.easycommerce.mvc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.armstech.easycommerce.mvc.model.Contact;

public interface ContactRepository extends MongoRepository<Contact, String> {

	Contact findByName(String name);

}
