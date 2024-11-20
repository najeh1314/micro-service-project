package nc.tunilog.stockMS.services;

import nc.tunilog.stockMS.models.Product;
import nc.tunilog.stockMS.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    // Ajouter un produit
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    // Modifier un produit
    public Optional<Product> updateProduct(Integer id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            updatedProduct.setName(product.getName());
            updatedProduct.setDescription(product.getDescription());
            updatedProduct.setPrice(product.getPrice());
            return Optional.of(productRepository.save(updatedProduct));
        }
        return Optional.empty();
    }
    // Supprimer un produit
    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    // Rechercher un produit par ID
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }
    // Rechercher tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
