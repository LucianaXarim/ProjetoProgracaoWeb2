package com.ada.MeuPrimeiroProjeto.service;

import com.ada.MeuPrimeiroProjeto.controller.dto.UserRequest;
import com.ada.MeuPrimeiroProjeto.controller.dto.UserResponse;
import com.ada.MeuPrimeiroProjeto.controller.exception.PasswordValidationError;
import com.ada.MeuPrimeiroProjeto.model.User;
import com.ada.MeuPrimeiroProjeto.repository.UserRepository;
import com.ada.MeuPrimeiroProjeto.utils.UserConvert;
import com.ada.MeuPrimeiroProjeto.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Page<UserResponse> getUsers(int page, int size, String direciion){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.fromString(direciion), "name");
        Page<User> users = userRepository.findAll(pageRequest);
        return UserConvert.toResponsePage(users);
    }

    //Substituido pela chamada acima
    //    public List<User> getUsers(){
    //       return userRepository.findAll();
    //    }

    public UserResponse saveUser(UserRequest userDTO) throws PasswordValidationError {
        User user = UserConvert.toEntity(userDTO);

        String encodePassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodePassword);

        user.setActive(true);
        if (!Validator.passwordValidate(user.getPassword())) throw new PasswordValidationError("Senha deve seguir o padrão - Deve conter pelo menos 08 caracteres e pelo menos um numero, uma maiuscula e um caractere especial");
        User userEntity = userRepository.save(user);
        return UserConvert.toResponse(userEntity);
    }

    public UserResponse getUserById (Integer id) {
        Optional<User> userResponse = userRepository.findById(id);
        if (userResponse.isPresent()) {
            return UserConvert.toResponse(userResponse.get());
        } else {
            throw new RuntimeException("Id não encontrado");
        }
    }
    public List<UserResponse> getAllByName(String name){
        return UserConvert.toResponseList(userRepository.findAllByName(name));
    }

//    public UserResponse getUserByEmail(String email){
//        return UserConvert.toResponse(userRepository.findByEmail(email).get());
//    }


    public UserResponse getUserByEmail(String email){
        User user = (User) userRepository.findByEmail(email);
        return UserConvert.toResponse(user);
    }


    public void deleteUser(Integer id){
        User user = userRepository.findById(id).orElseThrow();
        user.setActive(false);
        userRepository.save(user);
    }

    public UserResponse updateUser(Integer id, UserRequest userRequest){
        User user = UserConvert.toEntity(userRequest);
        user.setId(id);
        return UserConvert.toResponse(userRepository.save(user));
    }

    // Formas iniciais de serviços para a UserService
    //Evoluidas nas chamadas acima
    //   public User getUserById (Integer id){
    //        Optional<User> user = userRepository.findById(id);
    //        return user.orElse(null);
    //    } //

    //public Optional<User> getUserByEmail(String email){
    //        return userRepository.findByEmail(email);
    //    } ///

    //    public List<User> getAllByName(String name){
    //        return userRepository.findAllByName(name);
    //    }//
}
