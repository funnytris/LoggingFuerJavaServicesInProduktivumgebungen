package rw.project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rw.project.dto.BicycleDto;
import rw.project.dto.post.BicycleDto_POST;
import rw.project.dto.put.BicycleDto_PUT;
import rw.project.model.Bicycle;
import rw.project.repository.BicycleRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BicycleServiceTest {

    @Autowired
    BicycleRepository repo;
    @Autowired
    BicycleService service;

    @BeforeEach
    void setUp() {
        Bicycle bicycle1 = new Bicycle(1L, 199.99, "Klapprad Compact black");
        Bicycle bicycle2 = new Bicycle(2L, 638.99, "Hybrid Pro Trekking-bike");
        Bicycle bicycle3 = new Bicycle(3L, 559.99, "City-Bike Supreme");
        repo.save(bicycle1);
        repo.save(bicycle2);
        repo.save(bicycle3);
    }
    @Test
    void getBicycleById() {
        assertEquals(199.99, service.getBicycleById(1).getPrice());
        assertEquals("Hybrid Pro Trekking-bike", service.getBicycleById(2).getDescription());
    }

    @Test
    void getBicycles() {
        long i = service.getBicycles().stream().spliterator().estimateSize();
        assertEquals(repo.count(), i);
    }

    @Test
    void createBicycle() {
        BicycleDto_POST bicycleDto_POST = new BicycleDto_POST(500.00, "ServiceCreateTestBicycle");

        int sizeBefore = repo.findAll().size();
        BicycleDto bicycleDto = service.createBicycle(bicycleDto_POST);
        int sizeAfter = repo.findAll().size();

        assertEquals(sizeBefore + 1, sizeAfter);
        assertTrue(repo.findAll().stream()
                .anyMatch(bicycle -> bicycleDto.getDescription().equals(bicycle.getDescription())));
        assertNotNull(repo.findById(bicycleDto.getId()));
    }

    @Test
    void updateBicycle() {
        BicycleDto_PUT bicycleDto_PUT = new BicycleDto_PUT(299.99, "ServiceUpdateTestBicycle");

        service.updateBicycle(1L, bicycleDto_PUT);

        assertEquals(299.99, service.getBicycleById(1L).getPrice());
        assertEquals("ServiceUpdateTestBicycle", service.getBicycleById(1L).getDescription());

    }

    @Test
    void deleteBicycleById() {
        service.deleteBicycleById(3);

        assertFalse(repo.findById(3L).isPresent());

    }

    @Test
    void getBicycleByIdThrowsException() {
        assertThrows(Exception.class, () -> service.getBicycleById(1000L));
    }
    @Test
    void deleteBicycleThrowsException() {
        assertThrows(Exception.class, () -> service.deleteBicycleById(1000L));
    }

    @Test
    void updateBicycleThrowsException() {
        BicycleDto_PUT bicycleDto_PUT = new BicycleDto_PUT();
        assertThrows(Exception.class, () -> service.updateBicycle(1000L, bicycleDto_PUT));
    }

    @Test
    void createBicycleThrowsException() {
        BicycleDto_POST bicycleDto_POST = new BicycleDto_POST();
        assertThrows(Exception.class, () -> service.createBicycle(bicycleDto_POST));
    }
}