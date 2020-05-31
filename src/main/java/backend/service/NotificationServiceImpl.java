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
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import backend.dto.SellerDTO;
import backend.dto.UserDTO;
import backend.email.NeighbouringUser;
import backend.model.Seller;
import backend.model.User;
import backend.repository.UserRepository;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	public Environment env;
	public final String PRODUCT_URL = "microservices.product.url";
	public final String DELETE_URL = "microservices.delete.url";
	public final String EMAIL_USERNAME = "email.username";
	public final String EMAIL_PASSWORD = "email.password";
	public final String GOOGLE_API_KEY = "google.api.key";
	public final String GOOGLE_DISTANCE_API = "https://maps.googleapis.com/maps/api/distancematrix/json";



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
					//System.out.println(PRODUCT_URL_KEY1+","+env1.getProperty(EMAIL_USERNAME1)+","+env1.getProperty(EMAIL_PASSWORD1)+","+GOOGLE_DISTANCE_API1+","+env1.getProperty(GOOGLE_API_KEY1));
					user= nu.neighbouringUser(user,seller,env.getProperty(PRODUCT_URL),env.getProperty(EMAIL_USERNAME), env.getProperty(EMAIL_PASSWORD),GOOGLE_DISTANCE_API,env.getProperty(GOOGLE_API_KEY), env.getProperty(DELETE_URL));
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
			}List<UserDTO> existingUserDTO=findAllUser();
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
				userRepository.save(user);
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