package backend.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SellerDTO {
    private Long id;



    private Long sellerId;

    private Long productId;

    private String seller_addr;

    public void setSellerId(long sellerId) {
        this.sellerId=sellerId;
    }
    public void  setSeller_addr(String seller_addr) {
        this.seller_addr=seller_addr;
    }


}
