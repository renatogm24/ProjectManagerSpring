package com.codingdojo.projectmanager.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.projectmanager.models.LoginUser;
import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User getUserSession(Long id) {
		return userRepo.findById(id).get();
	}

	// PARA HACER: ¡Escribir métodos de registro e inicio de sesión!
	public User register(User newUser, BindingResult result) {
		// PARA HACER: ¡Validaciones adicionales!
		// PARA HACER: Rechazar valores o registrar si no hay errore:
		String passwordEntered = newUser.getPassword();
		

		// Rechazar si el correo electrónico ya existe (presente en la base de datos)
		Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
		if(potentialUser.isPresent()) {
		    result.rejectValue("email", "Matches", "The entered mail already has an associated account");
		}
		
		// Rechazar si la contraseña no coincide con la confirmación
		if(!newUser.getPassword().equals(newUser.getConfirm())) {
		    result.rejectValue("confirm", "Matches", "The Confirm Password does not match");
		}

		// Devuelve null si el resultado tiene errores
		if(result.hasErrors()) {           
            return null;
        }
		
		// Cifra y establece la contraseña, guarda el usuario en la base de datos
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		userRepo.save(newUser);
		
		return newUser;
	}

	public User login(LoginUser newLoginObject, BindingResult result) {
		// TO-DO: Additional validations!
		// PARA HACER: Rechazar valores:
        
    	// Buscar usuario en la base de datos por correo electrónico
        // Rechazar si NO está presente
		Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
		if(potentialUser.isEmpty()) {
		    result.rejectValue("email", "Matches", "The entered mail does not exist");
		}
		
		// Rechazar si falla la coincidencia de contraseña de BCrypt
		if (potentialUser.isPresent()) {
			// Rechazar si falla la coincidencia de contraseña de BCrypt
			if(!BCrypt.checkpw(newLoginObject.getPassword(), potentialUser.get().getPassword())) {
			    result.rejectValue("password", "Matches", "Invalid Password!");
			}
		}	
        
		if(result.hasErrors()) {           
            return null;
        }
        // Devuelve null si el resultado tiene errores
        // De lo contrario, devuelve el objeto de usuario
		return potentialUser.get();
	}
	
	public User updateUser(User b) {
		return userRepo.save(b);
	}
}