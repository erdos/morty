package io.github.erdos.stencil.standalone;

import org.junit.Test;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class JsonParserTest {

    @Test
    public void testReadStr() throws IOException {
        final String input = "\"asdf\"1";
        PushbackReader pbr = pbr(input);
        final String out = JsonParser.readStr(pbr);

        assertEquals("asdf", out);
        assertEquals('1', pbr.read());
    }

    @Test
    public void expectWordTest() throws IOException {
        final String input = "Alabama";
        JsonParser.expectWord(input, pbr(input));
    }

    @Test(expected = IllegalStateException.class)
    public void expectWordFailureTest() throws IOException {
        final String input = "Alabama";
        JsonParser.expectWord("Alibaba", pbr(input));
    }

    @Test(expected = IllegalStateException.class)
    public void expectWordFailureTest2() throws IOException {
        final String input = "Alab";
        JsonParser.expectWord("Alabama", pbr(input));
    }

    @Test
    public void readVecTestEmpty() throws IOException {
        final String input = "[]";
        final Object result = JsonParser.readVec(pbr(input));
        assertEquals(emptyList(), result);
    }

    @Test
    public void readVecTestUnit() throws IOException {
        final String input = "[1]";
        final Object result = JsonParser.readVec(pbr(input));
        assertEquals(singletonList(BigDecimal.ONE), result);
    }

    @Test
    public void readVecTestSimple() throws IOException {
        final String input = "[1,10]";
        final Object result = JsonParser.readVec(pbr(input));
        assertEquals(asList(BigDecimal.ONE, BigDecimal.TEN), result);
    }

    @Test
    public void readNumberTest() throws IOException {
        final String input = "123456789.123456789";
        final Number result = JsonParser.readNumber(pbr(input));
        assertEquals(new BigDecimal(input), result);
    }

    @Test
    public void readNumberTest2() throws IOException {
        final String input = "123456789.123456789xyz";
        PushbackReader pbr = pbr(input);

        final Number result = JsonParser.readNumber(pbr);
        assertEquals(new BigDecimal("123456789.123456789"), result);
        assertEquals((Character) 'x', (Character) (char) pbr.read());
    }

    private static PushbackReader pbr(String s) {
        return new PushbackReader(new StringReader(s), 32);
    }
}
