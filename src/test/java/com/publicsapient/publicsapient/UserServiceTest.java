package com.publicsapient.publicsapient;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.publicsapient.publicsapient.Exception.ResourceNotFoundException;
import com.publicsapient.publicsapient.Model.APIUser;
import com.publicsapient.publicsapient.Payload.APIUserDTO;
import com.publicsapient.publicsapient.Payload.ResponseDTO;
import com.publicsapient.publicsapient.Repository.UserRepository;
import com.publicsapient.publicsapient.Service.UserService;
import com.publicsapient.publicsapient.Service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testLoadDataSuccess() {
        Map<String, Object> userJson = Map.of(
                "firstName", "Emily",
                "lastName", "Johnson",
                "email", "emily@example.com",
                "ssn", "3222-4444",
                "role", "admin",
                "age", 28,
                "gender", "female"
        );

        Map<String, Object> responseBody = Map.of("users", List.of(userJson));
        ResponseEntity<Map> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(restTemplate.getForEntity(anyString(), eq(Map.class)))
                .thenReturn(responseEntity);

        String result = userService.loadData();
        assertEquals("Data loaded successfully", result);

        verify(jdbcTemplate, times(2)).execute(anyString());
        verify(userRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void findUserByIdTestSuccess() {

        APIUser user = new APIUser(
                1L, "Emily", "Johnson", 
                "emily.johnson@x.dummyjson.com",
                "+81 965-431-3024",
                28, "admin", "female"
        );

        APIUserDTO userDTO = new APIUserDTO(
                1L, "Emily", "Johnson", 
                "emily.johnson@x.dummyjson.com",
                "+81 965-431-3024",
                28, "admin", "female"
        );

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(modelMapper.map(user, APIUserDTO.class))
                .thenReturn(userDTO);
        
        
        APIUserDTO result = userService.findUserById(1L);

        assertEquals(user.getFirstName(), result.getFirstName());
        assertEquals(user.getLastName(), result.getLastName());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getSsn(), result.getSsn());
        assertEquals(user.getAge(), result.getAge());
        assertEquals(user.getRole(), result.getRole());
        assertEquals(user.getGender(), result.getGender());
    
      
    }

    @Test
    public void testFindUserByIdNotFound() {
        when(userRepository.findById(123L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.findUserById(123L));
    }


    @Test
    public void testFindByEmailSuccess() {
        APIUser user = new APIUser();
        user.setEmail("emily.johnson@x.dummyjson.com");

        APIUserDTO userDTO = new APIUserDTO();
        userDTO.setEmail("emily.johnson@x.dummyjson.com");

        when(userRepository.findByEmail("emily.johnson@x.dummyjson.com"))
                .thenReturn(user);

        when(modelMapper.map(user, APIUserDTO.class))
                .thenReturn(userDTO);

        APIUserDTO result = userService.findUserByEmail("emily.johnson@x.dummyjson.com");
        assertEquals("emily.johnson@x.dummyjson.com", result.getEmail());
    }


    @Test
    public void testFindByEmailNotFound() {
        when(userRepository.findByEmail("son@x.dummyjson.com"))
                .thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> userService.findUserByEmail("son@x.dummyjson.com"));
    }

    @Test
    public void testFindByKeywordSuccess() {
        APIUser user = new APIUser();
        user.setFirstName("Emily");
        APIUserDTO userDTO = new APIUserDTO();
        userDTO.setFirstName(user.getFirstName());

        when(userRepository.findByKeyword("emi"))
                .thenReturn(List.of(user));

        when(modelMapper.map(user, APIUserDTO.class))
                .thenReturn(userDTO);

        ResponseDTO result = userService.findByKeyword("emi");
        assertEquals(1, result.getContent().size());
    }


    @Test
    public void testFindByKeywordEmpty() {
        when(userRepository.findByKeyword("zz"))
                .thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.findByKeyword("zz"));
    }


    @Test
    public void testFindByKeywordNull() {
        when(userRepository.findByKeyword(""))
                .thenReturn(null);

        assertThrows(ResourceNotFoundException.class,
                () -> userService.findByKeyword(""));
    }



    
}
