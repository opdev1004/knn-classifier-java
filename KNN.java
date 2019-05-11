import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;


/**
 * @author Chanil Park
 *
 *
 * How to run the code:
 *
 * javac KNN.java
 * java KNN iris-test iris-training
 * or
 * java KNN iris-test.txt iris-training.txt
 *
 * Useful Commands: cls, clear, ls
 *
 * Java source code structure:
 * KNN.java: KNN controls with user input and output for results
 * KNNClassifier class: Functioning for all the calculations and operations for results
 * Instance classes - Instance: instance object to hold data
 *                    DistanceInstance: instance object to hold distance data
 *                    ClassCountInstance: instance object to hold count and class name
 */
public class KNN {

public KNN(String training, String test) {
        ArrayList<Instance> trainingInstances = loadInstances(training);
        ArrayList<Instance> testInstances = loadInstances(test);
        int k = 0;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the k value: ");
        k = scan.nextInt();
        scan.close();
        System.out.println("Current K value is: " + k);
        KNNClassifier knnc = new KNNClassifier(trainingInstances, testInstances, k);
        ArrayList<String> classifiedClassNames = knnc.classifyClasses();
        int correctCount = 0;
        for (int i = 0; i < testInstances.size(); i++) {
                if (testInstances.get(i).getClasssName().equals(classifiedClassNames.get(i))) correctCount++;
        }
        double accuracy = (double) correctCount/testInstances.size() * 100;
        System.out.println("Correct Count: " + correctCount + "/" + testInstances.size() + ", Accuracy: " + accuracy + "%");
}

/*
   Load data.
 */
private ArrayList<Instance> loadInstances (String fileName){
        ArrayList<Instance> tempInstances = new ArrayList<Instance>();
        try{
                BufferedReader bf = new BufferedReader(new FileReader(fileName));
                String curL = bf.readLine();
                while(curL != null) {
                        ArrayList<String> tempList = new ArrayList<String> (Arrays.asList(curL.split("\\s+")));
                        Deque<String> data = new ArrayDeque<String>(tempList);
                        String sepalLength = data.poll();
                        String sepalWidth = data.poll();
                        String petalLength = data.poll();
                        String petalWidth = data.poll();
                        String className = data.poll();
                        /* // for the testing
                           System.out.println(sepalLength);
                           System.out.println(sepalWidth);
                           System.out.println(petalLength);
                           System.out.println(petalWidth);
                           System.out.println(className);
                         */
                        if (sepalLength != null && sepalWidth != null && petalLength != null && petalWidth != null && className != null)
                                tempInstances.add(new Instance(Float.parseFloat(sepalLength), Float.parseFloat(sepalWidth),
                                                               Float.parseFloat(petalLength), Float.parseFloat(petalWidth),
                                                               className ));

                        curL = bf.readLine();
                }
                bf.close();
        }catch(IOException e) {System.out.println("E: Error occured with loading file. " + e);}
        return tempInstances;
}

/*
   Main, input and output information
 */
public static void main(String[] args) {
        // return if the arguments are not 2
        if(args.length != 2) {
                System.out.println("There must be 2 files.");
                return;
        }
        // remove .txt
        if(args[0].contains(".txt")) {
                args[0] = args[0].replace(".txt", "");
        }
        if(args[1].contains(".txt")) {
                args[1] = args[1].replace(".txt", "");
        }
        // just easy way to use the names.
        String training = "", test = "";
        for(int i=0; i < args.length; i++) {
                if(args[i].contains("test")) {
                        test = args[i] + ".txt";
                } else if(args[i].contains("training")) {
                        training = args[i] + ".txt";
                } else{
                        training = args[0] + ".txt";
                        test = args[1] + ".txt";
                        break;
                }
        }
        System.out.println("Loading data (training:" + training + ", test: " + test + ")");
        new KNN(training, test);
}
}
