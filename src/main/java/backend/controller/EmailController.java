package backend.controller;
import backend.dto.UserDTO;
import backend.service.EmailService;
import backend.dto.OrderDTO;
import backend.dto.SellerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmailController{

	@Autowired
	private EmailService emailService;


	@PostMapping("/email/order")
	@ResponseStatus(HttpStatus.OK)
	public void sendConfirmOrder(@RequestBody OrderDTO orderDTO) {
		emailService.emailConfirmOrder(orderDTO);
	}

//	@PostMapping("/email/forget")
//	@ResponseStatus(HttpStatus.OK)
//	public void sendForgetPassword() {
//		emailService.sendForgetPassword();
//	}
}
