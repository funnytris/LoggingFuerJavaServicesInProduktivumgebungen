package rw.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rw.project.dto.post.BicycleDto_POST;
import rw.project.dto.put.BicycleDto_PUT;
import rw.project.model.Bicycle;
import rw.project.repository.BicycleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class BicycleControllerTest {

    @Autowired
    BicycleController controller;
    @Autowired
    BicycleRepository repo;

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
        assertEquals(199.99 , controller.getBicycleById(1).getPrice());
    }

    @Test
    void getBicycles() {
        assertEquals(repo.findAll().size(), (long) controller.getBicycles().size());
    }

    @Test
    void createBicycle() {
        BicycleDto_POST bicycleDto_post = new BicycleDto_POST(100.00,"ControllerCreationTest");
        int counterbefore = repo.findAll().size();
        controller.createBicycle(bicycleDto_post);
        int counterafter = repo.findAll().size();
        assertEquals(counterbefore+1, counterafter);
    }

    @Test
    void updateBicycle() {
        BicycleDto_PUT bicycleDto_put = new BicycleDto_PUT(50.0, "ControllerUpdateTest");
        controller.updateBicycle(3L, bicycleDto_put);
        assertEquals("ControllerUpdateTest" , repo.findById(3L).get().getDescription());
    }

    @Test
    void deleteBicycleById() {
        controller.deleteBicycleById(2L);
        assertNotNull(repo.findById(2L));
    }
}