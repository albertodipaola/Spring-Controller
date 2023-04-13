package com.example.jpacontroller;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.jpacontroller.models.Author;
import com.example.jpacontroller.models.Comment;
import com.example.jpacontroller.models.Post;
import com.example.jpacontroller.repository.AuthorRepository;
import com.example.jpacontroller.repository.CommentRepository;
import com.example.jpacontroller.repository.PostRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class JpacontrollerApplicationTests {

	@Autowired
	AuthorRepository aRepository;
	@Autowired
	PostRepository pRepository;
	@Autowired
	CommentRepository cRepository;

	@BeforeEach
	public void createData() {
		Author a1 = new Author();
		a1.setFirstname("Alberto");
		a1.setLastname("Di Paola");
		a1.setEmail("test1@blog.it");
		aRepository.save(a1);

		Author a2 = new Author();
		a2.setFirstname("Simone");
		a2.setLastname("Santoro");
		a2.setEmail("test2@blog.it");
		aRepository.save(a2);

		Post p1 = new Post();
		p1.setTitle("nuovo iphone 14");
		p1.setBody("bla bla bla");
		p1.setPublishDate("12042023");
		p1.setAuthor(a1);
		pRepository.save(p1);

		Post p2 = new Post();
		p2.setTitle("nuovo xiaomi mi 14");
		p2.setBody("bla bla bla");
		p2.setPublishDate("12042023");
		p2.setAuthor(a1);
		pRepository.save(p2);

		Comment c1 = new Comment();
		c1.setEmail("mail@ciao.it");
		c1.setBody("fai kifo!!1!11!");
		c1.setDate("12042023");
		c1.setPost(p1);
		cRepository.save(c1);

		Comment c2 = new Comment();
		c2.setEmail("tizio@caio.it");
		c2.setBody("fai tanto kifo!!1!11!");
		c2.setDate("12042023");
		c2.setPost(p1);
		cRepository.save(c2);
	}

	@Test
	void findAlberto() {
		List<Post> posts = pRepository.findAlberto();
		Assertions.assertThat(posts).hasSize(2);
		Assertions.assertThat(posts)
			.first()
			.extracting("author")
			.extracting("firstname")
			.isEqualTo("Alberto");
	}

	@Test
	void findAlberto2() {
		List<Post> posts = pRepository.findAuthorFirstname("Alberto");
		Assertions.assertThat(posts).hasSize(2);
		Assertions.assertThat(posts)
				.first()
				.extracting("author")
				.extracting("firstname")
				.isEqualTo("Alberto");
	}

	@Test
	void findAlberto3() {
		List<Post> posts = pRepository.findAuthorFirstnameAndLastname("Alberto", "Di Paola");
		Assertions.assertThat(posts).hasSize(2);
		Assertions.assertThat(posts)
			.first()
			.extracting("author")
			.extracting("firstname")
			.isEqualTo("Alberto");
		Assertions.assertThat(posts)
			.first()
			.extracting("author")
			.extracting("lastname")
			.isEqualTo("Di Paola");

	}

	@Test
	void deleteAlberto(){
		cRepository.deleteAlberto();
		Assertions.assertThat(cRepository.count()).isEqualTo(1);
	}

}
