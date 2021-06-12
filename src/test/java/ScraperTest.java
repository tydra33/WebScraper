import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScraperTest {
    Scraper scraper;

    @BeforeEach
    void init(){
        scraper = new Scraper();
    }

    @Test
    void Test_Opens_Site() {
        scraper.search("Luka Doncic");
    }

    @Test
    void Test_No_Such_Player() {
        assertEquals("Player not found, try entering their full name\n", scraper.search("Ardit Nela"));
    }

    @Test
    void Test_Luka_Doncic() {
        assertEquals("Luka Dončić\n" +
                "2018-19 -> 2.3\n" +
                "2019-20 -> 2.8\n" +
                "2020-21 -> 2.9\n" +
                "Career -> 2.7\n", scraper.search("Luka Doncic"));
    }

    @Test
    void Test_Exit() {
        assertEquals("", scraper.search("exit"));
    }
}