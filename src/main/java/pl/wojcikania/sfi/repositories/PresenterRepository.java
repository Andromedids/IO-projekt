package pl.wojcikania.sfi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wojcikania.sfi.domain.PresenterEntity;

@Repository
public interface PresenterRepository extends JpaRepository<PresenterEntity, Long> {
}
