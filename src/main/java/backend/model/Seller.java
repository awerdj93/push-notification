package backend.model;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sellers")
@NoArgsConstructor
public class Seller implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="seller_id")
    private Long sellerId;

    @Column(name="product_id")
    private Long productId;


    private String seller_addr;



    public Long getId() {

        return id;
    }



    public Long getSellerId() {

        return sellerId;
    }


    public String getSellerAddr() {

        return seller_addr;
    }


}
