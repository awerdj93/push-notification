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


	@GetMapping("subscribers/users")
	@ResponseStatus(HttpStatus.OK)
	public List<UserDTO> findAllUser() {
		return notificationService.findAllUser();
	}
	
	@GetMapping("subscribers/users/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO findByUser(@PathVariable Long userId) {
		return notificationService.getByUser(userId);
	}

	@PostMapping("subscribers/users/{userId}")//This saves a record of userdto, who subscribed for the email notification service in the database
	@ResponseStatus(HttpStatus.CREATED)
	public Long addUser(@RequestBody UserDTO userDTO) {
		return notificationService.addUser(userDTO);
	}

	@PutMapping("subscribers/users/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@RequestBody UserDTO userDTO) {
		notificationService.update(userDTO);
	}


	@DeleteMapping("subscribers/{subId}/users/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByUserIdAndId(@PathVariable Long subId, @PathVariable Long userId) {
		notificationService.deleteByUserIdAndId(userId, subId);
	}

	@PostMapping("notifications/sellers/{sellerId}")
	@ResponseStatus(HttpStatus.CREATED)
	public List<UserDTO> neighbouringUserList(@RequestBody SellerDTO sellerDTO) throws Exception {
		return notificationService.neighbouringUserList(sellerDTO);
	}
}