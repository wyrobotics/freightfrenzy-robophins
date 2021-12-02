package org.firstinspires.ftc.teamcode.Components.Software;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractor;
import org.opencv.video.Video;
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
                camera.stopStreaming();
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

                contour.release();

            }

            Imgproc.rectangle(input, biggestRect, new Scalar(200,0,0), 2);

            //Imgproc.drawContours(input, pinkContours, 2, new Scalar(0,0,255), 2, 1, hierarchy);
            //Imgproc.drawContours(input, pinkContours, -1, new Scalar(0,0,200));

            contourX = biggestRect.x;

            pinkContours = null;

            return input;

        }

        private List<MatOfPoint> pinkContours(Mat input) {

            Mat inputRedMin = new Mat();
            Mat inputGreenMin = new Mat();
            Mat inputBlueMin = new Mat();
            Mat inputRedMax = new Mat();
            Mat inputGreenMax = new Mat();
            Mat inputBlueMax = new Mat();
            Mat pinkMask = new Mat(input.size(), 0);

            Imgproc.GaussianBlur(input, input, new Size(3,3), 0);

            List<Mat> channels = new ArrayList<Mat>();
            Core.split(input,channels);

            Imgproc.threshold(channels.get(0), inputRedMin, 120, 255, Imgproc.THRESH_BINARY);
            Imgproc.threshold(channels.get(0), inputRedMax, 200, 255, Imgproc.THRESH_BINARY_INV);
            Imgproc.threshold(channels.get(1), inputGreenMin, 5, 255, Imgproc.THRESH_BINARY);
            Imgproc.threshold(channels.get(1), inputGreenMax, 35, 255, Imgproc.THRESH_BINARY_INV);
            Imgproc.threshold(channels.get(2), inputBlueMin, 55, 255, Imgproc.THRESH_BINARY);
            Imgproc.threshold(channels.get(2), inputBlueMax, 90, 255, Imgproc.THRESH_BINARY_INV);

            Core.bitwise_and(inputRedMin, inputGreenMin, pinkMask);
            Core.bitwise_and(inputBlueMin, pinkMask, pinkMask);
            Core.bitwise_and(inputRedMax, pinkMask, pinkMask);
            Core.bitwise_and(inputBlueMax, pinkMask, pinkMask);
            Core.bitwise_and(inputGreenMax, pinkMask, pinkMask);

            List<MatOfPoint> pinkContours = new ArrayList<MatOfPoint>();
            Mat hierarchy = new Mat();

            Imgproc.findContours(pinkMask, pinkContours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

            inputRedMin.release();
            inputGreenMin.release();
            inputBlueMin.release();
            inputRedMax.release();
            inputGreenMax.release();
            inputBlueMax.release();
            pinkMask.release();

            return pinkContours;

        }

    }



}

