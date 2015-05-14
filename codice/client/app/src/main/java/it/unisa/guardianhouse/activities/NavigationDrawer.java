package it.unisa.guardianhouse.activities;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;
import it.unisa.guardianhouse.fragments.AccountFragment;
import it.unisa.guardianhouse.fragments.GetApartmentFragment;
import it.unisa.guardianhouse.fragments.GetReviewFragment;
import it.unisa.guardianhouse.fragments.GetUsersFragment;
import it.unisa.guardianhouse.fragments.HomeFragment;
import it.unisa.guardianhouse.fragments.LoginFragment;
import it.unisa.guardianhouse.fragments.RegisterFragment;
import it.unisa.guardianhouse.fragments.SearchFragment;
import it.unisa.guardianhouse.fragments.MapTestFragment;

public class NavigationDrawer extends MaterialNavigationDrawer implements MaterialSectionListener {

    private MaterialSection home;
    private MaterialSection search;
    private MaterialSection register;
    private MaterialSection login;
    private MaterialSection account;
    private MaterialSection getUsers;
    private MaterialSection getApartment;
    private MaterialSection getReview;
    private MaterialSection mapTest;


    private MaterialSection logout;
    private MaterialSection aboutUs;
    private MaterialSection termsConds;

    @Override
    public void init(Bundle savedInstanceState) {

        home = newSection("Home", new HomeFragment());
        search = newSection("Ricerca", new SearchFragment());
        account = newSection("Account", new AccountFragment());
        register = newSection("Register", new RegisterFragment());
        login = newSection("Login", new LoginFragment());
        getUsers = newSection("Utenti", new GetUsersFragment());
        getApartment = newSection("Appartamento", new GetApartmentFragment());
        getReview = newSection("Recensione", new GetReviewFragment());
        mapTest = newSection("Google Maps Test", new MapTestFragment());

        this.addSection(this.home);
        this.addSection(this.search);
        this.addSection(this.account);
        this.addSection(this.register);
        this.addSection(this.login);
        this.addSection(this.getUsers);
        this.addSection(this.getApartment);
        this.addSection(this.getReview);
        this.addSection(this.mapTest);

    }
}