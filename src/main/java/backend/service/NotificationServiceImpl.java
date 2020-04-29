package backend.service;
import backend.dto.SellerDTO;
import backend.dto.UserDTO;
import backend.model.Seller;
import backend.model.User;
import backend.repository.UserRepository;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
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
	public List<UserDTO> neighbouringUserList(SellerDTO sellerDTO)  throws Exception{
		Seller seller = new Seller();
		BeanUtils.copyProperties(sellerDTO, seller);
		Iterable<User> iterable = userRepository.findAll();

		List<UserDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<User, UserDTO>() {
			@Override
			public UserDTO apply(User user) {
				try {
					NeighbouringUser nu = new NeighbouringUser();
					user = nu.neighbouringUser(user,seller);
				} catch (Exception e) {
					e.printStackTrace();
				}
				UserDTO userDTO = new UserDTO();
				BeanUtils.copyProperties(user, userDTO);
				return userDTO;
			}
		}).collect(Collectors.toList());
		for (Iterator<UserDTO> i = result.iterator(); i.hasNext();) {
			UserDTO userdto1 = i.next();
			User user1 = new User();
			BeanUtils.copyProperties(userdto1,user1);
			if (Objects.equals(user1.getUserId(), null)) {
				i.remove();
			}
		}
		return result;
	}

	@Override
	public void update(UserDTO userDTO){
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		List<UserDTO> existingUserDTO=findAllUser();
		User user2 = new User();
		int usrListSize = existingUserDTO.size();
		for (int i=0; i< usrListSize; i++){

			BeanUtils.copyProperties(existingUserDTO.get(i), user2);
			if (user2.getUserId()==user.getUserId()){
				Long id = user2.getId();
				deleteByUserIdAndId(user.getUserId(),id);
			}
		}
		userRepository.save(user);
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
