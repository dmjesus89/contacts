package com.armstech.easycommerce.mvc.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Contact {

	@Id
	private String id;

	private String name;

	@NotNull
	private List<String> phoneNumber;

	private String notes;

	private String email;

	private LocalDateTime dtInsert;

	private Boolean active;

}
