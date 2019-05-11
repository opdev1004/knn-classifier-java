import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
public class KNNClassifier {
final String class1 = "Iris-setosa";
final String class2 = "Iris-versicolor";
final String class3 = "Iris-virginica";

private double sepalLengthRange;
private double sepalWidthRange;
private double petalLengthRange;
private double petalWidthRange;

private ArrayList<Instance> trainingInstances, testInstances;
private int k;

public KNNClassifier(ArrayList<Instance> trainingInstances, ArrayList<Instance> testInstances, int k) {
        if(trainingInstances.size() == 0 || testInstances.size() == 0 ) {
                System.out.println("KNNClassifier Error: Instaces are empty!");
                return;
        }
        this.trainingInstances = trainingInstances;
        this.testInstances = testInstances;
        this.k = k;
        calculateRanges();
}

public ArrayList<String> classifyClasses(){
        ArrayList<String> classifiedClassNames = new ArrayList<String>();
        for (int i = 0; i < testInstances.size(); i++) {
                ArrayList<DistanceInstance> distanceInstances = new ArrayList<DistanceInstance>();
                Instance testInstance = testInstances.get(i);
                for (int j = 0; j < trainingInstances.size(); j++) {
                        Instance trainingInstance = trainingInstances.get(j);
                        double distance = calculateDistance(testInstance, trainingInstance);
                        String className = trainingInstances.get(j).getClasssName();
                        distanceInstances.add(new DistanceInstance(distance, className));
                }
                Collections.sort(distanceInstances, new distanceComparator());
                String classifiedClassName = calculateCount(distanceInstances, k);
                System.out.println("Test object " + (i + 1) + " is classified as: " + classifiedClassName + ", actual result is: " + testInstances.get(i).getClasssName());
                classifiedClassNames.add(classifiedClassName);
        }
        return classifiedClassNames;
}

/*
   calculate number of count and return the class name that has the most counts.
 */
public String calculateCount(ArrayList<DistanceInstance> distanceInstances, int k) {
        String classifiedClassName;
        ArrayList<ClassCountInstance> classCountInstances = new ArrayList<ClassCountInstance>();
        classCountInstances.add(new ClassCountInstance("Iris-setosa"));
        classCountInstances.add(new ClassCountInstance("Iris-versicolor"));
        classCountInstances.add(new ClassCountInstance("Iris-virginica"));
        for(int j = 0; j < k; j++) {
                if (distanceInstances.get(j).getClasssName().equals(class1)) classCountInstances.get(0).increase();
                else if (distanceInstances.get(j).getClasssName().equals(class2)) classCountInstances.get(1).increase();
                else classCountInstances.get(2).increase();
        }
        Collections.sort(classCountInstances, new classCountComparator());
        if(classCountInstances.get(0).getCount() == classCountInstances.get(1).getCount()) {
                System.out.println("Found same count of classes. Updating k value to: " + (k + 1));
                classifiedClassName = calculateCount(distanceInstances, k + 1);
        }
        else classifiedClassName = classCountInstances.get(0).getClasssName();
        return classifiedClassName;
}


/*
   Distance measure (Euclidean distance)
 */
public Double calculateDistance(Instance a, Instance b) {
        double distance;
        distance = Math.pow((a.getSepalLength() - b.getSepalLength()),2)/Math.pow(sepalLengthRange,2) +
                   Math.pow((a.getSepalWidth() - b.getSepalWidth()),2)/Math.pow(sepalWidthRange,2) +
                   Math.pow((a.getPetalLength() - b.getPetalLength()),2)/Math.pow(petalLengthRange,2) +
                   Math.pow((a.getPetalWidth() - b.getPetalWidth()),2)/Math.pow(petalWidthRange,2);
        distance = Math.sqrt(distance);
        return distance;
}

/*
   Calculate range of features
 */
public void calculateRanges() {
        double sepalLengthRangeMax = Integer.MIN_VALUE;
        double sepalLengthRangeMin = Integer.MAX_VALUE;
        double sepalWidthRangeMax = Integer.MIN_VALUE;
        double sepalWidthRangeMin = Integer.MAX_VALUE;
        double petalLengthRangeMax = Integer.MIN_VALUE;
        double petalLengthRangeMin = Integer.MAX_VALUE;
        double petalWidthRangeMax = Integer.MIN_VALUE;
        double petalWidthRangeMin = Integer.MAX_VALUE;
        for (int i = 0; i < trainingInstances.size(); i++) {
                if (trainingInstances.get(i).getSepalLength() > sepalLengthRangeMax) sepalLengthRangeMax = trainingInstances.get(i).getSepalLength();
                if (trainingInstances.get(i).getSepalLength() < sepalLengthRangeMin) sepalLengthRangeMin = trainingInstances.get(i).getSepalLength();
                if (trainingInstances.get(i).getSepalWidth() > sepalWidthRangeMax) sepalWidthRangeMax = trainingInstances.get(i).getSepalWidth();
                if (trainingInstances.get(i).getSepalWidth() < sepalWidthRangeMin) sepalWidthRangeMin = trainingInstances.get(i).getSepalWidth();
                if (trainingInstances.get(i).getPetalLength() > petalLengthRangeMax) petalLengthRangeMax = trainingInstances.get(i).getPetalLength();
                if (trainingInstances.get(i).getPetalLength() < petalLengthRangeMin) petalLengthRangeMin = trainingInstances.get(i).getPetalLength();
                if (trainingInstances.get(i).getSepalWidth() > petalWidthRangeMax) petalWidthRangeMax = trainingInstances.get(i).getSepalWidth();
                if (trainingInstances.get(i).getSepalWidth() < petalWidthRangeMin) petalWidthRangeMin = trainingInstances.get(i).getSepalWidth();
        }
        /* // for the testing
           System.out.println(sepalLengthRangeMax);
           System.out.println(sepalLengthRangeMin);
           System.out.println(sepalWidthRangeMax);
           System.out.println(sepalWidthRangeMin);
           System.out.println(petalLengthRangeMax);
           System.out.println(petalLengthRangeMin);
           System.out.println(petalWidthRangeMax);
           System.out.println(petalWidthRangeMin);
         */
        sepalLengthRange = sepalLengthRangeMax - sepalLengthRangeMin;
        sepalWidthRange = sepalWidthRangeMax - sepalWidthRangeMin;
        petalLengthRange = petalLengthRangeMax - petalLengthRangeMin;
        petalWidthRange = petalWidthRangeMax - petalWidthRangeMin;

}


class distanceComparator implements Comparator<DistanceInstance> {
public int compare(DistanceInstance a, DistanceInstance b) {
        if(a.getDistance() < b.getDistance()) {
                return -1;
        } else if(a.getDistance() > b.getDistance()) {
                return 1;
        } else {
                return 0;
        }
}
}

class classCountComparator implements Comparator<ClassCountInstance> {
public int compare(ClassCountInstance a, ClassCountInstance b) {
        if(a.getCount() < b.getCount()) {
                return 1;
        } else if(a.getCount() > b.getCount()) {
                return -1;
        } else {
                return 0;
        }
}
}
}
