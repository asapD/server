package asapD.server.repository;


import asapD.server.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StoreRepository extends JpaRepository<Store, Long> {
    Page<Store> findAll(Pageable pageable);
}