package org.base.protos;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ProtoTest extends TestCase {

    Person person;

    @Before
    public void initialize() {
        this.person = Person.newBuilder()
                .setEmail("hmkhan@gmail.com")
                .setId(0)
                .setName("Hassaan Moin Khan")
                .build();
    }


    @Test
    public void testPerson() {

        System.err.println(person.getName());

        // convert to bin

        // convert to json

    }

}
