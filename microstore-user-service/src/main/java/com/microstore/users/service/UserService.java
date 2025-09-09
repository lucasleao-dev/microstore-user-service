package com.microstore.users.service;

import com.microstore.users.exception.UserNotFoundException;
import com.microstore.users.model.User;
import com.microstore.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Criar usuário
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Buscar todos os usuários
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar usuário por ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    // Atualizar usuário
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        
        return userRepository.save(existingUser);
    }

    // Deletar usuário
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
