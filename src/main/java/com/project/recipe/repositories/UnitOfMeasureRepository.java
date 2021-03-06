package com.project.recipe.repositories;


import com.project.recipe.models.UnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure,Long> {
    Optional<UnitOfMeasure> findByDescription(String description);

}
