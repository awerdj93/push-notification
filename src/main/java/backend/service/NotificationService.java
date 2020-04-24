package backend.service;

import backend.dto.UserDTO;
import backend.dto.SellerDTO;
import java.util.List;




public interface NotificationService {
	public List<UserDTO> findAllUser();
	public List<SellerDTO> findAllSeller();
	public Long addSeller(SellerDTO sellerDTO) ;

	public Long addUser(UserDTO userDTO);



}
