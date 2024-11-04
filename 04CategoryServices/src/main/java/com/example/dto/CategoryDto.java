package com.example.dto;


import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private Long categoryId;
	private String title;
	private String description;
	private Blob coverImage;

}
