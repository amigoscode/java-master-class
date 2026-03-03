package com.bennet.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles( "test" )
@AutoConfigureMockMvc
@Transactional
public class UserServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllUsersReturnsPersistedUserData() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post( "/api/v1/users" )
                    .accept( MediaType.APPLICATION_JSON )
                        .content( """
                                {
                                    "name": "Bennet"
                                }
                                """
                        )
                        .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.jsonPath("$.name").value("Bennet"));
    }
}
