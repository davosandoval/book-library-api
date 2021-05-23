/**
 * 
 */
package com.book.library.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author davosandoval
 *
 */
public class BookDTO implements BaseDTO {
	@ApiModelProperty(required = false, hidden = true)
	private Integer id;
	
	@ApiModelProperty(required = true)
	@NotBlank(message = "Title is mandatory")
	private String title;
	
	@ApiModelProperty(required = true)
	@NotBlank(message = "Author is mandatory")
	private String author;
	
	@ApiModelProperty(required = false, hidden = true)
	private String family;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the family
	 */
	public String getFamily() {
		return family;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setFamily(String family) {
		this.family = family;
	}

}
