package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void deveriaCarregarUmCursoAoBuscarPeloNome(){
		String nomeCurso = "HTML 5";

		Curso cursoAPersistir = new Curso();
		cursoAPersistir.setNome(nomeCurso);
		cursoAPersistir.setCategoria("Programação");

		testEntityManager.persist(cursoAPersistir);


		Curso curso = cursoRepository.findByNome(nomeCurso);
		Assert.assertNotNull(curso);
		Assert.assertEquals(curso.getNome(),
				nomeCurso);

	}

	@Test
	public void naoDeveriaCarregarUmCursoCujoNomeNaoEstejaCadastrado(){
		String nomeCurso = "JPA";
		Curso curso = cursoRepository.findByNome(nomeCurso);

		Assert.assertNull(curso);

	}

}
