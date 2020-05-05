package backend.service;
import backend.dto.SubscriberDTO;
import backend.dto.SellerDTO;
import java.util.List;

public interface NotificationService {
	public List<SubscriberDTO> findAllUser();
	public List<SubscriberDTO> neighbouringUserList(SellerDTO sellerDTO) throws Exception ;
	public Long addUser(SubscriberDTO subscriberDTO);
	public void update(SubscriberDTO subscriberDTO);
	public void deleteByUserIdAndId(Long userId, Long id);
	public SubscriberDTO getByUser(Long userId);
}
