package com.aacademy.advanceacademy.service.impl;

import com.aacademy.advanceacademy.model.User;
import com.aacademy.advanceacademy.repository.UserRepository;
import com.aacademy.advanceacademy.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    public void verifyFindAll() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(User.builder().build()));
        userServiceImpl.findAll();
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void verifyFindByEmail() {
        Set<User> list = new HashSet<>(Collections.singletonList(User.builder().build()));
        when(userRepository.findByEmail(any(String.class)))
                .thenReturn(list);
        String email = any(String.class);
        userServiceImpl.findByEmail(email);
        verify(userRepository, times(list.size())).findByEmail(email);
    }

    @Test
    public void verifyFindById() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(User.builder().build()));
        userServiceImpl.findById(any(Long.class));
        verify(userRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void verifySave() {
        User userSave = User.builder().build();
        when(userRepository.save(any(User.class))).thenReturn(userSave);
        userServiceImpl.save(userSave);
        verify(userRepository, times(1)).save(userSave);
    }

    @Test
    public void verifyDeleteById() {
        doNothing().when(userRepository).deleteById(any(Long.class));

        userServiceImpl.deleteById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    public void verifyDeleteByEmail() {
        doNothing().when(userRepository).deleteByEmail(any(String.class));

        String email = any(String.class);

        userServiceImpl.deleteByEmail(email);

        verify(userRepository, times(1)).deleteByEmail(email);
    }
}
