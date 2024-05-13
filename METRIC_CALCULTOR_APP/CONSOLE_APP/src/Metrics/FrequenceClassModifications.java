package Metrics;

import Model.ClassLevelMetric;
import Model.Result;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FrequenceClassModifications extends ClassLevelMetric {

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

    private static int calculateFrequency(String file_path, Repository repository) {
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
                        String path = treeWalk.getPathString();
                        if (path.endsWith(".java") && path.equals(file_path)) {
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


    private float result(String file_path, String repository_path) {
        Repository repository = openRepository(repository_path);
        int numberOfCommits =  getNumberOfCommits(repository);

        int numberOfModifications = calculateFrequency(file_path, repository);

        repository.close();

        return (float) numberOfModifications / numberOfCommits;
    }

    @Override
    public float calculate(String file_path) {
        return 0;
    }

    public float calculate(String file_path, String repository_path) {
        return result(file_path, repository_path);
    }


    @Override
    public Result execute(String file_path) {
        return  null;
    }

    public Result execute(String file_path, String repository_path) {
        return new Result(this.metricName, String.valueOf(this.calculate(file_path, repository_path)));
    }
}

