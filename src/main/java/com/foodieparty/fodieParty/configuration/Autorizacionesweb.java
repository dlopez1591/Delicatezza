package com.foodieparty.fodieParty.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.bind.annotation.PatchMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@EnableWebSecurity
@Configuration
public class Autorizacionesweb{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeRequests()

            .antMatchers(HttpMethod.POST,"/api/login").permitAll()
            .antMatchers(HttpMethod.POST,"/api/logout").permitAll()
            .antMatchers("/web/assets/**").permitAll()
            .antMatchers("/web/index.html").permitAll()
            .antMatchers("/web/adm**").hasAnyAuthority("ADMIN","MESERO")
            .antMatchers("/web/menu.html").hasAuthority("CLIENTE")
            .antMatchers("/web/pedidos.html").hasAuthority("CLIENTE")
            //CONTROLADOR BEBIDAS
            .antMatchers("/api/bebidas").authenticated()
            .antMatchers("/api/bebidas/{id}").authenticated()
            .antMatchers("/api/bebida/stock").hasAnyAuthority("ADMIN","MESERO")
            .antMatchers(HttpMethod.POST,"/api/crear/bebida").hasAuthority("ADMIN")
            //CONTROLADOR BEBIDA-PEDIDOS
            .antMatchers("/api/bebidaPedidos").authenticated()
            .antMatchers("/api/bebidaPedidos/{id}").authenticated()
            //CONTROLADOR CAPACIDAD
            .antMatchers(HttpMethod.POST,"/api/capacidad/editarPrecioReserva").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.POST,"/api/capacidad/editarCapacidad").hasAuthority("ADMIN")
            .antMatchers("/api/capacidad").authenticated()
            //CONTROLADOR COMIDA
            .antMatchers("/api/comidas").authenticated()
            .antMatchers("/api/comidas/{id}").authenticated()
            .antMatchers(HttpMethod.POST,"/api/crear/comida").hasAuthority("ADMIN")
            //CONTROLADOR COMIDA-PEDIDO
            .antMatchers("/api/comidaPedidos").authenticated()
            .antMatchers("/api/comidaPedidos/{id}").authenticated()
            //CONTROLADOR PEDIDO
            .antMatchers("/api/pedidos").authenticated()
            .antMatchers("/api/pedido/{id}").authenticated()
            .antMatchers(HttpMethod.POST,"/api/crear/pedido/usuario").hasAuthority("CLIENTE")
            .antMatchers(HttpMethod.POST,"/api/pedido/editarEstado").hasAnyAuthority("ADMIN","MESERO")
            //CONTROLADOR RESERVA
            .antMatchers("/api/reserva").authenticated()
            .antMatchers("/api/reservas/{id}").authenticated()
            .antMatchers("api/usuario/autenticado/reserva").hasAuthority("CLIENTE")
            .antMatchers(HttpMethod.POST,"/api/crear/reserva").hasAuthority("CLIENTE")
            .antMatchers(HttpMethod.PUT,"/api/reservas/{id}").hasAnyAuthority("ADMIN","MESERO")
            //CONTROLADOR TICKET PEDIDOS
            .antMatchers("/api/ticketPedidos").authenticated()
            //CONTROLADOR USUARIO
            .antMatchers(HttpMethod.POST,"/api/crear/usuario").permitAll()
            .antMatchers("/api/usuario").authenticated()
            .antMatchers("/api/usuario/{id}").authenticated()
            .antMatchers("/api/usuario/autenticado").hasAuthority("CLIENTE")
            .antMatchers(HttpMethod.PATCH,"/api/borrar/usuario").hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT,"/api/actualizar/usuario").hasAuthority("ADMIN")
            //
            .anyRequest().denyAll();


        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        // turn off checking for CSRF tokens
        http.csrf().disable();

        //disabling frameOptions so h2-console can be accessed
        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication

        http.formLogin().successHandler((req, res, auth) -> {
                if(auth.getAuthorities().equals("CLIENTE")){
                    res.setHeader("redireccion","./menu.html");
                }else{
                    res.setHeader("redireccion","./admin.html");
                }
                clearAuthenticationAttributes(req);
        }
        );

        // if login fails, just send an authentication failure response

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return http.build();
    }
    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
    }
}
