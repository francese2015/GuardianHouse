package it.unisa.guardianhouse;

import android.os.Bundle;
import android.widget.Toast;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;
import it.unisa.guardianhouse.fragments.ProfileFragment;
import it.unisa.guardianhouse.fragments.ApartmentFragment;
import it.unisa.guardianhouse.fragments.ReviewFragment;
import it.unisa.guardianhouse.fragments.HomeFragment;
import it.unisa.guardianhouse.fragments.LoginFragment;
import it.unisa.guardianhouse.fragments.RegisterFragment;
import it.unisa.guardianhouse.fragments.SearchFragment;
import it.unisa.guardianhouse.fragments.MapTestFragment;
import it.unisa.guardianhouse.helpers.SQLiteHandler;
import it.unisa.guardianhouse.helpers.SessionManager;


public class NavigationDrawer extends MaterialNavigationDrawer implements MaterialSectionListener {

    private SessionManager session;
    private SQLiteHandler db;

    private MaterialAccount account;
    private MaterialSection home;
    private MaterialSection search;
    private MaterialSection register;
    private MaterialSection login;
    private MaterialSection logout;
    private MaterialSection profile;
    //private MaterialSection logout;
    //private MaterialSection aboutUs;
    //private MaterialSection termsConds;
    private MaterialSection apartment;
    private MaterialSection review;
    private MaterialSection mapTest;


    @Override
    public void init(Bundle savedInstanceState) {

        // codice per gestire la sessione
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        // codice per riempire il navigation drawer
        this.account = new MaterialAccount(this.getResources(), "Guardian House", "Il tuo angelo custode!", R.drawable.app_logo_big, R.drawable.bg);
        addAccount(this.account);

        home = newSection("Home", new HomeFragment());
        search = newSection("Ricerca", new SearchFragment());
        profile = newSection("Profilo", new ProfileFragment());
        register = newSection("Registrazione", new RegisterFragment());
        login = newSection("Login", new LoginFragment());
        apartment = newSection("Appartamento", new ApartmentFragment());
        review = newSection("Recensione", new ReviewFragment());
        mapTest = newSection("Google Maps Test", new MapTestFragment());
        logout = newSection("Logout", new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection section) {
                Toast.makeText(NavigationDrawer.this, "Logout effettuato.", Toast.LENGTH_SHORT).show();
                //logoutUser();
                section.unSelect(); // so deselect the section if you want
            }
        });

        addSection(this.home);
        addSection(this.search);
        addDivisor();
//        if (!session.isLoggedIn()) {
//            logoutUser();
//            addSection(this.login);
//            addSection(this.register);
//
//        } else {
//            addSection(this.profile);
//            addSection(this.logout);
//        }
        addSection(this.login);
        addSection(this.register);
        addSection(this.profile);
        addSection(this.logout);
        addDivisor();
        addSection(this.apartment);
        addSection(this.review);
        addSection(this.mapTest);

        setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);

    }

    private void logoutUser() {
        session.setLogin(false, "");
        //db.deleteUsers();
    }
}