package messenger.service;

import messenger.database.HashMapClass;
import messenger.model.Profile;
import messenger.database.ConnectionDB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Антон on 17.11.2017.
 */
public class ProfileService {

    private Map<Long, Profile> profiles = HashMapClass.getProfiles();
    Long count = 1L;
    ConnectionDB con = new ConnectionDB();

    public ProfileService() {
        profiles.put(1L, new Profile(1, "NotLogginedRetard", "Retard","Retardov"));
       // profiles.put(2L, new Profile(2, "Shadow", "Anton","Belyavskii"));
    }

    public List<Profile> getAllProfiles() throws SQLException{
        ArrayList<Profile> prfList = new ArrayList<Profile>();



        ResultSet rs = con.getAllProfiles();
        while (rs.next())
        {
            profiles.put(count, new Profile(1, rs.getString("USERNAME"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME")));
            count++;
        }

        profiles.values();
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile addProfile(int i, Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getId(), profile);
        return profile;
    }

    public Profile getProfile(String username) {
        Long id;
        for (id = 1L;id<=profiles.size();id+=1)
        {
            Profile profile = profiles.get(id);
            if(profile.getProfileName().equals(username)) {
                return profiles.get(id);
            }
        }
        return profiles.get(1L);
    }



}
