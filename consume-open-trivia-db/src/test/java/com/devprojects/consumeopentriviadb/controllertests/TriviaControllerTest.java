package com.devprojects.consumeopentriviadb.controllertests;

import com.devprojects.consumeopentriviadb.ConsumeOpenTriviaDbApplication;
import com.devprojects.consumeopentriviadb.models.QuestionsFromAPI;
import com.devprojects.consumeopentriviadb.services.TriviaService;
import com.devprojects.consumeopentriviadb.utilities.MockQuestionsFromAPIUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumeOpenTriviaDbApplication.class)
public class TriviaControllerTest {

    /**
     * What I want to test:
     * 1. Test when Get Request response is NULL. The application should throw exception with clear messaging.
     * 2. Test when Get Request responseCode is 1, 2, 3 and 4. The application should throw exception with the related messaging from the API document.
     * 3. Test when Post Request questionId cannot be found. The application should throw exception that the questionId cannot be found.
     */

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private TriviaService triviaService;

    private MockQuestionsFromAPIUtils mockQuestionsFromAPIUtils;

    private static String url = "https://opentdb.com/api.php?amount=5&type=multiple";

    QuestionsFromAPI mockQuestions = mockQuestionsFromAPIUtils.getMockQuestionsFromAPI();
    QuestionsFromAPI mockQuestionsIsNULL = null;

    @Test
    public void givenMockQuestionsIsNull() throws Exception {
        QuestionsFromAPI questionsFromAPI = testRestTemplate.getForObject(url, QuestionsFromAPI.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/triviaquiz/multiplechoice")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
