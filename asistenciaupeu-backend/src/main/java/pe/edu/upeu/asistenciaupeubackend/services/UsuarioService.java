package pe.edu.upeu.asistenciaupeubackend.services;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import pe.edu.upeu.asistenciaupeubackend.dtos.CredencialesDto;
import pe.edu.upeu.asistenciaupeubackend.dtos.UsuarioCrearDto;
import pe.edu.upeu.asistenciaupeubackend.dtos.UsuarioDto;
import pe.edu.upeu.asistenciaupeubackend.exceptions.AppException;
import pe.edu.upeu.asistenciaupeubackend.mappers.UsuarioMapper;
import pe.edu.upeu.asistenciaupeubackend.models.Rol;
import pe.edu.upeu.asistenciaupeubackend.models.Usuario;
import pe.edu.upeu.asistenciaupeubackend.repositories.UsuarioRepository;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository userRepository;
       
    private final RolService rolService;    

    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper userMapper;

    public UsuarioDto login(CredencialesDto credentialsDto) {
        Usuario user = userRepository.findByCorreo(credentialsDto.correo())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UsuarioDto register(UsuarioCrearDto userDto) {
        Optional<Usuario> optionalUser = userRepository.findByCorreo(userDto.correo());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Usuario user = userMapper.usuarioCrearDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));
         System.out.println("Llego.................");
         System.out.println(userDto.token());
        
         Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(Rol.RolNombre.ROLE_USER).get());
        //if (userDto.roles().contains(Rol.RolNombre.ROLE_ADMIN))
        if (userDto.token().equals("admin"))       
            roles.add(rolService.getByRolNombre(Rol.RolNombre.ROLE_ADMIN).get());
        user.setRoles(roles);
        
        
        Usuario savedUser = userRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UsuarioDto findByLogin(String correo) {
        Usuario user = userRepository.findByCorreo(correo)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

}
