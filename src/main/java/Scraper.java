import java.io.IOException;
import java.util.NoSuchElementException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper {
    public String search(String searchQuery) {
        if (searchQuery.equals("exit")) {
            return "";
        }

        StringBuilder builder = new StringBuilder();

        try {
            Document doc = Jsoup.connect("https://www.basketball-reference.com/search/search.fcgi?search="
             + searchQuery).get();

            Elements queries = doc.getElementsByClass("search-item-url");
            if (queries.size() == 0)
                throw new NoSuchElementException();

            String playerQuery = queries.get(0).text();
            if (!playerQuery.contains("player"))
                throw new NoSuchElementException();

            doc = Jsoup.connect("https://www.basketball-reference.com/"
                    + playerQuery).get();

            Element table = doc.getElementById("per_game");
            Elements rows = table.select("tr");

            Element name = doc.getElementById("info");
            builder.append(name.select("h1[itemprop]").text());
            builder.append("\n");

            for (Element row : rows) {
                if (row.select("th[data-stat=season]").text().equals("Season") ||
                        row.select("th[data-stat=season]").text().equals("")) {
                    continue;
                }
                builder.append(row.select("th[data-stat=season]").text());
                builder.append(" -> ").append(row.select("td[data-stat=fg3_per_g]").text());
                builder.append("\n");
            }
        } catch (IOException e) {
            return ("Issue with getting website\n");
        } catch (NoSuchElementException e) {
            return ("Player not found, try entering their full name\n");
        }

        return builder.toString();
    }

}
