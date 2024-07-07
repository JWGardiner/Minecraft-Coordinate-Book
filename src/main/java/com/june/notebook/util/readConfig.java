package com.june.notebook.util;

import com.june.notebook.Notebook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;

import static com.june.notebook.screens.menuScreen.*;

public class readConfig {
    public static String readCfg(String entry, int cfgLine) throws IOException {
        String dat = Files.readAllLines(Paths.get("config/vanilla-notebook/config.cfg")).get(cfgLine);
        return dat.substring(entry.length()+2);
    }
    public static void getBookmarkPage() throws IOException {
        String dat = Files.readAllLines(Paths.get("config/vanilla-notebook/bookmark.cfg")).get(1);
        bookmarkedpage = Integer.parseInt(dat);
    }
    public static void read() {
        Notebook.LOGGER.info("Reading config...");

        try {
            Notebook.pageLocation = readCfg("pagedirector", 2);
        } catch (IOException e) { throw new RuntimeException(e); }

        try {
            pageLimit = Integer.parseInt(readCfg("pagelimi", 4));
        } catch (IOException e) { throw new RuntimeException(e); }

        try {
            page = Integer.parseInt(readCfg("startpag", 6));
        } catch (IOException e) { throw new RuntimeException(e); }

        try {
            String preset = readCfg("preset", 8);
            Notebook.presetsEnabled = preset.toLowerCase(Locale.ROOT).equals("true");


        } catch (IOException e) { throw new RuntimeException(e); }

        try {
            getBookmarkPage();
        } catch (IOException e) { throw new RuntimeException(e); }
    }
}