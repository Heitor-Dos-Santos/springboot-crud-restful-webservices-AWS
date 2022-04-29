package net.javaguides.springbootcrudrestfulwebservices.controller;

import net.javaguides.springbootcrudrestfulwebservices.entity.User;
import net.javaguides.springbootcrudrestfulwebservices.exception.ResourceNotFoundException;
import net.javaguides.springbootcrudrestfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //Chamar todos usuarios
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    //Chamar usuario por Id
    @GetMapping("{id}")
    public User getUserById(@PathVariable(value = "id") long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
    }
    //Criar usuario
    @PostMapping
    public User CreateUser(@Valid @RequestBody User user){
        return this.userRepository.save(user);

    }
    //Atualizar usuario
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable ("id") long userId ){
        User existing = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        existing.setFirstName(user.getFirstName());
        existing.setLastName(user.getLastName());
        existing.setEmail(user.getEmail());
        return this.userRepository.save(existing);
    }

    //Deletar usuarios por id
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long userId){
        User existingUser = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        this.userRepository.delete(existingUser);
        return  ResponseEntity.ok().build();
    }


}
