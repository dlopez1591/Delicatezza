package com.foodieparty.fodieParty.configuration;

import com.foodieparty.fodieParty.models.Usuario;
import com.foodieparty.fodieParty.repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class Autenticacion extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;


    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();

    }
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            Usuario usuario= usuarioRepositorio.findByEmail(inputName);
            if (usuario.getEmail().contains("@fdparty")){
                if (usuario.getEmail().contains("admin")){
                    return new User(usuario.getEmail(), usuario.getContraseña(),

                            AuthorityUtils.createAuthorityList("ADMIN"));
                }
                if (usuario.getEmail().contains("mesero")){
                    return new User(usuario.getEmail(), usuario.getContraseña(),

                            AuthorityUtils.createAuthorityList("MESERO"));
                }
                else {
                    throw new UsernameNotFoundException("Unknown user: " + inputName);
                }

            }
            else if (usuario != null) {

                return new User(usuario.getEmail(), usuario.getContraseña(),

                        AuthorityUtils.createAuthorityList("CLIENTE"));

            } else {

                throw new UsernameNotFoundException("Unknown user: " + inputName);

            }

        });

    }
}
