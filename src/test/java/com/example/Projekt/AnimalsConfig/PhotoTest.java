package com.example.Projekt.AnimalsConfig;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PhotoTests {

    @Mock
    private PhotoRepository photoRepository;

    @InjectMocks
    private PhotoService photoService;

    @InjectMocks
    private PhotoController photoController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(photoController).build();
    }

    @Test
    void testSavePhoto() {
        Photo photo = new Photo();
        photo.setId(1L);

        photoService.savePhoto(photo);

        verify(photoRepository, times(1)).save(photo);
    }

    @Test
    void testGetPhotoById_Success() throws Exception {
        byte[] imageData = new byte[]{1, 2, 3, 4};
        Photo photo = new Photo();
        photo.setId(1L);
        photo.setImageData(imageData);

        when(photoRepository.findById(1L)).thenReturn(Optional.of(photo));

        mockMvc.perform(get("/photos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(imageData));
    }

    @Test
    void testGetPhotoById_NotFound() throws Exception {
        when(photoRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/photos/1"))
                .andExpect(status().isNotFound());
    }

}
