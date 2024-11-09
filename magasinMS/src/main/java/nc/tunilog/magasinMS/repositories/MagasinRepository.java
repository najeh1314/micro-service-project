package nc.tunilog.magasinMS.repositories;

import nc.tunilog.magasinMS.models.Magasin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagasinRepository extends JpaRepository<Magasin, Integer> {
}
