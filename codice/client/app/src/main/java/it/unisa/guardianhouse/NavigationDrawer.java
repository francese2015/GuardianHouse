package it.unisa.guardianhouse;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;
import it.unisa.guardianhouse.fragments.AccountFragment;
import it.unisa.guardianhouse.fragments.GetApartmentFragment;
import it.unisa.guardianhouse.fragments.GetReviewFragment;
import it.unisa.guardianhouse.fragments.HomeFragment;
import it.unisa.guardianhouse.fragments.LoginFragment;
import it.unisa.guardianhouse.fragments.RegisterFragment;
import it.unisa.guardianhouse.fragments.SearchFragment;
import it.unisa.guardianhouse.fragments.MapTestFragment;

public class NavigationDrawer extends MaterialNavigationDrawer implements MaterialSectionListener {

    private MaterialAccount account;
    private MaterialSection home;
    private MaterialSection search;
    private MaterialSection register;
    private MaterialSection login;
    private MaterialSection accountPage;
    //private MaterialSection logout;
    //private MaterialSection aboutUs;
    //private MaterialSection termsConds;
    private MaterialSection getApartment;
    private MaterialSection getReview;
    private MaterialSection mapTest;


    @Override
    public void init(Bundle savedInstanceState) {

        this.account = new MaterialAccount(this.getResources(), "Guardian House", "Il tuo angelo custode!", R.drawable.app_logo_big, R.drawable.bg);
        addAccount(this.account);

        home = newSection("Home", new HomeFragment());
        search = newSection("Ricerca", new SearchFragment());
        accountPage = newSection("Account", new AccountFragment());
        register = newSection("Register", new RegisterFragment());
        login = newSection("Login", new LoginFragment());
        getApartment = newSection("Appartamento", new GetApartmentFragment());
        getReview = newSection("Recensione", new GetReviewFragment());
        mapTest = newSection("Google Maps Test", new MapTestFragment());

        addSection(this.home);
        addSection(this.search);
        addDivisor();
        addSection(this.accountPage);
        addSection(this.register);
        addSection(this.login);
        addDivisor();
        addSection(this.getApartment);
        addSection(this.getReview);
        addSection(this.mapTest);
        //this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
        setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);

    }
}