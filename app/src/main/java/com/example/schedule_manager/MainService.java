package com.example.schedule_manager;


import java.util.HashMap;
import java.util.List;

public class MainService {

    public HashMap<String,String> popShiftsMap(List<Vardies> vardiesList){

        HashMap<String,String> hashMap = new HashMap<String,String>();

        for(int i=0; i <(vardiesList.size()); i++){
            hashMap.put(Integer.toString(vardiesList.get(i).sid),vardiesList.get(i).onoma);
        }
        return hashMap;
    }

    public HashMap<Integer,String> popEidList(List<Vardies> vardiesList){
        HashMap<Integer,String> hashMap = new HashMap<Integer, String>();

        for(int i=0; i <(vardiesList.size()); i++){
            hashMap.put(vardiesList.get(i).jid,vardiesList.get(i).eidikotita);
        }
        return hashMap;
    }
}
