package org.base.protos;

import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.protobuf.format.JsonFormat;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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

        System.err.println(this.person.getName());

    }

    // convert to bin
    @Test
    public void protoToBin() {
        System.err.println(this.person.toByteArray());

        try {
            Person p1 = Person.parseFrom(this.person.toByteArray());

            try {
                OutputStream out = new FileOutputStream("./protoTest.bin");
                this.person.writeDelimitedTo(out);
                this.person.writeDelimitedTo(out);
                System.out.println("Data is written to the file.");
                // Closes the output stream
                out.close();

                File initialFile = new File("./protoTest.bin");
                InputStream inputStream = new FileInputStream(initialFile);

                Person tmpPerson;
                while ((tmpPerson = Person.parseDelimitedFrom(inputStream)) != null) {
                    System.err.println(tmpPerson.getName());
                }

            } catch (Exception e) {
                e.getStackTrace();
            }

        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

    }

    // convert to json
    @Test
    public void protoToJson() {
        System.err.println(this.person.toByteString());

        JsonFormat jsonFormat = new JsonFormat();
        String json = jsonFormat.printToString(this.person);
        System.out.println("JSON: " + json);


        InputStream jsonStream = new ByteArrayInputStream(json.getBytes());
         Person.Builder builder = Person.newBuilder();
        try {
            jsonFormat.merge(jsonStream, builder);
            Person p1 = builder.build();

            System.err.println(p1.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
