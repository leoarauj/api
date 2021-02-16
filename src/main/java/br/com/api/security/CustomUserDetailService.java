package br.com.api.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.api.model.Perfil;
import br.com.api.model.Usuario;
import br.com.api.repository.UsuarioRepository;

//public class CustomUserDetailService  {
@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = Optional.ofNullable(usuarioRepository.findByLogin(username))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		String[] permissoes = user.getPerfis()
				.stream()
				.map(Perfil::getPermissao)
				.collect(Collectors.toList())
				.toArray(new String[user.getPerfis().size()]);

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(permissoes);

		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}

}
