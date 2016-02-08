package org.pac4j.core.util;

import org.junit.Test;
import org.pac4j.core.exception.TechnicalException;

import static org.junit.Assert.*;

/**
 * This class tests the {@link CommonHelper} class.
 *
 * @author Jerome Leleu
 * @since 1.4.0
 */
public final class CommonHelperTests {

    private static final String URL_WITHOUT_PARAMETER = "http://host/app";

    private static final String URL_WITH_PARAMETER = "http://host/app?param=value";

    private static final String NAME = "name";

    private static final String VALUE = "va+l+ue";

    private static final String ENCODED_VALUE = "va%2Bl%2Bue";

    private static final Class<?> CLAZZ = String.class;

    private static final String CLASS_NAME = String.class.getSimpleName();

    @Test
    public void testIsNotBlankNull() {
        assertFalse(CommonHelper.isNotBlank(null));
    }

    @Test
    public void testIsNotBlankEmply() {
        assertFalse(CommonHelper.isNotBlank(""));
    }

    @Test
    public void testIsNotBlankBlank() {
        assertFalse(CommonHelper.isNotBlank("     "));
    }

    @Test
    public void testIsNotBlankNotBlank() {
        assertTrue(CommonHelper.isNotBlank(NAME));
    }

    @Test
    public void testAssertNotBlankBlank() {
        try {
            CommonHelper.assertNotBlank(NAME, "");
            fail("must throw an ClientException");
        } catch (final TechnicalException e) {
            assertEquals(NAME + " cannot be blank", e.getMessage());
        }
    }

    @Test
    public void testAssertNotBlankNotBlank() {
        CommonHelper.assertNotBlank(NAME, VALUE);
    }

    @Test
    public void testAssertNotNullNull() {
        try {
            CommonHelper.assertNotNull(NAME, null);
            fail("must throw an ClientException");
        } catch (final TechnicalException e) {
            assertEquals(NAME + " cannot be null", e.getMessage());
        }
    }

    @Test
    public void testAssertNotNullNotNull() {
        CommonHelper.assertNotNull(NAME, VALUE);
    }

    @Test
    public void testAddParameterNullUrl() {
        assertNull(CommonHelper.addParameter(null, NAME, VALUE));
    }

    @Test
    public void testAddParameterNullName() {
        assertEquals(URL_WITH_PARAMETER, CommonHelper.addParameter(URL_WITH_PARAMETER, null, VALUE));
    }

    @Test
    public void testAddParameterNullValue() {
        assertEquals(URL_WITH_PARAMETER + "&" + NAME + "=", CommonHelper.addParameter(URL_WITH_PARAMETER, NAME, null));
    }

    @Test
    public void testAddParameterWithParameter() {
        assertEquals(URL_WITH_PARAMETER + "&" + NAME + "=" + ENCODED_VALUE,
                CommonHelper.addParameter(URL_WITH_PARAMETER, NAME, VALUE));
    }

    @Test
    public void testAddParameterWithoutParameter() {
        assertEquals(URL_WITHOUT_PARAMETER + "?" + NAME + "=" + ENCODED_VALUE,
                CommonHelper.addParameter(URL_WITHOUT_PARAMETER, NAME, VALUE));
    }

    @Test
    public void testToStringNoParameter() {
        assertEquals("<" + CLASS_NAME + "> |", CommonHelper.toString(CLAZZ));
    }

    @Test
    public void testToStringWithParameter() {
        assertEquals("<" + CLASS_NAME + "> | " + NAME + ": " + VALUE + " |", CommonHelper.toString(CLAZZ, NAME, VALUE));
    }

    @Test
    public void testToStringWithParameters() {
        assertEquals("<" + CLASS_NAME + "> | " + NAME + ": " + VALUE + " | " + NAME + ": " + VALUE + " |",
                CommonHelper.toString(CLAZZ, NAME, VALUE, NAME, VALUE));
    }

    @Test
    public void testAreEqualsBothNull() {
        assertTrue(CommonHelper.areEquals(null, null));
    }

    @Test
    public void testAreEqualsOneIsNull() {
        assertFalse(CommonHelper.areEquals(VALUE, null));
    }

    @Test
    public void testAreEqualsDifferentValue() {
        assertFalse(CommonHelper.areEquals(NAME, VALUE));
    }

    @Test
    public void testAreEqualsSameValue() {
        assertTrue(CommonHelper.areEquals(VALUE, VALUE));
    }

    @Test(expected = TechnicalException.class)
    public void testAssertNotBlank_null() {
        String var = null;
        CommonHelper.assertNotBlank("var", var);
    }
  
    @Test(expected = TechnicalException.class)
    public void testAssertNotBlank_empty() {
        String var = " ";
        CommonHelper.assertNotBlank("var", var);
    }

    @Test
    public void testAssertNotBlank_notBlank() {
        String var = "contents";
        CommonHelper.assertNotBlank("var", var);
    }

    @Test(expected = TechnicalException.class)
    public void testAssertNotNull_null() {
        String var = null;
        CommonHelper.assertNotNull("var", var);
    }

    @Test
    public void testAssertNotNull_notBlank() {
        String var = "contents";
        CommonHelper.assertNotNull("var", var);
    }

    @Test
    public void testAssertNull_null() {
        CommonHelper.assertNull("var", null);
    }

    @Test(expected = TechnicalException.class)
    public void testAssertNull_notNull() {
        CommonHelper.assertNull("var", "notnull");
    }
}