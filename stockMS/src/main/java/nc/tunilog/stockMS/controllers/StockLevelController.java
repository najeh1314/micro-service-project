package nc.tunilog.stockMS.controllers;

import nc.tunilog.stockMS.models.StockLevel;
import nc.tunilog.stockMS.services.StockLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nc.tunilog.stockMS.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stock-levels")
public class StockLevelController {

    @Autowired
    private StockLevelService stockLevelService;

    // Ajouter ou mettre à jour un niveau de stock
    @PostMapping
    public ResponseEntity<StockLevel> saveOrUpdateStockLevel(@RequestBody StockLevel stockLevel) {
        StockLevel savedStockLevel = stockLevelService.saveOrUpdateStockLevel(stockLevel);
        return new ResponseEntity<>(savedStockLevel, HttpStatus.CREATED);
    }

    // Trouver un niveau de stock par ID
    @GetMapping("/{id}")
    public ResponseEntity<StockLevel> getStockLevelById(@PathVariable Integer id) {
        Optional<StockLevel> stockLevel = stockLevelService.getStockLevelById(id);
        return stockLevel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtenir les niveaux de stock pour un magasin donné
    @GetMapping("/magasin/{magasinId}")
    public ResponseEntity<List<StockLevel>> getStockLevelsByMagasinId(@PathVariable Integer magasinId) {
        List<StockLevel> stockLevels = stockLevelService.getStockLevelsByMagasinId(magasinId);
        return ResponseEntity.ok(stockLevels);
    }

    // Supprimer un niveau de stock
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockLevel(@PathVariable Integer id) {
        boolean deleted = stockLevelService.deleteStockLevel(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Approvisionner un produit
    @PutMapping("/approvisionner")
    public ResponseEntity<StockLevel> approvisionner(@RequestParam Integer productId,
                                                     @RequestParam Integer magasinId,
                                                     @RequestParam Integer quantity) {
        Optional<StockLevel> updatedStock = stockLevelService.approvisionner(productId, magasinId, quantity);
        return updatedStock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Réaliser une vente
    @PutMapping("/vendre")
    public ResponseEntity<StockLevel> vendre(@RequestParam Integer productId,
                                             @RequestParam Integer magasinId,
                                             @RequestParam Integer quantity) {
        Optional<StockLevel> updatedStock = stockLevelService.vendre(productId, magasinId, quantity);
        return updatedStock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
