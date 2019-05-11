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
public class DistanceInstance {
private double distance;
private String className;

public DistanceInstance(double distance, String className) {
        this.distance = distance;
        this.className = className;
}

public Double getDistance() {
        return distance;
}

public String getClasssName() {
        return className;
}
}
