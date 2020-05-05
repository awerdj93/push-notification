package backend.service;
import backend.dto.UserDTO;
import backend.dto.SellerDTO;
import java.util.List;

public interface NotificationService {
	public List<UserDTO> findAllUser();
	public List<UserDTO> neighbouringUserList(SellerDTO sellerDTO) throws Exception ;
	public Long addUser(UserDTO userDTO);
	public void update(UserDTO userDTO);
	public void deleteByUserIdAndId(Long userId, Long id);
	public UserDTO getByUser(Long userId);
}
