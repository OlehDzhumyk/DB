package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.LoginAndPassword;
import ua.lviv.iot.exception.LoginAndPasswordNotFoundException;
import ua.lviv.iot.repository.LoginAndPasswordRepository;
import ua.lviv.iot.repository.UserRepository;
import ua.lviv.iot.service.LoginAndPasswordService;

import java.util.List;

@Service
public class LoginAndPasswordServiceImpl implements LoginAndPasswordService {

    @Autowired
    LoginAndPasswordRepository loginAndPasswordRepository;

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    DriverRepository driverRepository;

    @Override
    public List<LoginAndPassword> findAll() {
        return loginAndPasswordRepository.findAll();
    }

    @Override
    public LoginAndPassword findById(Integer id) {
        return loginAndPasswordRepository.findById(id)
                .orElseThrow(() -> new LoginAndPasswordNotFoundException(id));
    }

    @Override
    public LoginAndPassword create(LoginAndPassword loginAndPassword) {
        loginAndPasswordRepository.save(loginAndPassword);
        return loginAndPassword;
    }

    @Override
    public void update(Integer id, LoginAndPassword uLoginAndPassword) {
        LoginAndPassword loginAndPassword = loginAndPasswordRepository.findById(id)
                .orElseThrow(() -> new LoginAndPasswordNotFoundException(id));
        //update
        loginAndPassword.setLogin(uLoginAndPassword.getLogin());
        loginAndPassword.setPassword(uLoginAndPassword.getPassword());

        loginAndPasswordRepository.save(loginAndPassword);
    }

    @Override
    public void delete(Integer id) {
        LoginAndPassword loginAndPassword = loginAndPasswordRepository.findById(id)
                .orElseThrow(() -> new LoginAndPasswordNotFoundException(id));
        loginAndPasswordRepository.delete(loginAndPassword);
    }

//
//    @Override
//    public List<LoginAndPassword> findLoginAndPasswordsByDriverId(Integer driverId) {
//        Driver driver = driverRepository.findById(driverId)
//                .orElseThrow(() -> new DriverNotFoundException(driverId));
//        return driver.getLoginAndPasswords();
//    }

}
