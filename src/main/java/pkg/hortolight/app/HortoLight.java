package pkg.hortolight.app;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class HortoLight extends Activity {

    Button flashlightToggle;
    SurfaceView camPlaceHolder;
    Camera cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horto_light);
        flashlightToggle = (Button) findViewById(R.id.xmlButton);
        camPlaceHolder = (SurfaceView) findViewById(R.id.surfaceView);
        flashlightToggle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(flashlightToggle.getText().equals("OFF"))
                {
                    cam = Camera.open();
                    try {
                        cam.setPreviewDisplay(camPlaceHolder.getHolder());
                        Camera.Parameters p = cam.getParameters();
                        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        cam.setParameters(p);
                        cam.startPreview();
                        flashlightToggle.setText("ON");
                    } catch (IOException e) {
                        e.printStackTrace();
                        cam.release();
                        Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG);
                    }

                }

                else{
                    cam.stopPreview();
                    cam.release();
                    flashlightToggle.setText("OFF");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.horto_light, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
