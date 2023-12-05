package com.galileo.ecommerce.service.user;


import com.galileo.ecommerce.dto.SignupDTO;
import com.galileo.ecommerce.dto.UserDTO;
import com.galileo.ecommerce.entities.User;
import com.galileo.ecommerce.enums.UserRole;
import com.galileo.ecommerce.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;


  @PostConstruct
  public void createAdminAccount() {
    User adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
    if (adminAccount == null){
      User user = new User();
      user.setUserRole(UserRole.ADMIN);
      user.setEmail("admin@galileo.com");
      user.setName("admin");
      user.setPassword(new BCryptPasswordEncoder().encode("admin"));
      userRepository.save(user);
    }
  }

  @Override
  public UserDTO createUSer(SignupDTO signupDTO) {
    User user = new User();
    user.setName(signupDTO.getName());
    user.setEmail(signupDTO.getEmail());
    user.setUserRole(UserRole.USER);
    user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
    User createdUser = userRepository.save(user);
    UserDTO userDTO = new UserDTO();
    userDTO.setId(createdUser.getId());
    userDTO.setName(createdUser.getName());
    userDTO.setEmail(createdUser.getEmail());
    userDTO.setUserRole(createdUser.getUserRole());
    return userDTO;
  }



  @Override
  public boolean hasUserWithEmail(String email) {
    return userRepository.findFirstByEmail(email)!=null;
  }
}


/*


Bu Java sınıfı, kullanıcı işlemleriyle ilgili iş mantığını içeren bir servis sınıfını temsil eder.
Sınıfın adı UserServiceImpl ve com.galileo.ecommerce.service.user paketi altında yer alır.
Bu sınıf, UserService arabirimini uygular ve aşağıdaki temel özelliklere sahiptir:

Dependency Injection:

UserRepository türündeki userRepository alanına Spring Framework tarafından bağımlılık enjeksiyonu yapılır
(@Autowired annotasyonu ile). Bu, UserRepository'nin bu sınıf içinde kullanılabilir hale gelmesini sağlar.
Kullanıcı Oluşturma:

createUser metodu, SignupDTO türünden bir parametre alır ve bu bilgilerle bir kullanıcı nesnesi
oluşturarak veritabanına kaydeder.

 İlgili adımlar şu şekildedir:

SignupDTO içindeki bilgileri kullanarak yeni bir User nesnesi oluşturulur.
Kullanıcının şifresi BCryptPasswordEncoder kullanılarak şifrelenir.
Kullanıcının rolü, UserRole.USER olarak belirlenir.
Oluşturulan kullanıcı nesnesi userRepository.save ile veritabanına kaydedilir.
Oluşturulan kullanıcının bilgileri UserDTO'ya kopyalanır ve bu nesne geri döndürülür.

E-posta Kontrolü:

hasUserWithEmail metodu, veritabanında belirli bir e-posta adresine sahip bir kullanıcının olup
olmadığını kontrol eder. Eğer e-posta adresine sahip bir kullanıcı varsa true, yoksa false döndürür.
Bu sınıf, genellikle kullanıcı kaydı (signup) işlemlerinin gerçekleştirildiği bir servis sınıfını temsil eder.
UserRepository aracılığıyla veritabanı işlemleri gerçekleştirilir ve iş mantığı katmanında kullanıcı ile
ilgili bazı işlemler gerçekleştirilir.


 */
