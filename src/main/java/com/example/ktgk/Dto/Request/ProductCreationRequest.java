package com.example.ktgk.Dto.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreationRequest {
    String name;
    double price;
    String description;
    LocalDate releaseDate;
}
