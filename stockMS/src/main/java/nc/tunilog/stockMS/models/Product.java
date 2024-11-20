package nc.tunilog.stockMS.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String idMagasin;
    private double price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StockLevel> stockLevels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(String mg) {
        this.idMagasin = mg;
    }

    public List<StockLevel> getStockLevels() {
        return stockLevels;
    }

    public void setStockLevels(List<StockLevel> stockLevels) {
        this.stockLevels = stockLevels;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
