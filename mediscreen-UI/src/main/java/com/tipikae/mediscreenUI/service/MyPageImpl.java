/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Custom PageImpl for Jackson deserialization.
 * @author tipikae
 * @version 1.0
 *
 */
public class MyPageImpl<T> extends PageImpl<T> {

	private static final long serialVersionUID = 1L;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public MyPageImpl(@JsonProperty("content") List<T> content,
            @JsonProperty("pageable") JsonNode pageable,
            @JsonProperty("last") boolean last,
            @JsonProperty("totalPages") int totalPages,
            @JsonProperty("totalElements") Long totalElements,
            @JsonProperty("sort") JsonNode sort,
            @JsonProperty("first") boolean first,
            @JsonProperty("size") int size,
            @JsonProperty("number") int number,
            @JsonProperty("numberOfElements") int numberOfElements,
            @JsonProperty("empty") boolean empty) {

        super(content, PageRequest.of(number, size), totalElements);
    }

	public MyPageImpl(List<T> content) {
		super(content);
	}

	public MyPageImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}
	
	public MyPageImpl() {
		super(new ArrayList<>());
	}
}
