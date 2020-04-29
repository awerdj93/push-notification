package backend.controller;
import backend.dto.UserDTO;
import backend.dto.SellerDTO;
import backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

	@PostMapping("/users/{userId}/notifications")//This saves a record of userdto, who subscribed for the email notification service in the database
	@ResponseStatus(HttpStatus.CREATED)
	public Long addUser(@RequestBody UserDTO userDTO) {
		return notificationService.addUser(userDTO);
	}

	@PutMapping("/users/{userId}/notifications")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody UserDTO userDTO) {
		notificationService.update(userDTO);
	}


	@DeleteMapping("/users/{userId}/notifications/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByUserIdAndId(@RequestBody Long userId, Long id) {
		notificationService.deleteByUserIdAndId(userId, id);
	}

	@PostMapping("sellers/{sellerId}/notifications")
	@ResponseStatus(HttpStatus.CREATED)
	public List<UserDTO> neighbouringUserList(@RequestBody SellerDTO sellerDTO) throws Exception {
		return notificationService.neighbouringUserList(sellerDTO);
	}
}
