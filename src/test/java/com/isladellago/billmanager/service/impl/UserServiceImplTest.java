package com.isladellago.billmanager.service.impl;

import com.isladellago.billmanager.TestUtils;
import com.isladellago.billmanager.domain.model.User;
import com.isladellago.billmanager.domain.model.UserRepository;
import com.isladellago.billmanager.exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public final void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public final void testGetUserByEmail() {
        Mockito.when(userRepository.findByEmail(TestUtils.USER_EMAIL))
                .thenReturn(Optional.of(User.builder().build()));

        final User user = userService.getUserByEmail(TestUtils.USER_EMAIL);

        Assert.assertNotNull(user);
    }

    @Test(expected = UserNotFoundException.class)
    public final void testGetUserByEmailUserDoNotExists() {
        Mockito.when(userRepository.findByEmail(TestUtils.USER_EMAIL))
                .thenReturn(Optional.empty());

        userService.getUserByEmail(TestUtils.USER_EMAIL);
    }
}
