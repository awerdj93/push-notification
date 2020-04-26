package backend.service;

import backend.dto.UserDTO;
import backend.dto.SellerDTO;
import java.util.List;




public interface NotificationService {
	public List<UserDTO> findAllUser();
	public List<SellerDTO> findAllSeller();
	public List<UserDTO> addSeller(SellerDTO sellerDTO) throws Exception ;

	public Long addUser(UserDTO userDTO);



}
