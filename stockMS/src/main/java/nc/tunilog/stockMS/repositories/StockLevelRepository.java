package nc.tunilog.stockMS.repositories;

import nc.tunilog.stockMS.models.StockLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockLevelRepository extends JpaRepository<StockLevel, Integer> {
    Optional<StockLevel> findByProductIdAndMagasinId(Integer productId, Integer magasinId);
    List<StockLevel> findByMagasinId(Integer magasinId);

}
