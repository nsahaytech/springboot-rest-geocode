package com.ns.retailmgr.controller.exception;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestErrorMessage {

	private List<String> errors;

	public RestErrorMessage() {
	}

	public RestErrorMessage(List<String> errors) {
		this.errors = errors;
	}

	public RestErrorMessage(String error) {
		this(Collections.singletonList(error));
	}

	public RestErrorMessage(String... errors) {
		this(Arrays.asList(errors));
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
