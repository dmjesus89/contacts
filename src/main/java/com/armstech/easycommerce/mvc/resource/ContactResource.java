package com.armstech.easycommerce.mvc.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.armstech.easycommerce.api.event.ResourceCreateEvent;
import com.armstech.easycommerce.mvc.model.Contact;
import com.armstech.easycommerce.mvc.service.ContactServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/contacts")
public class ContactResource {

	@Autowired
	private ContactServiceImpl contactService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping("/{idUser}")
	public ResponseEntity<Contact> save(@PathVariable String idUser, @Valid @RequestBody Contact contact,
			HttpServletResponse response) {
		contact = contactService.save(idUser, contact);
		publisher.publishEvent(new ResourceCreateEvent(this, response, contact.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(contact);
	}

	@PutMapping("/{idUser}/{idContact}")
	public ResponseEntity<Contact> update(@PathVariable String idUser, @PathVariable String idContact,
			@RequestBody Contact user) {
		Contact userUpdated = contactService.update(idUser, idContact, user);
		return ResponseEntity.ok(userUpdated);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable String id) {
		Contact user = contactService.getContactById(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		contactService.delete(id);
	}

}
