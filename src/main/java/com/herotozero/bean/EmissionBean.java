package com.herotozero.bean;

import com.herotozero.dao.EmissionDAO;
import com.herotozero.entity.EmissionEntry;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class EmissionBean implements Serializable {

   @Inject
   private EmissionDAO dao;

   private List<EmissionEntry> entries;
   private EmissionEntry newEntry = new EmissionEntry();
   private String searchCountryCode;
   private EmissionEntry searchResult;
   private boolean searched = false;

   @PostConstruct
   public void init() {
        entries = dao.findAllApproved();
    }

   public void search() {
        searchResult = dao.findLatestByCountryCode(searchCountryCode);
        searched = true;
    }

   public void save() {
        newEntry.setApproved(false);
        dao.save(newEntry);
        newEntry = new EmissionEntry();
        entries = dao.findAllApproved();
    }

   public List<EmissionEntry> getEntries() { return entries; }
   public EmissionEntry getNewEntry() { return newEntry; }
   public void setNewEntry(EmissionEntry newEntry) { this.newEntry = newEntry; }

   public String getSearchCountryCode() { return searchCountryCode; }
   public void setSearchCountryCode(String searchCountryCode) { this.searchCountryCode = searchCountryCode; }
   public EmissionEntry getSearchResult() { return searchResult; }
    public boolean isSearched() { return searched; }
}