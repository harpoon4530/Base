package org.base.parser;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.base.protos.Person;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void testJSONObjectToProto() {

        String data = "{\"id\":\"2ec069e3-576f-48eb-869f-74a540ef840c\",\"name\":\"Acai Bowl\",\"temp\":\"cold\",\"shelfLife\":249,\"decayRate\":0.3}";
        JSONObject jsonObject = new JSONObject(data);

        Person.Order.Builder orderBuilder = Person.Order.newBuilder();

        try {
            JsonFormat.parser().merge(data, orderBuilder);
        } catch (InvalidProtocolBufferException e) {
            int i = 0;
            i++;
            throw new RuntimeException(e);
        }

        Assert.assertEquals("2ec069e3-576f-48eb-869f-74a540ef840c", orderBuilder.getId());
        Assert.assertEquals(Person.Temp.cold, orderBuilder.getTemp());
        System.err.println(orderBuilder.getTemp());
        // TODO: add more assertions here!
    }

    @Test
    public void testGetNItems() {
        int totalItems = 132;
        Parser parser = new Parser();
        List<Person.Order> items;


        for (int i = 0; i < totalItems/ 2; i++) {
            items = parser.getNextNOrders();
            Assert.assertEquals(2, items.size());
        }

        // tests getting items when list has been emptied!
        items = parser.getNextNOrders();
        Assert.assertEquals(0, items.size());
    }

    // TODO: write a test case where fewer than "N" items has been picked up!

}