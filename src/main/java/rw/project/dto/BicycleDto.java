package rw.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BicycleDto {

    @NotNull
    private Long id;
    @NotNull
    private Double price;
    @NotBlank
    private String description;
}
