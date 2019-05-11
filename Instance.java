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
public class Instance {
private double sepalLength;
private double sepalWidth;
private double petalLength;
private double petalWidth;
private String className;

public Instance(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String className) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.className = className;
}

public Double getSepalLength() {
        return sepalLength;
}

public Double getSepalWidth() {
        return sepalWidth;
}

public Double getPetalLength() {
        return petalLength;
}

public Double getPetalWidth() {
        return petalWidth;
}

public String getClasssName() {
        return className;
}
}
