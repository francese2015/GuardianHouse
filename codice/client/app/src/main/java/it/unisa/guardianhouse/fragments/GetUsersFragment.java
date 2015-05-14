package it.unisa.guardianhouse.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.unisa.guardianhouse.R;
import it.unisa.guardianhouse.adapters.UserListAdapter;
import it.unisa.guardianhouse.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class GetUsersFragment extends Fragment {

    private List<User> userList = new ArrayList<User>();
    private ListView listView;
    UserListAdapter adapter;

    public GetUsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_get_users, container, false);

        listView = (ListView) view.findViewById(R.id.listView1);
        adapter = new UserListAdapter(getActivity(), userList);
        listView.setAdapter(adapter);

        try {
            // simulo il response del server
            String stringJsonUsersList = "{\"users\":[{\"id\":\"37\",\"api_key\":\"bf45c093e542f057caee68c4" +
                    "77dbt7zg\",\"username\":\"m.rossi\",\"encrypted_password\":\"t6lGmFNmZCP9xibSb48" +
                    "xCFGraPc0Y2FkZjbTdfRz\",\"salt\":\"4cadf5f986\",\"email\":\"m.rossi@email.it\"," +
                    "\"name\":\"Mario\",\"surname\":\"Rossi\",\"birthdate\":\"1989-03-02\",\"birthpla" +
                    "ce\":\"Roma\",\"address\":\"Via Garibaldi 12\",\"phone\":\"3397483647\",\"role" +
                    "\":\"admin\"},{\"id\":\"46\",\"api_key\":\"ffb032fb7c073d16a9b5e7146fd6e3zz" +
                    "\",\"username\":\"c.tranzollo\",\"encrypted_password\":\"OexyZAZdn3eNQ2og89Xq01x" +
                    "ufBBjNTgzOWUyzZzZ\",\"salt\":\"c5839e2d1z\",\"email\":\"c.tranzollo@email.it" +
                    "\",\"name\":\"Claudio\",\"surname\":\"Tranzollo\",\"birthdate\":\"1989-06-02\"" +
                    ",\"birthplace\":\"Campobasso\",\"address\":\"Via Colvento 12\",\"phone\":\"33874" +
                    "83647\",\"role\":\"admin\"}]}";
            JSONObject jsonUserList = new JSONObject(stringJsonUsersList);

            // estraggo l'array users dal response del server
            JSONArray usersArray = jsonUserList.getJSONArray("users");
            for (int i = 0; i < usersArray.length(); i++) {
                JSONObject singleUser = usersArray.getJSONObject(i);
                User user = new User();
                user.setUsername(singleUser.getString("username"));
                user.setName(singleUser.getString("name"));
                user.setSurname(singleUser.getString("surname"));
                userList.add(user);
                adapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }




}
