package itest.com.kg.spring.jpa.service;

import com.kg.spring.jpa.Application;
import com.kg.spring.jpa.controller.StockQuoteController;
import com.kg.spring.jpa.controller.SummarizedDataController;
import com.kg.spring.jpa.model.SummarizedData;
import com.kg.spring.jpa.service.StockQuoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, SummarizedDataController.class})
@TestPropertySource(locations="classpath:test.properties")
@WebAppConfiguration
public class SummarizedDataControllerTest {

    @Autowired
    public  StockQuoteService stockQuoteService ;

    @Autowired
    public StockQuoteController stockQuoteController;

    @Autowired
    public SummarizedDataController summarizedDataController;

    private static boolean dataLoaded = false;

    @Before
    public void before(){
        if(!dataLoaded) {
            stockQuoteController.saveAll();
            dataLoaded = true;
        }
    }

    @Test
    public void testByDateClosingPrice(){
        try {
            SummarizedData summarizedData = summarizedDataController.getSummarizedDataByDate("PVTL", "2018-06-25");
            assertEquals(Double.doubleToLongBits(200.87),
                    Double.doubleToLongBits(summarizedData.getClosingPrice()));

        }
        catch (Exception e){
            fail("Exception thrown.....");
        }
    }

    @Test
    public void testByDateNotFound(){
        try {
            SummarizedData summarizedData = summarizedDataController.getSummarizedDataByDate("PVTL", "2018-06-28");
            assertEquals(Double.doubleToLongBits(0),
                    Double.doubleToLongBits(summarizedData.getClosingPrice()));

        }
        catch (Exception e){
            fail("Exception thrown.....");
        }
    }

    @Test
    public void testByDateTotalVolume(){
        try {
            SummarizedData summarizedData = summarizedDataController.getSummarizedDataByDate("PVTL", "2018-06-25");
            assertEquals(2681, summarizedData.getTotalVolume());

        }
        catch (Exception e){
            fail("Exception thrown.....");
        }
    }

    @Test
    public void testByMonthClosingPrice(){
        try {
            SummarizedData summarizedData = summarizedDataController.getSummarizedDataByMonth("AAPl", "2018-06");
            assertEquals(Double.doubleToLongBits(182.84),
                    Double.doubleToLongBits(summarizedData.getClosingPrice()));

        }
        catch (Exception e){
            fail("Exception thrown.....");
        }
    }

    @Test
    public void testByMonthNotFound(){
        try {
            SummarizedData summarizedData = summarizedDataController.getSummarizedDataByMonth("AAPl", "2018-07");
            assertEquals(Double.doubleToLongBits(0),
                    Double.doubleToLongBits(summarizedData.getClosingPrice()));

        }
        catch (Exception e){
            fail("Exception thrown.....");
        }
    }

    @Test
    public void testByMonthTotalVolume(){
        try {
            SummarizedData summarizedData = summarizedDataController.getSummarizedDataByMonth("AAPl", "2018-06");
            assertEquals(4268, summarizedData.getTotalVolume());

        }
        catch (Exception e){
            fail("Exception thrown.....");
        }
    }
}
