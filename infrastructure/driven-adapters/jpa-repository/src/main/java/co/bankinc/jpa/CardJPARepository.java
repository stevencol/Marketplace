package co.bankinc.jpa;

import co.bankinc.jpa.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardJPARepository extends JpaRepository<CardEntity, Long> {


    Optional<CardEntity> findByCardNumber(String cardNumber);
}
