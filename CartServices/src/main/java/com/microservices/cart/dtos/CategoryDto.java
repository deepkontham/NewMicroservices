package com.microservices.cart.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String categoryId;
    //    @NotBlank(message = "title is required !!")
    @Size(min = 4, message = "title must be of minimum 4 characters !!")
    private String title;

    @NotBlank(message = "description required !!")
    private String description;
    private String coverImage;

}