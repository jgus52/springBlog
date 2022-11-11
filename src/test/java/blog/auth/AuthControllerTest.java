package blog.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties
@AutoConfigureMockMvc
class AuthControllerTest {
    @Value("${id}")
    String id;
    @Value("${password}")
    String pwd;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public AuthController authController;

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("login test")
    void login() throws Exception {
        LoginForm loginForm = new LoginForm(id, pwd);
        LoginForm loginForm2 = new LoginForm("xxx", "xyz");

        mvc.perform(MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginForm))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        mvc.perform(MockMvcRequestBuilders.post("/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginForm2))
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}