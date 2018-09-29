package com.armstech.easycommerce.mvc.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Contact {

	@Id
	private String id;

	private String name;

	private String phone;

}
