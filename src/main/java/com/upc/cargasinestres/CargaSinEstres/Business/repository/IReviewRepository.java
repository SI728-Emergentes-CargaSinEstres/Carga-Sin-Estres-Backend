package com.upc.cargasinestres.CargaSinEstres.Business.repository;

import com.upc.cargasinestres.CargaSinEstres.Business.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The IReviewRepository interface extends JpaRepository for CRUD operations on Review entities.
 * It provides methods for querying reviews based on company id, and other criteria.
 * @author Grupo1
 * @version 1.0
 */
public interface IReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompanyId(Long companyId);
}
