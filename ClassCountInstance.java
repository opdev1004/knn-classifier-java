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
public class ClassCountInstance {
private int count;
private String className;

public ClassCountInstance(String className) {
        this.className = className;
        this.count = 0;
}

public void increase() {
        this.count++;
}

public void decrease() {
        this.count--;
}

public int getCount() {
        return this.count;
}

public String getClasssName() {
        return this.className;
}
}
