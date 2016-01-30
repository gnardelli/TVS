package it.ielettronica.TVS;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gnardelli on 1/1/16.
 * this is a class used to store in local the list received by internet. Using this class will remove the call to the server every time that the data are in local
 *
 */
public class SitesLocalStore {

    public static final String SP_NAME = "spStackListArray";
    SharedPreferences sitesLocalDatabase;

    public SitesLocalStore(Context context) {
        sitesLocalDatabase = context.getSharedPreferences(SP_NAME,0);
    }




    public void deleteGroupListArray() {


        SharedPreferences.Editor spEditor = sitesLocalDatabase.edit();
        spEditor.remove("JSONGroupString");
        spEditor.apply();

    }


    public void storeStackListArray(List<StackSite> StackSites) {

        Gson gson = new Gson();
        String jsArray = gson.toJson(StackSites);


        SharedPreferences.Editor spEditor = sitesLocalDatabase.edit();
        spEditor.putString("JSONString", jsArray);
        spEditor.apply();

    }




    public void storeGroupListArray(List<StackGroup> Groups) {

        Gson gson = new Gson();
        String jsArray = gson.toJson(Groups);

        SharedPreferences.Editor spEditor = sitesLocalDatabase.edit();
        spEditor.putString("JSONGroupString", jsArray);
        spEditor.apply();

    }


    public List<StackGroup> getGroupListArray() {

        String JSONString = sitesLocalDatabase.getString("JSONGroupString", "");
        List<StackGroup> groups = new ArrayList<StackGroup>();

        try {
            JSONArray jArr=new JSONArray(JSONString);

            for(int i=0;i<jArr.length();i++) {
                JSONObject jObject=jArr.getJSONObject(i);
                String Name = jObject.getString("NameGroup");
                StackGroup groupsV = new StackGroup();
                groupsV.setGroupName(Name);
                groups.add(groupsV);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return groups;
    }


    public List<StackSite> getStackListArray() {

        String JSONString = sitesLocalDatabase.getString("JSONString", "");

        List<StackSite> stackSites = new ArrayList<StackSite>();

        try {
            JSONArray jArr=new JSONArray(JSONString);

            for(int i=0;i<jArr.length();i++) {
                JSONObject jObject=jArr.getJSONObject(i);
                String Name = jObject.getString("name");
                String Description = jObject.getString("about");
                String Image = jObject.getString("imgUrl");
                int isAccepted = jObject.getInt("accepted");
                StackSite stackSiteL = new StackSite(Name,Description,Image,isAccepted);
                stackSites.add(stackSiteL);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return stackSites;
    }



}
