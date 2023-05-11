package rw.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rw.project.dto.BicycleDto;
import rw.project.dto.post.BicycleDto_POST;
import rw.project.dto.put.BicycleDto_PUT;
import rw.project.service.BicycleService;

import java.util.List;

@RestController
@RequestMapping("bicycles")
@RequiredArgsConstructor
public class BicycleController {

    private final BicycleService bicycleService;

    @GetMapping("/{id}")
    public BicycleDto getBicycleById(@Valid @PathVariable long id){
        return bicycleService.getBicycleById(id);
    }

    @GetMapping
    public List<BicycleDto> getBicycles(){
        return bicycleService.getBicycles();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BicycleDto createBicycle(@Valid @RequestBody BicycleDto_POST bicycleDto_post) {
        return bicycleService.createBicycle(bicycleDto_post);
    }

    @PutMapping("/{id}")
    public BicycleDto updateBicycle(@Valid @PathVariable Long id, @Valid @RequestBody BicycleDto_PUT bicycleDto_put) {
        return bicycleService.updateBicycle(id, bicycleDto_put);
    }

    @DeleteMapping("/{id}")
    public BicycleDto deleteBicycleById(@Valid @PathVariable long id){
        return bicycleService.deleteBicycleById(id);
    }

}
