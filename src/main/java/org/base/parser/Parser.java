package org.base.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Singleton;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.base.protos.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Singleton
public class Parser {

    private static final Logger logger = LoggerFactory.getLogger(Parser.class);

    private static final String filePath = "./src/main/resources/orders.json";

    private final List<Person.Order> orderList;
    private Iterator<Person.Order> orderIterator;

    public Parser() {
        this.orderList = new ArrayList();

        try {
            readDataIntoArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

        this.orderIterator = orderList.iterator();
    }


    // Read the file and load the orders into a List
    public void readDataIntoArray() throws FileNotFoundException, InvalidProtocolBufferException {

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(br).getAsJsonArray();

        for(int i = 0; i < jsonArray.size(); i++) {
            JsonObject jObj = (JsonObject) jsonArray.get(i);
            String jsonStr = jObj.toString();

            Person.Order.Builder orderBuilder = Person.Order.newBuilder();

            JsonFormat.parser().merge(jsonStr, orderBuilder);

            //orderBuilder.setPickupTime(Pruner.getPickupTime());
            orderBuilder.setOrderInsertTime(System.currentTimeMillis());

            orderList.add(orderBuilder.build());
        }

        logger.info("Loaded a total of {} itmes from the file", orderList.size());
    }

    public List<Person.Order> getNextNOrders() {

        logger.info("Picking up the next: {} items", 2);
        List<Person.Order> retList = new ArrayList();

        int count = 0;
        while(orderIterator.hasNext() && count < 2) {
            retList.add(orderIterator.next());
            count++;
        }
        logger.info("Picked up {} items", retList.size());
        return retList;
    }
}
