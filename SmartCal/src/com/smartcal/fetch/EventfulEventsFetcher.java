package com.smartcal.fetch;

import java.util.List;

import com.evdb.javaapi.APIConfiguration;
import com.evdb.javaapi.EVDBAPIException;
import com.evdb.javaapi.EVDBRuntimeException;
import com.evdb.javaapi.data.Event;
import com.evdb.javaapi.data.SearchResult;
import com.evdb.javaapi.data.request.EventSearchRequest;
import com.evdb.javaapi.operations.EventOperations;

public class EventfulEventsFetcher implements EventFetcher{

  private static final String APP_KEY = "tn5Rmwfp2zzBxfwX";
  private static final String APP_USERNAME = "GorjanZ";
  private static final String APP_PASSWORD = "EventFul@123";
  
  public List<Event> fetch(String query, String location, int limit) {

    APIConfiguration.setEvdbUser(APP_USERNAME);
    APIConfiguration.setEvdbPassword(APP_PASSWORD);
    APIConfiguration.setApiKey(APP_KEY);

    EventSearchRequest req = new EventSearchRequest();
    req.setPageSize(limit);
    req.setKeywords(query);
    req.setLocation(location);
    req.setLocationRadius(10);
    EventOperations op = new EventOperations();
    SearchResult result = null;
    try {
      result = op.search(req);
    } catch (EVDBRuntimeException e) {
      e.printStackTrace();
    } catch (EVDBAPIException e) {
      e.printStackTrace();
    }
    List<Event> eventfulListe = result.getEvents();

    for (Event event : eventfulListe) {
      System.out.println(event.getTitle());
      // System.out.println(event.getDescription());
      System.out.println(event.getURL());
      System.out.println(event.getVenueAddress());
      System.out.println(event.getVenueLatitude());
      System.out.println(event.getVenueLongitude());
      System.out.println();
    }

    return eventfulListe;
  }
  
}
