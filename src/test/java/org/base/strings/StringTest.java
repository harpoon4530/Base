package org.base.strings;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class StringTest {

    @Test
    public void testStringUtil() {
        System.out.println("Hello world!!!");

        boolean blank = StringUtils.isBlank(" ");

        Assert.assertEquals(true, blank);

    }

}