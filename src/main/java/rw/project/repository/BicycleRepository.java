package rw.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.project.model.Bicycle;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Long> {
}
