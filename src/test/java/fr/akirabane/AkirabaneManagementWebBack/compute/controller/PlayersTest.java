package fr.akirabane.AkirabaneManagementWebBack.compute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.in.PlayersDtoIn;
import fr.akirabane.AkirabaneManagementWebBack.compute.dto.out.PlayersDtoOut;
import fr.akirabane.AkirabaneManagementWebBack.compute.service.PlayersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PlayersTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PlayersService playersService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPlayer() throws Exception {
        // Create a player DTO object to send in the request body
        PlayersDtoIn player = new PlayersDtoIn(null, "johndoe", "uuid123", "ok", 0);

        // Convert the player DTO to JSON
        String json = objectMapper.writeValueAsString(player);

        // Send a POST request to the addPlayer endpoint with the player DTO in the request body
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/apiv1/player/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        // Retrieve the response body as a string
        String responseBody = result.getResponse().getContentAsString();

        // Convert the response body from JSON to a PlayerDtoOut object
        PlayersDtoOut addedPlayer = objectMapper.readValue(responseBody, PlayersDtoOut.class);

        // Assert that the added player has the correct values
        assertEquals("johndoe", addedPlayer.getPseudo_player());
        assertEquals("uuid123", addedPlayer.getUuid_player());
        assertEquals(0, addedPlayer.getStaff());
    }

    @Test
    void deletePlayer() {

        PlayersDtoOut deletePlayer = playersService.getPlayerByUuid("uuid123");
        if(deletePlayer == null) {
            System.out.println("Le joueur n'existe pas");
        } else {
            assertNotNull(deletePlayer);
            assertEquals("johndoe", deletePlayer.getPseudo_player());
            assertEquals("uuid123", deletePlayer.getUuid_player());
            assertNotNull(deletePlayer.getPassword_player()); // vérifie que la valeur du mot de passe n'est pas nulle
            assertFalse(deletePlayer.getStaff() != null && deletePlayer.getStaff() != 0);
        }

    }

    @Test
    void updatePlayer() {
    }

    @Test
    void getAllPlayers() {
    }

    @Test
    void privatePage() {
    }

    @Test
    void publicpage() {
    }
}