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
public class AdminBean implements Serializable {

   @Inject
    private EmissionDAO dao;

    private List<EmissionEntry> pendingEntries;

   @PostConstruct
    public void init() {
        pendingEntries = dao.findAllPending();
    }

    public String approve(Long id) {
        dao.approve(id);
        pendingEntries = dao.findAllPending();
        return null;
    }

    public List<EmissionEntry> getPendingEntries() { return pendingEntries; }
}
