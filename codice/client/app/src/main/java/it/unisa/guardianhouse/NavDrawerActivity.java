package it.unisa.guardianhouse;

import android.os.Bundle;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.unisa.guardianhouse.fragment.BlankFragment;


public class NavDrawerActivity extends MaterialNavigationDrawer {

    @Override
    public void init(Bundle savedInstanceState) {
        MaterialSection section = newSection("Section 1", new BlankFragment());
        this.addSection(section);
    }
}
