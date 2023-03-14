package fr.akirabane.AkirabaneManagementWebBack.springsecurity.configuration;
import fr.akirabane.AkirabaneManagementWebBack.compute.dao.IPlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AppUserService implements UserDetailsService {

    @Autowired
    private IPlayerDao playerDao;

    public AppUserService(){}
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("hello world ");
        var player = playerDao.findById(username);
        if(!player.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        var user = player.get();
        var result = new MyUserDetails(user);
        return result;
    }
}
