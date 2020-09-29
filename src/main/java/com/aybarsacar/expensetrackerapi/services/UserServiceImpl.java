package com.aybarsacar.expensetrackerapi.services;

import com.aybarsacar.expensetrackerapi.domain.User;
import com.aybarsacar.expensetrackerapi.exceptions.EtAuthException;
import com.aybarsacar.expensetrackerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public User validateUser(String email, String password) throws EtAuthException {

    if (email !=  null) email = email.toLowerCase();

    return userRepository.findByEmailAndPassword(email, password);
  }

  @Override
  public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {

    Pattern pattern = Pattern.compile("^(.+)@(.+)$");
    if (email != null) {
      email = email.toLowerCase();
    }

    if (!pattern.matcher(email).matches()) {
      throw new EtAuthException("Invalid email format");
    }

//    check if the user already exists
    Integer count = userRepository.getCountByEmail(email);
    if (count > 0) throw new EtAuthException("Email is already in use");

    Integer userId = userRepository.create(firstName, lastName, email, password);

//    after save fetch the user by id and return
    return userRepository.findById(userId);

  }
}
