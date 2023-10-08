package com.ada.MeuPrimeiroProjeto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor// cria construtor padrão com todos os argumentos
@NoArgsConstructor // não cria construtor padrão com todos os argumentos
@Entity //é uma entidade do banco de dados >> tera uma tabela onde os dados serão salvo
@Table (name = "users")
@Where(clause = "active is true") //retornará apenas os registros que estiverem ativos, ie, sem delete logico
public class User implements UserDetails {

    //@Id
    //private Integer id;
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@GeneratedValue(strategy = GenerationType.TABLE)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name ="name", nullable = false)
    private String name;

    @Column (name ="email", nullable = false, unique = true)
    private String email;

    @Column (name ="password", nullable = false)
    private String password;

    private Boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    //obs em column nullable = false >> a informação nao pode ser nula
    // e unique = true >> e os emails nao podem se respetidos
    //variavel active >> usada para o delete logico,ie, para nao deletar definitivamente os dados de um usuário

}
