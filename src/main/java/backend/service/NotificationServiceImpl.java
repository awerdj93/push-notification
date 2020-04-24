package backend.service;

import backend.dto.SellerDTO;
import backend.dto.UserDTO;
import backend.model.Seller;
import backend.model.User;
import backend.repository.UserRepository;
import backend.repository.SellerRepository;
/*import backend.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.hibernate.mapping.Map;*/
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;


import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;*/
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public Long addUser(UserDTO userDTO) {
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);
		user = userRepository.save(user);
		return user.getUserId();
	}


	@Override
	public Long addSeller(SellerDTO sellerDTO)  {
		Seller seller = new Seller();
		BeanUtils.copyProperties(sellerDTO, seller);
		seller = sellerRepository.save(seller);
		return seller.getSellerId();
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


	@Override
	public List<SellerDTO> findAllSeller() {
		Iterable<Seller> iterable = sellerRepository.findAll();

		List<SellerDTO> result = StreamSupport.stream(iterable.spliterator(), false).map(new Function<Seller, SellerDTO>() {
			@Override
			public SellerDTO apply(Seller seller) {
				SellerDTO sellerDTO = new SellerDTO();
				BeanUtils.copyProperties(seller, sellerDTO);

				return sellerDTO;
			}
		}).collect(Collectors.toList());

		return result;
	}




}
