package nc.tunilog.magasinMS.services;

import nc.tunilog.magasinMS.models.Magasin;
import nc.tunilog.magasinMS.repositories.MagasinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagasinService {
    @Autowired
    private MagasinRepository magasinRepository;
    public List<Magasin> allMagasins() {
        return magasinRepository.findAll();
    }
    public Magasin newMagasin(Magasin magasin) {
        return magasinRepository.save(magasin);
    }
    public Magasin updateMagasin(Magasin updatedMagasin) {
        return magasinRepository.findById(updatedMagasin.getId())
                .map(
                        /*
                        Si un magasin est trouvé par findById, on l’appelle existingMagasin, puis on exécute le code suivant :
                        existingMagasin -> { .. souhaite faire avec existingMagasin .. } c’est une expression lambda, elle simplifie la syntaxe pour passer une fonction anonyme.
                        */
                    existingMagasin -> {
                        existingMagasin.setName(updatedMagasin.getName());
                        existingMagasin.setAdress(updatedMagasin.getAdress());
                        return magasinRepository.save(existingMagasin);// Enregistrer le magasin mis à jour
                    }
                )
                .orElseThrow(() -> new RuntimeException("Magasin avec l'ID " + updatedMagasin.getId() + " n'existe pas."));
    }
    public void delete(int idMagasin) {
        if (magasinRepository.existsById(idMagasin)) {
            magasinRepository.deleteById(idMagasin);
        } else {
            throw new RuntimeException("Magasin avec l'ID " + idMagasin + " n'existe pas.");
        }
    }

}
