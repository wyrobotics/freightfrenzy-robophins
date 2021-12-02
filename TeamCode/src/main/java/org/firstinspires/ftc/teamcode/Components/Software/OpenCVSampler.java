package org.firstinspires.ftc.teamcode.Components.Software;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class OpenCVSampler {

    private OpenCvCamera camera;

    private double contourX = 0;

    public OpenCVSampler(HardwareMap hardwareMap, Telemetry telemetry) {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        //WebcamName webcamName = hardwareMap.get(WebcamName.class, "webcam");
        //OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

    }

    public void initOpenCVCamera() {

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
                //GPU acceleration may cause errors -J

                camera.setPipeline(new OpenCVSampler.Pipeline());
            }
            @Override
            public void onError(int errorCode)
            {

            }
        });

    }

    public double getPinkX(double trials) {

        double total = 0;

        for(int i = 0; i < trials; i++) {
            total += contourX;
        }

        return total / trials;

    }

    public double getPinkX() { return getPinkX(1000); }

    public class Pipeline extends OpenCvPipeline {

        @Override
        public Mat processFrame(Mat input) {

            List<MatOfPoint> pinkContours = pinkContours(input);

            double maxArea = 0;
            double newArea;

            Rect biggestRect = new Rect(0,0, 1,1);

            for(MatOfPoint contour : pinkContours) {

                newArea = Imgproc.contourArea(contour);

                if(newArea > maxArea) {

                    biggestRect = Imgproc.boundingRect(contour);

                    maxArea = newArea;

                }

            }

            Imgproc.rectangle(input, biggestRect, new Scalar(200,0,0), 2);

            contourX = biggestRect.x;

            return input;

        }

        private List<MatOfPoint> pinkContours(Mat input) {

            Mat inputRed = new Mat();
            Mat inputGreen = new Mat();
            Mat inputBlue = new Mat();
            Mat pinkMask = new Mat(input.size(), 0);

            //Imgproc.GaussianBlur(input, input, new Size(3,3), 0);

            List<Mat> channels = new ArrayList<Mat>();
            Core.split(input,channels);

            Imgproc.threshold(channels.get(0), inputRed, 170, 230, Imgproc.THRESH_BINARY);
            Imgproc.threshold(channels.get(1), inputGreen, 20, 70, Imgproc.THRESH_BINARY);
            Imgproc.threshold(channels.get(2), inputBlue, 120, 140, Imgproc.THRESH_BINARY);

            Core.bitwise_and(inputRed, inputGreen, pinkMask);
            Core.bitwise_and(inputBlue, pinkMask, pinkMask);

            List<MatOfPoint> pinkContours = new ArrayList<MatOfPoint>();
            Mat hierarchy = new Mat();

            Imgproc.findContours(pinkMask, pinkContours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

            return pinkContours;

        }

    }



}

