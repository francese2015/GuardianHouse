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
import it.unisa.guardianhouse.fragments.ResultsFragment;
import it.unisa.guardianhouse.fragments.ReviewFragment;
import it.unisa.guardianhouse.fragments.HomeFragment;
import it.unisa.guardianhouse.fragments.LoginFragment;
import it.unisa.guardianhouse.fragments.RegisterFragment;
import it.unisa.guardianhouse.fragments.SearchFragment;
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
    //private MaterialSection aboutUs;
    //private MaterialSection termsConds;


    @Override
    public void init(Bundle savedInstanceState) {

        // codice per gestire la sessione
        db = new SQLiteHandler(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        // codice per riempire il navigation drawer
        this.account = new MaterialAccount(this.getResources(), "Guardian House", "Il tuo angelo custode!", R.drawable.app_logo_big, R.drawable.bg2);
        addAccount(this.account);

        home = newSection("In primo piano", R.drawable.ic_home, new HomeFragment());
        search = newSection("Cerca appartamento", R.drawable.ic_magnify, new SearchFragment());
        apartmentEntry = newSection("Inserisci appartamento", R.drawable.ic_plus, new ApartmentEntryFragment());
        profile = newSection("Profilo", R.drawable.ic_account, new ProfileFragment());
        register = newSection("Registrazione", R.drawable.ic_account_plus, new RegisterFragment());
        login = newSection("Login", R.drawable.ic_login, new LoginFragment());
        //mapTest = newSection("Google Maps Test", new MapTestFragment());
        logout = newSection("Logout", R.drawable.ic_logout, new MaterialSectionListener() {
            @Override
            public void onClick(MaterialSection section) {
                Toast.makeText(NavigationDrawer.this, "Logout effettuato.", Toast.LENGTH_SHORT).show();
                logoutUser();
                section.unSelect();
                HomeFragment homeFragment = new HomeFragment();
                setFragment(homeFragment, "In primo piano");
            }
        });

        this.addSection(this.home);
        this.addSection(this.search);

        this.addDivisor();
        if (!session.isLoggedIn()) {
            this.addSection(this.login);
            this.addSection(this.register);
        } else {
            this.addSection(this.apartmentEntry);
            this.addSection(this.profile);
            this.addSection(this.logout);
        }

        setBackPattern(MaterialNavigationDrawer.BACKPATTERN_BACK_TO_FIRST);

    }

    private void logoutUser() {
        session.setLogin(false, "", "");
        this.removeSection(this.apartmentEntry);
        this.removeSection(this.profile);
        this.removeSection(this.logout);
        this.addSection(this.login);
        this.addSection(this.register);
    }

    public void loginUser() {
        this.addSection(this.apartmentEntry);
        this.addSection(this.profile);
        this.addSection(this.logout);
        this.removeSection(this.login);
        this.removeSection(this.register);
    }

}