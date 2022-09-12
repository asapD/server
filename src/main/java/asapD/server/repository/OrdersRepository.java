package asapD.server.repository;

import asapD.server.domain.Member;
import asapD.server.domain.Orders;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

  List<Orders> findAllByMember(Member member);
}
