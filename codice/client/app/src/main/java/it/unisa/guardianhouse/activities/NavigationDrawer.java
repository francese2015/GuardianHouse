package it.unisa.guardianhouse.activities;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.unisa.guardianhouse.fragments.BlankFragment;

public class NavigationDrawer extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        MaterialSection section = newSection("Section 1", new BlankFragment());

        this.addAccountSection(section);
        this.addSection(section);
        this.addBottomSection(section);

    }
}