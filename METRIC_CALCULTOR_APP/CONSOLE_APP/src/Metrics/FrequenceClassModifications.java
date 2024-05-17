package Metrics;

import Model.PackageLevelMetric;
import Model.Result;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;


import java.util.HashMap;
import  com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Paths;


public class FrequenceClassModifications extends PackageLevelMetric {

    public FrequenceClassModifications(String metricName) {
        super(metricName);
    }

    private static Repository openRepository(String directoryPath) {
        try {
            return Git.open(Paths.get(directoryPath).toFile()).getRepository();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//    private static int calculateFrequency(String file_path, Repository repository) {
//        int numberOfModifications = 0;
//        try {
//            try (RevWalk revWalk = new RevWalk(repository)) {
//                RevCommit headCommit = revWalk.parseCommit(repository.resolve("HEAD"));
//                revWalk.markStart(headCommit);
//
//                for (RevCommit commit : revWalk) {
//                    TreeWalk treeWalk = new TreeWalk(repository);
//                    treeWalk.addTree(commit.getTree());
//                    treeWalk.setRecursive(true);
//                    while (treeWalk.next()) {
//                        String path = treeWalk.getPathString();
//                        if (path.endsWith(".java") && path.equals(file_path)) {
//                            numberOfModifications++;
//                        }
//                    }
//                    treeWalk.close();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace(); // Handle or log the exception appropriately
//        }
//        return numberOfModifications;
//    }

    public int getNumberOfCommits(Repository repository) {
        int numberOfCommits = 0;
        try {
            Git git = new Git(repository);
            Iterable<RevCommit> commits = git.log().all().call();
            for (RevCommit commit : commits) {
                numberOfCommits++;
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        } catch (NoHeadException e) {
            throw new RuntimeException(e);
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
       return numberOfCommits;
    }

    private HashMap<String , Float> claculateFrequencies(Repository repository) {
        HashMap<String, Float> modificationCountMap = new HashMap<>();
        try (RevWalk revWalk = new RevWalk(repository)) {
            RevCommit headCommit = revWalk.parseCommit(repository.resolve("HEAD"));
            revWalk.markStart(headCommit);
            for (RevCommit commit : revWalk) {
                TreeWalk treeWalk = new TreeWalk(repository);
                treeWalk.addTree(commit.getTree());
                treeWalk.setRecursive(true);
                while (treeWalk.next()) {
                    String path = treeWalk.getPathString();
                    if (path.endsWith(".java")) {
                        modificationCountMap.put(path, modificationCountMap.getOrDefault(path, 0.0f) + 1);
                    }
                }
                treeWalk.close();
            }
        } catch (IOException e) {
        e.printStackTrace(); // Handle or log the exception appropriately
        }
        int numberOfCommits = getNumberOfCommits(repository);
        for(String key : modificationCountMap.keySet()) {
            modificationCountMap.put(key, modificationCountMap.get(key) / numberOfCommits);
        }

        return modificationCountMap;
    }

    private String hashMapToJson(HashMap<String, Float> map) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(map));
        return gson.toJson(map);
    }


    public String result(String repository_path) {
        Repository repository = openRepository(repository_path);
       String result = hashMapToJson(claculateFrequencies(repository));
        repository.close();

        return result;
    }

    @Override
    public float calculate(String file_path) {
        return 0.0f;
    }

    @Override
    public Result execute(String file_path) {
        return null;
    }

//    public Result execute(String file_path, String repository_path) {
//        return new Result(this.metricName, String.valueOf(this.calculate(file_path, repository_path)));
//    }

}

