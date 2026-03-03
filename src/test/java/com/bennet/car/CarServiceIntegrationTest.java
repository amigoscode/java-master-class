package com.bennet.car;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ActiveProfiles( "test" )
@AutoConfigureMockMvc
@Transactional
public class CarServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName( "Test view available cars" )
    void testViewAvailableCars() throws Exception {
        // given - Create a new available car
        mockMvc.perform( MockMvcRequestBuilders.post( "/api/v1/cars" )
                .content( """
                        {
                            "brand": "Audi",
                            "model": "RS e-tron GT",
                            "type": "ELECTRIC"
                        }
                        """
                )
                .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.status().isOk() );

        // given - Create another car
        mockMvc.perform( MockMvcRequestBuilders.post( "/api/v1/cars" )
                .content( """
                        {
                            "brand": "BMW",
                            "model": "i4 eDrive40 Gran Coupé",
                            "type": "ELECTRIC"
                        }
                        """
                )
                .contentType( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.status().isOk() );

        // when & then - Verify both cars are in the available cars list using JSONPath filter
        mockMvc.perform( MockMvcRequestBuilders.get( "/api/v1/cars/available" )
                .accept( MediaType.APPLICATION_JSON ) )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.jsonPath( "$[?(@.brand == 'Audi' && @.model == 'RS e-tron GT')]" ).exists() )
                .andExpect( MockMvcResultMatchers.jsonPath( "$[?(@.brand == 'BMW' && @.model == 'i4 eDrive40 Gran Coupé')]" ).exists() );
    }

}

