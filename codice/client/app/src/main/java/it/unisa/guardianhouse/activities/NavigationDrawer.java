package it.unisa.guardianhouse.activities;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;
import it.unisa.guardianhouse.fragments.SearchFragment;

public class NavigationDrawer extends MaterialNavigationDrawer implements MaterialSectionListener {

    private MaterialSection search;

    @Override
    public void init(Bundle savedInstanceState) {

        search = newSection("Ricerca", new SearchFragment());

        this.addSection(this.search);

    }
}