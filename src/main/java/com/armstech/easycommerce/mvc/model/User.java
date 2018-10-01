package com.armstech.easycommerce.mvc.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class User {

	@Id
	private String id;

	private String name;

	private List<Contact> contacts;

	private String email;

	private String password;

	private LocalDateTime dtInsert;

	private Boolean active;

	private LocalDateTime dtLastScheduleUpdated;

}
