package org.data.busisness;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
public class CassandraServiceTest {

  /*  @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CassandraService cassandraService;

    @Test
    public void serviceTEst(){
        User u = new User(1,2,"ss","dd");
        User u2 = new User(2,3,"uu","vv");

        List<User> listUser= new ArrayList<>();
        listUser.add(u);
        listUser.add(u2);

        when(userRepository.findAll()).thenReturn(listUser);
        List<User> otherUsers = cassandraService.getAllUsers();
        assertEquals(otherUsers, listUser);


    }
*/
}
