package com.diveno.fastandroid.test;

import com.diveno.fastandroid.data.model.Owner;
import com.diveno.fastandroid.data.model.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 15/06/2016.
 */
public class TestDataFactory {


    public static List<Repo> makeListRepos(int number) {
        List<Repo> repos = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            repos.add(makeRepo(String.valueOf(i)));
        }
        return repos;
    }

    public static Repo makeRepo(String uniqueSuffix) {
        Repo repo = new Repo();
        repo.setStargazersCount(Integer.valueOf(uniqueSuffix));
        repo.setForksCount(Integer.valueOf(uniqueSuffix));
        repo.setName(uniqueSuffix + "-Repo");
        Owner owner = new Owner();
        owner.setAvatarUrl("https://avatars.githubusercontent.com/u/3430433?v=3");
        repo.setOwner(owner);
        return repo;
    }
}
