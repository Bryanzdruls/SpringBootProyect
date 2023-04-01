package com.ideasexpress.sprignboot.app.demo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ideasexpress.sprignboot.app.demo.Models.Entity.Usuario;
import com.ideasexpress.sprignboot.app.demo.Models.Repository.IUsuarioRepo;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private IUsuarioRepo repo;
	@Test
	void crearUsuarioTest() {
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNombre("Brian");
		usuario.setClave("123");
		Usuario retorno =repo.save(usuario);

		assertTrue(retorno.getClave().equalsIgnoreCase(usuario.getClave()));
	}

}
