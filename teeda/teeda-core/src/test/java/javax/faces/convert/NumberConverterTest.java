package javax.faces.convert;

import java.util.ArrayList;

import org.seasar.teeda.core.mock.MockUIComponent;

public class NumberConverterTest extends AbstractConverterTestCase {

    public void testConstants() throws Exception {
        assertEquals("javax.faces.Number", NumberConverter.CONVERTER_ID);
    }

    public void testGetAsObject_convertSuccess() throws Exception {
        NumberConverter converter = (NumberConverter) createConverter();
        converter.setType("number");
        Object o = converter.getAsObject(getFacesContext(),
                new MockUIComponent(), "99.9");
        assertNotNull(o);
        assertTrue(o instanceof Number);
        Number n = (Number) o;
        assertTrue(n.floatValue() == 99.9F);
    }

    public void testGetAsObject_convertFail() throws Exception {
        NumberConverter converter = (NumberConverter) createConverter();
        converter.setType("number");
        try {
            converter.getAsObject(getFacesContext(), new MockUIComponent(),
                    "aaa");
            fail();
        } catch (ConverterException expected) {
            success();
        }
    }

    public void testGetAsString_convertSuccess() throws Exception {
        NumberConverter converter = (NumberConverter) createConverter();
        converter.setType("number");
        String s = converter.getAsString(getFacesContext(),
                new MockUIComponent(), new Integer(123));
        assertEquals("123", s);
    }

    public void testGetAsString_convertFail() throws Exception {
        NumberConverter converter = (NumberConverter) createConverter();
        converter.setType("number");
        try {
            converter.getAsString(getFacesContext(), new MockUIComponent(),
                    new ArrayList());
            fail();
        } catch (ConverterException expected) {
            success();
        }
    }

    public void testGetAsObjectWithCurrency() throws Exception {
        NumberConverter converter = (NumberConverter) createConverter();
        converter.setType("currency");
        converter.setPattern("#,##0.000");
        double amount = 500000.123;
        Object o = converter.getAsObject(getFacesContext(),
                new MockUIComponent(), Double.toString(amount));
        assertEquals("500000.123", o.toString());
    }

    public void testGetAsStringWithCurrency() throws Exception {
        NumberConverter converter = (NumberConverter) createConverter();
        converter.setType("currency");
        converter.setPattern("#,##0.000");
        double amount = 500000.123456;
        String s = converter.getAsString(getFacesContext(),
                new MockUIComponent(), new Double(amount));
        System.out.println(s);
        assertEquals("500,000.123", s);
    }


    protected Converter createConverter() {
        return createNumberConverter();
    }

    private NumberConverter createNumberConverter() {
        return new NumberConverter();
    }
}
