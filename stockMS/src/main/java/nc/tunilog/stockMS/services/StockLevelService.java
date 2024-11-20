package nc.tunilog.stockMS.services;

import nc.tunilog.stockMS.models.StockLevel;
import nc.tunilog.stockMS.repositories.StockLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockLevelService {

    @Autowired
    private StockLevelRepository stockLevelRepository;

    // Ajouter ou mettre à jour un niveau de stock
    public StockLevel saveOrUpdateStockLevel(StockLevel stockLevel) {
        return stockLevelRepository.save(stockLevel);
    }

    // Trouver un niveau de stock par ID
    public Optional<StockLevel> getStockLevelById(Integer id) {
        return stockLevelRepository.findById(id);
    }

    // Obtenir les niveaux de stock pour un magasin donné
    public List<StockLevel> getStockLevelsByMagasinId(Integer magasinId) {
        return stockLevelRepository.findByMagasinId(magasinId);
    }

    // Supprimer un niveau de stock
    public boolean deleteStockLevel(Integer id) {
        if (stockLevelRepository.existsById(id)) {
            stockLevelRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Approvisionner un produit dans un magasin
    public Optional<StockLevel> approvisionner(Integer productId, Integer magasinId, Integer quantity) {
        Optional<StockLevel> existingStock = stockLevelRepository.findByProductIdAndMagasinId(productId, magasinId);
        if (existingStock.isPresent()) {
            StockLevel stockLevel = existingStock.get();
            stockLevel.setQuantity(stockLevel.getQuantity() + quantity);
            return Optional.of(stockLevelRepository.save(stockLevel));
        }
        return Optional.empty();
    }

    // Réaliser une vente d'un produit dans un magasin
    public Optional<StockLevel> vendre(Integer productId, Integer magasinId, Integer quantity) {
        Optional<StockLevel> existingStock = stockLevelRepository.findByProductIdAndMagasinId(productId, magasinId);
        if (existingStock.isPresent()) {
            StockLevel stockLevel = existingStock.get();
            if (stockLevel.getQuantity() >= quantity) {
                stockLevel.setQuantity(stockLevel.getQuantity() - quantity);
                return Optional.of(stockLevelRepository.save(stockLevel));
            }
        }
        return Optional.empty();
    }
}
