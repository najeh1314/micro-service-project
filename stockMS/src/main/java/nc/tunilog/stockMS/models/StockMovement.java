package nc.tunilog.stockMS.models;

import jakarta.persistence.*;
import nc.tunilog.stockMS.enumerations.MovementType;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class StockMovement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int magasinId; // ID du magasin d'origine ou de destination

    @Enumerated(EnumType.STRING)
    private MovementType type; // Type de mouvement (IN, OUT, TRANSFER)

    private Integer quantity; // Quantité en mouvement
    private LocalDateTime movementDate;

    private int relatedOrderId; // Identifiant de commande, si applicable

    // Getters et Setters
}
