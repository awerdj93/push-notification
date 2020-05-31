package backend.service;


import backend.dto.SellerDTO;
import backend.dto.UserDTO;
import backend.model.User;
import backend.repository.UserRepository;
import backend.service.NotificationServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class NotificationServiceImplTest {

    @Autowired
    NotificationServiceImpl notificationServiceImpl;

    @Test
    void addUser() {
        UserDTO userdto = new UserDTO();
        userdto.setUserId(1l);
        userdto.setUser_name("xx");
        userdto.setUser_email("sss@ss.com");
        userdto.setUser_addr("3 Science Drive 2, kent ridge, Singapore 117543");
        Long id = notificationServiceImpl.addUser(userdto);
        System.out.println(id);
    }

    @Test
    void neighbouringUserList() throws Exception {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setSellerId(1l);
        sellerDTO.setSeller_addr("3 Science Drive 2, kent ridge, Singapore 117543");
        List<UserDTO> userdto = notificationServiceImpl.neighbouringUserList(sellerDTO);
        System.out.println(userdto.get(0));
    }
    @Autowired
    UserRepository userRepository;
    @Test
    void update() {
        Optional<User> userOpt = userRepository.findById(1l);
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userdto = new UserDTO();
            userdto.setUserId(1l);
            userdto.setUser_name("xx1");
            userdto.setUser_email("aaa@ss.com");
            userdto.setUser_addr("3 Science Drive 2, kent ridge, Singapore 117543");
            notificationServiceImpl.deleteByUserIdAndId(1l,user.getId());
            notificationServiceImpl.update(userdto);
        }

    }

    @Test
    void deleteByUserIdAndId() {
    }

    @Test
    void findAllUser() {
    }
}