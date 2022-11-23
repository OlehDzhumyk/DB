package ua.lviv.iot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.Event;
import ua.lviv.iot.domain.User;
import ua.lviv.iot.exception.UserHasNoEventException;
import ua.lviv.iot.exception.UserNotFoundException;
import ua.lviv.iot.repository.UserRepository;
import ua.lviv.iot.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void update(Integer id, User uUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        //update
        user.setName(uUser.getName());
        user.setPhone(uUser.getPhone());
        user.setEmail(uUser.getEmail());
        userRepository.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(user);
    }

    @Override
    public List<Event> findEventsByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return user.getEvents().stream().toList();
    }

    @Override
    public void insertTenUsers() {
        userRepository.insertTenUser();
    }

    @Override
    public Integer getUsersIdSum() {
        return userRepository.getUsersIdSum();
    }
}
