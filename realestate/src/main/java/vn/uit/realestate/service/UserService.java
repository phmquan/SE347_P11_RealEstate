package vn.uit.realestate.service;

import java.util.List;
import org.springframework.stereotype.Service;
import vn.uit.realestate.domain.Role;
import vn.uit.realestate.domain.User;
import vn.uit.realestate.domain.dto.RegisterDTO;
import vn.uit.realestate.repository.RoleRepository;
import vn.uit.realestate.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  public UserService(RoleRepository roleRepository, UserRepository userRepository) {
    this.roleRepository = roleRepository;
    this.userRepository = userRepository;
  }

  public User GetUserByID(Long id) {
    return this.userRepository.findByid(id);
  }

  public List<User> GetAllUser() {
    return this.userRepository.findAll();
  }

  public List<User> GetAllUserByEmail(String email) {
    return this.userRepository.findOneByEmail(email);
  }

  public User handleSaveUser(User user) {
    return this.userRepository.save(user);
  }

  public void handleDeleteUserById(Long id) {
    this.userRepository.deleteById(id);
  }

  public Role getRoleByName(String name) {
    return this.roleRepository.findByName(name);
  }

  public boolean checkEmailExist(String email) {
    return this.userRepository.existsByEmail(email);
  }

  public User getUserByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }

  public User RegisterDTOtoUser(RegisterDTO registerDTO) {
    User user = new User();
    user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
    user.setEmail(registerDTO.getEmail());
    user.setPassword(registerDTO.getPassword());
    return user;
  }

  public Role getRoleById(Long id) {
    return this.roleRepository.findById(id).get();
  }
}
