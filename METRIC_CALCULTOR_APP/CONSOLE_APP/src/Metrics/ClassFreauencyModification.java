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


import java.io.IOException;
import java.nio.file.Paths;


public class ClassFreauencyModification {

    public ClassFreauencyModification(String metricName) {
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

       private static int calculateFrequency(String fileName, Repository repository) {
           int numberOfModifications = 0;
           try {
               try (RevWalk revWalk = new RevWalk(repository)) {
                   RevCommit headCommit = revWalk.parseCommit(repository.resolve("HEAD"));
                   revWalk.markStart(headCommit);
    
                   for (RevCommit commit : revWalk) {
                       TreeWalk treeWalk = new TreeWalk(repository);
                       treeWalk.addTree(commit.getTree());
                       treeWalk.setRecursive(true);
                       while (treeWalk.next()) {
                        String pathString = treeWalk.getPathString();
                        Path path = Paths.get(pathString);
                        Path name = path.getFileName();
                           if ( FileName.equals(name)) {
                               numberOfModifications++;
                           }
                       }
                       treeWalk.close();
                   }
               }
           } catch (IOException e) {
               e.printStackTrace(); // Handle or log the exception appropriately
           }
           return numberOfModifications;
       }

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




    public float result(String repositoryPath, String fileName) {
        Repository repository = openRepository(repositoryPath);
        return calculateFrequency(fileName, repository) / getNumberOfCommits(repository);
    }


}

