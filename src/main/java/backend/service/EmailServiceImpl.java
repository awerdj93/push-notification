package backend.service;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import backend.dto.OrderDTO;
import backend.dto.UserDTO;
import backend.email.SendEmailHTML;
import backend.repository.UserRepository;

@Service
public class EmailServiceImpl implements EmailService{
	@Autowired
	private Environment env;
	
	private final String EMAIL_USERNAME = "email.username";
	private final String EMAIL_PASSWORD = "email.password";
	private final String PRODUCT_URL_KEY = "microservices.product.url";	
	private final String GOOGLE_API_KEY = "google.api.key";
	
	@Override
	public void emailConfirmOrder(OrderDTO orderDTO) {
		String username = env.getProperty(EMAIL_USERNAME);
        String password = env.getProperty(EMAIL_PASSWORD);
    	
		try {
			SendEmailHTML.sendmail(username, password, "awerdj93@hotmail.com", "Order " + orderDTO.getId() + " confirmed",
					"<h1>Order Confirmed </h1> <br>"
					+ "Order  order.id");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Hmm i need userDTO
	@Override
	public void welcome(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void sendForgetPassword() {
		String username = env.getProperty(EMAIL_USERNAME);
        String password = env.getProperty(EMAIL_PASSWORD);
        String prod = env.getProperty(PRODUCT_URL_KEY);
        String key = env.getProperty(GOOGLE_API_KEY);
    	
		try {
			System.out.println(username);
			System.out.println(password);
			System.out.println(prod);
			System.out.println(key);
			SendEmailHTML.sendmail(username, password, "awerdj93@hotmail.com", "Order  order.id confirmed",
					"<h1>Order Confirmed </h1> <br>"
					+ "Order  order.id");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	

}
