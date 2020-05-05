package backend.service;
import backend.dto.SubscriberDTO;
import backend.dto.OrderDTO;
import backend.dto.SellerDTO;
import java.util.List;

public interface EmailService {
	
	public void emailConfirmOrder(Long id, OrderDTO orderDTO);
	
	public void welcome(SubscriberDTO userDTO);
	
	public void sendForgetPassword();
}
