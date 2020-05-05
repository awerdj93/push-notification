package backend.service;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.dto.SellerDTO;
import backend.dto.SubscriberDTO;
import backend.email.NeighbouringUser;
import backend.model.Seller;
import backend.model.User;
import backend.repository.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService{

	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Long addUser(SubscriberDTO subscriberDTO) {
		Optional<User> opt = userRepository.findByUserId(subscriberDTO.getUserId());
		if (opt.isPresent()) {
			return opt.get().getId();	
		}
		else {
			User user = new User();
			BeanUtils.copyProperties(subscriberDTO, user);
			user = userRepository.save(user);
			return user.getUserId();
		}
	}


	@Override
	public List<SubscriberDTO> neighbouringUserList(SellerDTO sellerDTO)  throws Exception{
		Seller seller = new Seller();
		BeanUtils.copyProperties(sellerDTO, seller);
		Iterable<User> iterable = userRepository.findAll();

		List<SubscriberDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<User, SubscriberDTO>() {
			@Override
			public SubscriberDTO apply(User user) {
				try {
					NeighbouringUser nu = new NeighbouringUser();
					user= nu.neighbouringUser(user,seller);
				} catch (Exception e) {
					e.printStackTrace();
				}
				SubscriberDTO subscriberDTO = new SubscriberDTO();
				BeanUtils.copyProperties(user, subscriberDTO);
				return subscriberDTO;
			}
		}).collect(Collectors.toList());
		for (Iterator<SubscriberDTO> i = result.iterator(); i.hasNext();) {
			SubscriberDTO userdto1 = i.next();
			User user1 = new User();
			BeanUtils.copyProperties(userdto1,user1);
			if (Objects.equals(user1.getUserId(), null)) {
				i.remove();
			}
		}
		return result;
	}

	@Override
	public void update(SubscriberDTO subscriberDTO){
		User user = new User();
		BeanUtils.copyProperties(subscriberDTO, user);
		List<SubscriberDTO> existingDTO=findAllUser();
		User user2 = new User();
		int usrListSize = existingDTO.size();
		for (int i=0; i< usrListSize; i++){

			BeanUtils.copyProperties(existingDTO.get(i), user2);
			if (user2.getUserId()==user.getUserId()){
				Long id = user2.getId();
				deleteByUserIdAndId(user.getUserId(),id);
				userRepository.save(user);
			}
		}

	}

	@Override
	public void deleteByUserIdAndId(Long userId, Long id) {
//		Optional<User> userOpt = userRepository.findById(id);
//		if(userOpt.isPresent()) {
//			User user = userOpt.get();
			userRepository.deleteById(id);
//		}
	}

	@Override
	public List<SubscriberDTO> findAllUser() {
		Iterable<User> iterable = userRepository.findAll();

		List<SubscriberDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<User, SubscriberDTO>() {
			@Override
			public SubscriberDTO apply(User user) {
				SubscriberDTO subscriberDTO = new SubscriberDTO();
				BeanUtils.copyProperties(user, subscriberDTO);

				return subscriberDTO;
			}
		}).collect(Collectors.toList());

		return result;
	}


	@Override
	public SubscriberDTO getByUser(Long userId) {
		Optional<User> opt = userRepository.findByUserId(userId);
		SubscriberDTO dto = null;
		if (opt.isPresent()) {
			dto = new SubscriberDTO();
			BeanUtils.copyProperties(opt.get(), dto);
		}
		return dto;
	}
}
