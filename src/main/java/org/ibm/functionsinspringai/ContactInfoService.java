package org.ibm.functionsinspringai;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
public class ContactInfoService implements Function<ContactInfoService.Request, ContactInfoService.Response> {
    private static final Logger log = LoggerFactory.getLogger(ContactInfoService.class);
    private static final String URL = "https://www.gsa.gov/about-us/contact-us/contact-information-for-inventory-of-owned-and-lea";

    @Override
    public Response apply(Request request) {
        log.info("Contact Info Request: {}", request);

        try {
            Document doc = Jsoup.connect(URL).get();

            Elements rows = doc.select("table[summary='Contact information for Inventory of Owned and Leased Properties'] tbody tr");
            List<ContactInfo> contacts = new ArrayList<>();

            for (Element row : rows) {
                Elements columns = row.select("td");
                if (columns.size() == 4) {
                    String region = columns.get(0).text();
                    String name = columns.get(1).text();
                    Element contactInfoColumn = columns.get(2);
                    String states = columns.get(3).text();

                    String phone = contactInfoColumn.select("a[href^=tel]").first() != null
                            ? contactInfoColumn.select("a[href^=tel]").first().text()
                            : "";
                    String email = contactInfoColumn.select("a[href^=mailto]").text();

                    ContactInfo contact = new ContactInfo(region, name, phone, email, states);
                    contacts.add(contact);
                }
            }

            Response response = new Response(contacts);
            log.info("Contact Info Response: {}", response);
            return response;
        } catch (IOException e) {
            log.error("Error scraping webpage", e);
            return new Response(new ArrayList<>());
        }
    }

    public record Request(String dummy) {} // You might not need any input for this function
    public record Response(List<ContactInfo> contacts) {}
    public record ContactInfo(String region, String name, String phone, String email, String states) {}
}