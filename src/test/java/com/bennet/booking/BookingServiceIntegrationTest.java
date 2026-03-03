package com.bennet.booking;

import com.bennet.booking.value_objects.TimeSequence;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles( "test" )
@AutoConfigureMockMvc
@Transactional
public class BookingServiceIntegrationTest {
    @Inject
    private MockMvc mockMvc;

    @Test
    @DisplayName( "Test create Booking - Successfully" )
    void testCreateBooking() throws Exception {
        // given
        UUID userId = UUID.fromString("0bed8fee-3de8-4391-b9f7-6b6bf764bc13");
        UUID carId = UUID.fromString("574e2e9b-eecc-4629-b1c3-96cb60bd9e61");
        TimeSequence sequence = new TimeSequence(
                LocalDateTime.of( 2026, 3, 3, 15, 40, 0 ),
                LocalDateTime.of( 2026, 3, 28, 15, 40, 0 )
        );

        // when && then
        mockMvc.perform( MockMvcRequestBuilders.post( "/api/v1/bookings")
                        .content( """
                                {
                                    "userId": "%s",
                                    "carId": "%s",
                                    "timeSequence": {
                                        "startDate": "%s",
                                        "endDate": "%s"
                                    }
                                }
                                """.formatted( userId, carId, sequence.startDate(), sequence.endDate() ) )
                        .contentType( "application/json" )
                    )
        .andExpect( MockMvcResultMatchers.status().isOk() );

        mockMvc.perform( MockMvcRequestBuilders.get("/api/v1/bookings/user/%s".formatted(userId) ) )
                .andExpect( MockMvcResultMatchers.status().isOk() )
                .andExpect( MockMvcResultMatchers.jsonPath("$[0].userId").value(userId.toString()) )
                .andExpect( MockMvcResultMatchers.jsonPath("$[0].carId").value(carId.toString()) );
    }
}
