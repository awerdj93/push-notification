package backend.service;
import backend.dto.SellerDTO;
import backend.dto.UserDTO;
import backend.model.Seller;
import backend.model.User;
import backend.repository.UserRepository;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private UserRepository userRepository;



	@Override
	public Long addUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		user = userRepository.save(user);
		return user.getUserId();
	}


	@Override
	public List<UserDTO> addSeller(SellerDTO sellerDTO)  throws Exception{
		Seller seller = new Seller();
		BeanUtils.copyProperties(sellerDTO, seller);
		//seller = sellerRepository.save(seller);
		Iterable<User> iterable = userRepository.findAll();
		Seller finalSeller = seller;
		List<UserDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<User, UserDTO>() {
   		@Override
   		public UserDTO apply(User user) {
			try {
				user= NeighbouringUsers.users(user,finalSeller);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UserDTO userDTO = new UserDTO();
			BeanUtils.copyProperties(user, userDTO);
			return userDTO;
   		}
		}).collect(Collectors.toList());
		return result;
	}


	@Override
	public void update(UserDTO userDTO){
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		userRepository.save(user);
		List<UserDTO> existingUserDTO=findAllUser();
		User user1 = new User();
		for (int i=0; i< existingUserDTO.size(); i++){

			BeanUtils.copyProperties(existingUserDTO.get(i), user1);
			if (user1.getUserId()==user.getUserId()){
				Long id = user1.getId();
				deleteByUserIdAndId(user.getUserId(),id);
			}
		}
	}

	@Override
	public void deleteByUserIdAndId(Long userId, Long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			if(user.getUserId().equals(userId)) {
				userRepository.deleteById(id);
			}
		}
	}




	@Override
	public List<UserDTO> findAllUser() {
		Iterable<User> iterable = userRepository.findAll();

		List<UserDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<User, UserDTO>() {
			@Override
			public UserDTO apply(User user) {
				UserDTO userDTO = new UserDTO();
				BeanUtils.copyProperties(user, userDTO);

				return userDTO;
			}
		}).collect(Collectors.toList());

		return result;
	}
}
