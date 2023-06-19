package com.sre.transportation.entity;

import com.sre.transportation.security.authority.Privilege;
import com.sre.transportation.security.authority.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;*/
    @Id  @Size(max = 36)
    String username;
    @Size(max = 8)
    String password;

    @Enumerated(EnumType.STRING)
    Role roles;

    String privileges;

    public void setPermissions(List<Privilege> privileges) {
        int privelegeSize = Privilege.values().length;
        List<Integer> list = new ArrayList<Integer>(Collections.nCopies(privelegeSize, 0));
        for(Privilege p:privileges){
            list.set(p.ordinal(),1);
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < list.size(); i++)
        {
            int myNumbersInt = list.get(i);
            str.append(myNumbersInt);
        }
        this.privileges = str.toString();
    }

    private Collection<? extends GrantedAuthority> getUserRoles(
            Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    private Collection<? extends GrantedAuthority> getUserPrivileges(
            String privileges) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        char[] x = privileges.toCharArray();
        for(int i =0; i < x.length; i++){
            if(Integer.parseInt(String.valueOf(x[i])) == 1){
                authorities.add(new SimpleGrantedAuthority(Privilege.values()[i].name()));
            }
        }
        return authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(
            Role role, String privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(getUserRoles(role));
        authorities.addAll(getUserPrivileges(privileges));
        return authorities;
    }

    /*private List<String> getPrivileges(String privileges){
        List<String> pv = new ArrayList<>();
        for(char c : privileges.toCharArray()){
            pv.add(Privilege.values()[privileges.indexOf(c)].name());
        }
        return pv;
    }
*/
}
