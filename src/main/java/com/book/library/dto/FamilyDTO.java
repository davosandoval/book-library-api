package com.book.library.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class FamilyDTO implements BaseDTO {
	@ApiModelProperty(required = false, hidden = true)
	private Integer id;
	
	@ApiModelProperty(required = true)
	@NotBlank(message = "Genre/Family is mandatory")
	private String genre;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
}
