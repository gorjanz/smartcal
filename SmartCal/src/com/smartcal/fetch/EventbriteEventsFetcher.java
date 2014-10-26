package com.smartcal.fetch;

import java.util.List;

import com.evdb.javaapi.data.Event;


public class EventbriteEventsFetcher implements EventFetcher {

  public static final String APP_KEY = "2NYN33MPKSUMLZQES2";
  public static final String APP_CLIENT_SECRET = "C3W6JG7DAFWLDJT5L6JQTUOGQHHTAYNNBBI2LDY4LVB5WCUFPZ";
  public static final String APP_PERSONAL_OAUTH_TOKEN = "7HYQC7MIKBKRMAUYZDUA";
  public static final String APP_ANONYMOUS_OAUTH_TOKEN = "GHWCPMLGSXBPGMWVM447";
  
  
  public List<Event> fetch(String query, String location, int limit) {
    return null;
  }

}
