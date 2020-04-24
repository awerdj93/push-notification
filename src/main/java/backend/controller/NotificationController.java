package backend.controller;

import backend.dto.UserDTO;
import backend.dto.SellerDTO;
import backend.service.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class NotificationController{

	@Autowired
	private NotificationService notificationService;


	@GetMapping("/users/notifications")
	@ResponseStatus(HttpStatus.OK)
	public List<UserDTO> findAllUser() {
		return notificationService.findAllUser();
	}

	@GetMapping("/sellers/notifications")
	@ResponseStatus(HttpStatus.OK)
	public List<SellerDTO> findAllSeller() {
		return notificationService.findAllSeller();
	}

	@PostMapping("/users/{userId}/notifications")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addUser(@RequestBody UserDTO userDTO) {
		return notificationService.addUser(userDTO);
	}


	@PostMapping("sellers/{sellerId}/notifications")
	@ResponseStatus(HttpStatus.CREATED)
	public Long addSeller(@RequestBody SellerDTO sellerDTO){
		return notificationService.addSeller(sellerDTO);
	}




	
}
