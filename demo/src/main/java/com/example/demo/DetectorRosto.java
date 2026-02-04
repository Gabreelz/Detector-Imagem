package com.example.demo;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class DetectorRosto {
    static {
        nu.pattern.OpenCV.loadLocally();
    }

    private static final String BASE_PATH = "src/main/resources/";
    private static final String HAARCASCADE_PATH ="src/main/resources/haarcascade_frontalface_default.xml";
    private static final String IMAGE_PATH = "src/main/resources/002.jpg";

    public static void main(String[] args) {
        
        CascadeClassifier DetectorRosto = new CascadeClassifier(HAARCASCADE_PATH);

        Mat image = Imgcodecs.imread(IMAGE_PATH);

        if (DetectorRosto.empty()) {
            System.out.println("ERRO: Haarcascade não foi carregado!");
            return;
        }

        if( image.empty()){
            System.out.println("Rosto não detectado!");
            return;
        }

        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);


        MatOfRect DetectorRostos = new MatOfRect();
        DetectorRosto.detectMultiScale(gray, DetectorRostos);

        for(Rect rect : DetectorRostos.toArray()){
            Imgproc.rectangle(
                image,
                new Point(rect.x, rect.y),
                new Point(rect.x + rect.width, rect.y + rect.height),
                new Scalar(0, 255, 0),
                3
            );
        };

        Imgcodecs.imwrite(BASE_PATH + "resultado.jpg", image);
        System.out.println("Concluido!!!!");
    }
}
