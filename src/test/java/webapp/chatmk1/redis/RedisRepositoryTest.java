package webapp.chatmk1.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.ConfigurableMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import webapp.chatmk1.controller.HomeController;

@Slf4j
@SpringBootTest
class RedisRepositoryTest {
    MockMvc mock;

    @BeforeEach
    void init() {
        this.mock = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }

    @Test
    void test1() {
        log.info("무언가 나와야한다.");
    }

    @Test
    void test2() throws Exception {
        ResultActions perform  = mock.perform(MockMvcRequestBuilders.get("/"));
        log.info("perform : {}", perform);
    }
}