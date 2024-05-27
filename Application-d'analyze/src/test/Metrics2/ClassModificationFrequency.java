package org.example.Metrics;

import org.example.Model.gitMetric.gitMetricDefinition;
import org.example.Model.Result;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class  ClassModificationFrequency extends gitMetricDefinition{
    private static Repository openRepository(String directoryPath) {
        try {
            return Git.open(Paths.get(directoryPath).toFile()).getRepository();
        } catch (IOException e) {
            System.out.println("Error opening repository");
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
                        if ( fileName.equals(path.getFileName().toString())) {
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
        } catch (GitAPIException e) {
            throw new RuntimeException(e);
        }
        return numberOfCommits;
    }




    public float result(String repositoryPath, String fileName) {
        Repository repository = openRepository(repositoryPath);
        if(repository == null){
            return 0;
        }else {
            float returned = (float) calculateFrequency(fileName, repository) / getNumberOfCommits(repository);
            repository.close();
            return returned;
        }
    }

    public ClassModificationFrequency(String file_name) {
        this.metric_name = file_name;
    }
    @Override
    public float calculate(String git_file_path, String file_name) {
        return result(git_file_path , file_name);
    }

    @Override
    public Result execute(String git_file_path, String file_name) {
        return new Result(this.metric_name , String.valueOf(calculate(git_file_path , file_name)));
    }
}
