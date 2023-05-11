package rw.project.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import rw.project.model.Bicycle;
import rw.project.repository.BicycleRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Getter
public class InitializationService {
    private final BicycleRepository repo;
    private final BicycleService service;
    private final List<Bicycle> initialBicycles;
    public InitializationService(@Value("${bicycles.initial}") String bicyclesPath,
                                 @Autowired ObjectMapper objectMapper,
                                 @Autowired BicycleRepository bicycleRepository,
                                 @Autowired BicycleService service) throws IOException {
        this.service = service;
        JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Bicycle.class);
        initialBicycles = objectMapper.readValue(new File(bicyclesPath), type);
        bicycleRepository.saveAll(initialBicycles);
        repo = bicycleRepository;
    }

}
