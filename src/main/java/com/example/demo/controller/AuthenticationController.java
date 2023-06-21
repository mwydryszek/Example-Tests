package com.example.demo.controller;

import com.example.demo.model.dtos.jwt.AuthenticationDTO;
import com.example.demo.model.dtos.jwt.LogoutDTO;
import com.example.demo.model.dtos.jwt.UsernamePasswordDTO;
import com.example.demo.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final JwtService jwtService;

	@PostMapping
	public ResponseEntity<AuthenticationDTO> authenticate(@RequestBody UsernamePasswordDTO usernamePasswordDTO){
		return ResponseEntity.ok(jwtService.auth(usernamePasswordDTO));
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestBody LogoutDTO logoutDTO){
		jwtService.logout(logoutDTO);
		return ResponseEntity.ok().build();
	}


}
