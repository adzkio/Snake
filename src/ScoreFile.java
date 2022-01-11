import java.io.*;

public class ScoreFile {
    public void maxScore(int score) {
        String s = String.valueOf(score);
        try {
            if (score > getScore()) {
                FileWriter fileWriter = new FileWriter("C:/Users/ll/IdeaProjects/Snake/src/static/score.txt");
                fileWriter.write(s);
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getScore() {
        try {
            FileReader fileReader = new FileReader("C:/Users/ll/IdeaProjects/Snake/src/static/score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String s = bufferedReader.readLine();
            Integer score = Integer.valueOf(s);
            return score;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
