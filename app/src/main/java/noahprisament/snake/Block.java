package noahprisament.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by student on 7/26/2016.
 */
public class Block {
    SurfaceView surface;
    private Bitmap bitmap;
    BitmapFactory bitmapFactory = new BitmapFactory();
    private Bitmap blackMap;
    private Bitmap redMap;
    private Bitmap whiteMap;
    private Bitmap grayMap;
    private String color = "Black";

    public Block(String setColor, Context context){
        this.surface = new SurfaceView(context);
        this.color = setColor;
        blackMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.black10);
        redMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.black10);
        whiteMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.black10);
        grayMap = this.bitmapFactory.decodeResource(this.surface.getResources(), R.drawable.black10);
        if(color.equals("Black")){
            this.bitmap = blackMap;
        }else if(color.equals("Red")){
            this.bitmap = redMap;
        }else if(color.equals("White")){
            this.bitmap = whiteMap;
        }
    }

    public void draw(Canvas canvas, int xSpot, int ySpot){
        canvas.drawBitmap(this.bitmap, xSpot, ySpot, null);

    }
}
