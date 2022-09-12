package asapD.server.repository;

import asapD.server.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
