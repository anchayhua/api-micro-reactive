package com.quique.apireactive;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ApiReactiveApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGreetingMessage() {
		MyTestController controller = new MyTestController();

		StepVerifier.create(controller.greetingMessage())
				.expectNext("Hello Quique")
				.expectComplete()
				.verify();
	}

	@Test
	public void testGreet() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/greet"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello ncnr , Good day "));
	}
}
