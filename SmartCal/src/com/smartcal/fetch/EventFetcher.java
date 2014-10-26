package com.smartcal.fetch;

import java.util.List;

import com.evdb.javaapi.data.Event;


public interface EventFetcher {

  public List<Event> fetch(String query, String location, int limit);
  
}
