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
import com.armstech.easycommerce.mvc.model.filter.ContactFilter;
import com.armstech.easycommerce.mvc.service.ContactServiceImpl;

@RestController
@RequestMapping("/contacts")
public class ContactResource {

	@Autowired
	private ContactServiceImpl userService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Contact> save(@Valid @RequestBody Contact user, HttpServletResponse response) {
		user = userService.save(user);
		publisher.publishEvent(new ResourceCreateEvent(this, response, user.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Contact> update(@PathVariable String id, @RequestBody Contact user) {
		Contact userUpdated = userService.update(id, user);
		return ResponseEntity.ok(userUpdated);
	}

	@GetMapping
	public ResponseEntity<List<Contact>> getContacts() {
		List<Contact> listUser = userService.getContacts();
		if (!listUser.isEmpty()) {
			return ResponseEntity.ok(listUser);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{name}")
	public ResponseEntity<List<Contact>> getContacts(@PathVariable String name) {
		List<Contact> listUser = userService.getContacts();
		if (!listUser.isEmpty()) {
			return ResponseEntity.ok(listUser);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable String id) {
		Contact user = userService.getContactById(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		userService.delete(id);
	}

}
