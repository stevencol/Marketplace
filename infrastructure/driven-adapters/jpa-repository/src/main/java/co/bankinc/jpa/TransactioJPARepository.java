package co.bankinc.jpa;

import co.bankinc.jpa.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactioJPARepository extends JpaRepository<TransactionEntity, Long> {


}
