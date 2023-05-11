package rw.project.dto.put;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BicycleDto_PUT {
    @NotNull
    private Double price;
    @NotBlank
    private String description;
}
