package com.example.proiectandrada;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EntryParser {
    private static final String ID = "id";
    private static final String ENTRYTEXT = "entryText";
    private static final String ALEGERE = "alegere";

    public static List<Entry> parsareJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsareEntries(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Entry> parsareEntries(JSONArray jsonArray) throws JSONException {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            entries.add(parsareEntry(jsonArray.getJSONObject(i)));
        }
        return entries;
    }

    private static Entry parsareEntry(JSONObject jsonObject) throws JSONException {

        Long id = jsonObject.has(ID) ? jsonObject.getLong(ID) : null;
        String entryText = jsonObject.getString(ENTRYTEXT);
        String alegereString = jsonObject.getString(ALEGERE);
        Mood alegere = Mood.valueOf(alegereString);

        Entry entry = new Entry(entryText, alegere);
        entry.setId(id);
        return entry;

    }
}
