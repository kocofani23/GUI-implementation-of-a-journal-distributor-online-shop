package tests;

import static org.junit.Assert.*;
import org.junit.*;
import term_project.DateInfo;

public class DateInfoTest {
	
	private DateInfo dateInfo;

    @Before
    public void setUp() {
        dateInfo = new DateInfo(01, 11, 2023);
    }

    @Test
    public void testStartMonth() {
        dateInfo.setStartMonth(5);
        assertEquals(5, dateInfo.getStartMonth());
    }

    @Test
    public void testStartYear() {
        dateInfo.setStartYear(2022);
        assertEquals(2022, dateInfo.getStartYear());
    }

    @Test
    public void testEndMonth() {
        dateInfo.setEndMonth(12);
        assertEquals(12, dateInfo.getEndMonth());
    }
    
    @Test
    public void testEndMonthAfterMultipleChanges() {
        dateInfo.setEndMonth(6);
        assertEquals(6, dateInfo.getEndMonth());

        dateInfo.setEndMonth(9);
        assertEquals(9, dateInfo.getEndMonth());
    }
    
    @Test
    public void testDateInfoGettersAndSetters() {
        DateInfo dateInfo = new DateInfo(1, 2023, 12);
        dateInfo.setStartMonth(5);
        dateInfo.setStartYear(2022);
        dateInfo.setEndMonth(10);
        assertEquals(5, dateInfo.getStartMonth());
        assertEquals(2022, dateInfo.getStartYear());
        assertEquals(10, dateInfo.getEndMonth());
    }

    @Test
    public void testDateInfoToString() {
        DateInfo dateInfo = new DateInfo(3, 2022, 6);
        assertEquals("DateInfo [startMonth=3, startYear=2022, endMonth=6]", dateInfo.toString());
    }

    @Test
    public void testDateInfoValidity() {
        DateInfo dateInfo = new DateInfo(1, 2023, 12);
        assertEquals(1, dateInfo.getStartMonth());
        assertEquals(2023, dateInfo.getStartYear());
        assertEquals(12, dateInfo.getEndMonth());

        dateInfo.setStartMonth(0);
        dateInfo.setStartYear(2024);
        dateInfo.setEndMonth(13);

        assertEquals(0, dateInfo.getStartMonth());
        assertEquals(2024, dateInfo.getStartYear());
        assertEquals(13, dateInfo.getEndMonth());
    }
}
