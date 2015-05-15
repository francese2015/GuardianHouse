package it.unisa.guardianhouse;

import android.os.Bundle;
import android.widget.Toast;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;
import it.unisa.guardianhouse.fragments.ApartmentEntryFragment;
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
    private MaterialSection apartmentEntry;
    private MaterialSection register;
    private MaterialSection login;
    private MaterialSection logout;
    private MaterialSection profile;
    //private MaterialSection logout;
    //private MaterialSection aboutUs;
    //private MaterialSection termsConds;
    private MaterialSection apartment;
    private MaterialSection review;
    //private MaterialSection mapTest;


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
        apartmentEntry = newSection("Inserisci appartamento", new ApartmentEntryFragment());
        profile = newSection("Profilo", new ProfileFragment());
        register = newSection("Registrazione", new RegisterFragment());
        login = newSection("Login", new LoginFragment());
        apartment = newSection("Appartamento", new ApartmentFragment());
        review = newSection("Recensione", new ReviewFragment());
        //mapTest = newSection("Google Maps Test", new MapTestFragment());
        logout = newSection("Logout", new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection section) {
                Toast.makeText(NavigationDrawer.this, "Logout effettuato.", Toast.LENGTH_SHORT).show();
                logoutUser();
                section.unSelect();
                HomeFragment homeFragment = new HomeFragment();
                setFragment(homeFragment, "Home");
            }
        });

        this.addSection(this.home);
        this.addSection(this.search);
        this.addSection(this.apartmentEntry);
        this.addDivisor();
        if (!session.isLoggedIn()) {
            this.addSection(this.login);
            this.addSection(this.register);
        } else {
            this.addSection(this.profile);
            this.addSection(this.logout);
        }
        this.addBottomSection(this.apartment);
        this.addBottomSection(this.review);
        //this.addBottomSection(this.mapTest);

        setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);

    }

    private void logoutUser() {
        session.setLogin(false, "");
        this.removeSection(this.profile);
        this.removeSection(this.logout);
        this.addSection(this.login);
        this.addSection(this.register);
    }

    public void loginUser() {
        session.setLogin(false, "");
        this.addSection(this.profile);
        this.addSection(this.logout);
        this.removeSection(this.login);
        this.removeSection(this.register);
    }

}