package it.unisa.guardianhouse.activities;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;
import it.unisa.guardianhouse.fragments.AccountFragment;
import it.unisa.guardianhouse.fragments.HomeFragment;
import it.unisa.guardianhouse.fragments.SearchFragment;

public class NavigationDrawer extends MaterialNavigationDrawer implements MaterialSectionListener {

    private MaterialSection home;
    private MaterialSection search;
    private MaterialSection register;
    private MaterialSection login;
    private MaterialSection account;
    private MaterialSection logout;
    private MaterialSection aboutUs;
    private MaterialSection termsConds;

    @Override
    public void init(Bundle savedInstanceState) {

        home = newSection("Home", new HomeFragment());
        search = newSection("Ricerca", new SearchFragment());
        account = newSection("Account", new AccountFragment());

        this.addSection(this.home);
        this.addSection(this.search);
        this.addSection(this.account);

    }
}