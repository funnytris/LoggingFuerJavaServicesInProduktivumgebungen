package rw.project.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import rw.project.dto.BicycleDto;
import rw.project.dto.post.BicycleDto_POST;
import rw.project.dto.put.BicycleDto_PUT;
import rw.project.mapper.bicycle.put.UpdateBicycleWithDtoMapper;
import rw.project.model.Bicycle;
import rw.project.repository.BicycleRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class BicycleService {

    private final BicycleRepository bicycleRepository;
    private final ConversionService conversionService;
    private final UpdateBicycleWithDtoMapper updateBicycleWithDtoMapper;

    public BicycleDto getBicycleById(long id) throws NoSuchElementException {
        log.info("Using getBicycleById-method with parameter: ({})..", id);

        Optional<BicycleDto> bicycleOptional = bicycleRepository.findById(id)
                .map(bicycle -> conversionService.convert(bicycle, BicycleDto.class));

        if (bicycleOptional.isPresent()) {
            BicycleDto bicycleDto = bicycleOptional.get();
            log.debug("Result: ({})", bicycleDto);
            return bicycleDto;

        } else {
            log.debug("Result: Bicycle is not Present.");
            throw new NoSuchElementException("Getting Bicycle failed! Reason: This ID does not exist!");
        }
    }

    public List<BicycleDto> getBicycles() {
        log.info("Using getBicycles-method..");
        log.warn("Using Expensive Method! (getBicycles-method)..");

        List<BicycleDto> bicycleList = bicycleRepository.findAll().stream()
                .map(bicycle -> conversionService.convert(bicycle, BicycleDto.class))
                .toList();

        if (bicycleList.isEmpty()) {
            log.debug("Result: List is empty!");
        } else {
            log.debug("Result: {}", bicycleList);
        }

        return bicycleList;
    }

    public BicycleDto createBicycle(BicycleDto_POST bicycleDtoPost) throws NoSuchElementException {
        log.info("Using createBicycle-method with parameter: ({}, {})..", bicycleDtoPost.getPrice(), bicycleDtoPost.getDescription());
        log.warn("Changing Database by Using createBicycle-method!");

        return Optional.ofNullable(conversionService.convert(bicycleDtoPost, Bicycle.class))
                .map(bicycleRepository::save)
                .map(bicycle -> conversionService.convert(bicycle, BicycleDto.class))
                .orElseThrow(() -> new NoSuchElementException("Creating Bicycle failed! Reason: Wrong Types of given Attributes."));
    }

    public BicycleDto updateBicycle(Long id, BicycleDto_PUT bicycleDtoPut) throws NoSuchElementException {
        log.info("Using updateBicycle-method with parameter: ({}, {}, {})..", id, bicycleDtoPut.getPrice(), bicycleDtoPut.getDescription());
        log.warn("Changing Database by Using updateBicycle-method!");

        Optional<Bicycle> bicycleOptional = bicycleRepository.findById(id);

        if (bicycleOptional.isPresent()) {

            Bicycle updatedBicycle =
                    bicycleRepository.findById(id)
                            .map(bicycle -> updateBicycleWithDtoMapper.updateWithDto(bicycle, bicycleDtoPut)).get();

            if (bicycleDtoPut.getPrice() == 0.0) {
                updatedBicycle.setPrice(bicycleOptional.get().getPrice());
                log.trace("Price not changed.");
            }

            if (bicycleDtoPut.getDescription().isBlank()) {
                log.trace("Description not changed.");
                updatedBicycle.setDescription(bicycleOptional.get().getDescription());
            }

            bicycleRepository.save(updatedBicycle);

            BicycleDto newBicycleDto = conversionService.convert(updatedBicycle, BicycleDto.class);
            log.debug("Result: Changed Bicycle in Database: " + newBicycleDto);

            return newBicycleDto;
        }

        log.debug("Result: Bicycle is not Present.");
        throw new NoSuchElementException("Updating Bicycle failed! Reason: This ID does not exist!");
    }

    public BicycleDto deleteBicycleById(long id) throws NoSuchElementException {
        log.info("Using deleteBicycleById-method with parameter: (" + id + ")..");
        log.warn("Changing Database by Using deleteBicycleById-method!");

        Optional<Bicycle> optionalBicycle = bicycleRepository.findById(id);
        log.debug("Checking if Bicycle with id: " + id + " is Present..");

        if (optionalBicycle.isPresent()) {
            Bicycle deletedBicycle = optionalBicycle.get();

            bicycleRepository.deleteById(id);

            BicycleDto deletedBicycleDto = conversionService.convert(deletedBicycle, BicycleDto.class);
            log.debug("Result: DeletedBicycle: " + optionalBicycle.get());

            return deletedBicycleDto;
        }

        log.debug("Bicycle is not Present.");
        throw new NoSuchElementException("Deleting Bicycle failed! Reason: This ID does not exist!");
    }
}