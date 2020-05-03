package backend.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private long userId;
	
	private BigDecimal subTotalPrice;
	
	private BigDecimal totalPrice;
	
	private Address deliveryAddress;
	
	private Address billingAddress;
	
	private List<Item> items;

    public long getId() {
    	return id;
    }

    @Getter
	@Setter
	@ToString
	public static class Item implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private long id;
		private BigDecimal price;
		private int quantity;
		private String name;
	}
	
	@Getter
	@Setter
	@ToString
	public static class Address implements Serializable {
		
		private static final long serialVersionUID = 3L;
		private String firstName;
		
		private String lastName;
		
		private String block;
		
		private String street;
		
		private String unitNumber;
		
		private String postalCode;
		
		private String phoneNumber;
	}
	
}
