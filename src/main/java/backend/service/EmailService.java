package backend.service;
import backend.dto.UserDTO;
import backend.dto.OrderDTO;
import backend.dto.SellerDTO;
import java.util.List;

public interface EmailService {
	
	public void emailConfirmOrder(OrderDTO orderDTO);
	
	public void welcome(UserDTO userDTO);
	
	public void sendForgetPassword();
}
