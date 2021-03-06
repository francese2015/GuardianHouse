package it.unisa.guardianhouse.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import it.unisa.guardianhouse.R;


public class ResultsFragment extends Fragment implements MaterialTabListener {

    MaterialTabHost tabHost;
    ViewPager pager;
    ViewPagerAdapter adapter;
    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        setHasOptionsMenu(true);

        bundle = getArguments();

        // codice del fragment search
        tabHost = (MaterialTabHost) view.findViewById(R.id.tabHost);
        pager = (ViewPager) view.findViewById(R.id.pager);

        // init view pager
        adapter = new ViewPagerAdapter(this.getFragmentManager());
        pager.setAdapter(adapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < adapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(adapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }

        tabHost.setSelectedNavigationItem(0);

        return view;
    }

    @Override
    public void onTabSelected(MaterialTab materialTab) {
        pager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }


    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        public Fragment getItem(int index) {
            bundle = getArguments();
            switch (index) {
                case 0:
                    ResultsListFragment searchResults = new ResultsListFragment();
                    searchResults.setArguments(bundle);
                    return searchResults;
                case 1:
                    ResultsMapFragment searchResultsMap = new ResultsMapFragment();
                    searchResultsMap.setArguments(bundle);
                    return searchResultsMap;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Lista";
                case 1:
                    return "Mappa";
            }
            return null;
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            // do nothing here! no call to super.restoreState(state, loader);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                SearchFragment searchFragment = new SearchFragment();
                ((MaterialNavigationDrawer) getActivity()).setFragmentChild(searchFragment, "Cerca appartamento");
                return true;

            default:
                break;
        }
        return false;
    }

}
