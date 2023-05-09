package com.sansa.FundacioEsplaiHackathon.user;

import com.sansa.FundacioEsplaiHackathon.user.dto.UserDto;
import com.sansa.FundacioEsplaiHackathon.user.dto.UserToDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository data;
    private final UserToDto toDto;

    public boolean exists(String name){
        if(name == null){
            return false;
        }else{
            return data.existsByUsername(name);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return data.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }

    public UserDto userToDTO(User user){
        return toDto.apply(user);
    }


    public User saveUser(User user){
        return data.save(user);
    }

    public User saveAndFlushUser(User user){
        return data.saveAndFlush(user);
    }
}
