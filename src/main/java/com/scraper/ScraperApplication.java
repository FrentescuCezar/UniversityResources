package com.scraper;

import com.database.DataBaseConnection;

public class ScraperApplication {
    public static void main(String[] args){
        TimeTableScraper scraper = new TimeTableScraper();

        scraper.startScrape();

    }
}
