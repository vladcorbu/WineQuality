package com.example.winefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.widget.TextView;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

public class Result extends AppCompatActivity {

    Interpreter tflite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView test = (TextView)findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        float[] features = extras.getFloatArray("features");

        try {
            tflite = new Interpreter(loadModelFile());
        } catch (Exception ex){
            ex.printStackTrace();
        }

        float[][] output = doInference(features);
        int quality = argmax(output);
        test.setText(String.valueOf(quality));

        setPriceRecommandation(quality);
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd("tf_lite_model.tflite");
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    private float[][] scaleData(float[] features){
        float[] means = {(float) 6.86504594e+00, (float) 2.79337672e-01, (float) 3.32730985e-01,
                (float) 6.45070189e+00, (float) 4.57340480e-02, (float) 3.50945636e+01,
                (float) 1.38001149e+02, (float) 9.9407646e-01, (float) 3.18929301e+00,
                (float) 4.89780500e-01, (float) 1.05088404e+01};

        float[] std = {(float) 8.44375370e-01, (float) 1.01592923e-01, (float) 1.19742341e-01, (float) 5.13865471e+00,
                (float) 2.17946404e-02, (float) 1.66748299e+01, (float)4.20622981e+01, (float)3.02130357e-03,
                (float)1.50164060e-01, (float)1.13575483e-01, (float)1.22772995e+00};

        float[][] scaledData = new float[1][11];

        for(int i = 0; i < 11; ++i){
            scaledData[0][i] = (features[i]-means[i])/std[i];
        }

        return scaledData;
    }

    private int argmax(float[][] outputVal){
        int max_ind = 0;
        for(int i = 1; i < 7; ++i) {
            if (outputVal[0][i] > outputVal[0][max_ind]) {
                max_ind = i;
            }
        }
        return max_ind + 3;
    }

    private float[][] doInference(float[] features){
        float[][] inputVal = scaleData(features);
        float[][] outputVal = new float[1][7];
        tflite.run(inputVal, outputVal);
        return outputVal;
    }

    private void setPriceRecommandation(int quality){
        TextView rec = (TextView) findViewById(R.id.pricerec);
        int[] prices1 = {4,10,20,20,30,50,100};
        int[] prices2 = {10,15,30,30,50,100,200};

        String price = "Prices for this wine range between " + String.valueOf(prices1[quality-3]);
        price += "$-" + String.valueOf(prices2[quality-3]+"$");
        rec.setText(price);

    }
}
