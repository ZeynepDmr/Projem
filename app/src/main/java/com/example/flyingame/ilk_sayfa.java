package com.example.flyingame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ilk_sayfa extends AppCompatActivity {
    private ConstraintLayout cl;

    private ImageView canBir, canIki, canUc, kus, kirmiziTop, siyahTop;
    private ImageView hakBir, hakIki, hakUc;
    private TextView Score, Basla;

    private int skor = 0;
    private int can = 2;

    private int kusY;

    private int kirmiziTopX;
    private int kirmiziTopY;
    private int siyahTopX;
    private int siyahTopY;


    private boolean dokunma = false;
    private boolean baslangic = false;

    private Timer timer = new Timer();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilk_sayfa);

        cl = findViewById(R.id.cl);

        canBir = findViewById(R.id.canBir);
        canIki = findViewById(R.id.canIki);
        canUc = findViewById(R.id.canUc);
        kus = findViewById(R.id.kus);
        siyahTop = findViewById(R.id.siyahTop);
        kirmiziTop = findViewById(R.id.kirmiziTop);

        Score = findViewById(R.id.score);
        Basla = findViewById(R.id.basla);

        kirmiziTop.setY(-80);
        kirmiziTop.setX(-80);
        siyahTop.setY(-80);
        siyahTop.setX(-80);

        /*hakBir.setVisibility(View.INVISIBLE);
        hakIki.setVisibility(View.INVISIBLE);
        hakUc.setVisibility(View.INVISIBLE);*/

        cl.setOnTouchListener(new View.OnTouchListener() { //kuşu oynatma işlemi
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Basla.setVisibility(View.INVISIBLE); // Başlamak için Dokun yazısı gider
                if(baslangic){
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){ //ekrana dokunma işlemi
                        dokunma = true;
                    }
                    if(motionEvent.getAction() == MotionEvent.ACTION_UP){ // ekrana dokunmayı bırakma
                        dokunma = false;
                    }
                }
                else{
                    baslangic = true;

                    kusY = (int) kus.getY();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    kusHareketleri();
                                    topHareketleri();
                                    carpisma();
                                }
                            });

                        }
                    }, 0, 20);

                }
                return true;
            }
        });
    }

    public void kusHareketleri(){
        if(dokunma == true){
            kusY-=20;
        }
        else{
            kusY+=20;
        }

        if(kusY <= 60 + kus.getHeight()){
            kusY = 60 + kus.getHeight(); //kuş ekranın üstüne çıkmaya çalışırsa en üstte sabit kalacak
            // 60 koymamın sebebi en üstten skor yazan yerin altına kadar olan uzaklığı 60 olmasıdır.
        }
        if(kusY >= cl.getHeight() - kus.getHeight()){
            kusY = cl.getHeight() - kus.getHeight(); //kuş ekranın altından çıkmaya kalkışırsa Constraint Layout ekran uzunluğundan kuşun uzunluğu farkı kadar aşağıda kalcak. yani en altta sabitlenecek.

        }
        kus.setY(kusY);
    }
    public void topHareketleri(){
        Random rnd = new Random();
        siyahTopX -= 15; //topun hızı. ne kadar yüksek olursa o kadar hızlı
        if(siyahTopX < 0){
            siyahTopX = cl.getWidth() +20; // ilk top ekranın hemen dışından gelecektir
            int top = rnd.nextInt(cl.getHeight()); // random gelen sayı gelecek olan topun ekran yüksekliğini belirleyecek
            if(top < 90 ){
                top = top + 90;
                siyahTopY = top;
            }
            else{
                siyahTopY = top;
            }
        }
        siyahTop.setX(siyahTopX);
        siyahTop.setY(siyahTopY);


        kirmiziTopX -= 9; //topun hızı. ne kadar yüksek olursa o kadar hızlı
        if(kirmiziTopX < 0){
            kirmiziTopX = cl.getWidth() +20; // ilk top ekranın hemen dışından gelecektir
            int top = rnd.nextInt(cl.getHeight()); // random gelen sayı gelecek olan topun ekran yüksekliğini belirleyecek
            if(top < 90 ){
                top = top + 90;
                kirmiziTopY = top;
            }
            else{
                kirmiziTopY = top;
            }
        }
        kirmiziTop.setX(kirmiziTopX);
        kirmiziTop.setY(kirmiziTopY);
    }

    public void carpisma(){
        int kirmiziTopMerkezX = kirmiziTopX + kirmiziTop.getWidth()/2;
        int kirmiziTopMerkezY = kirmiziTopY + kirmiziTop.getHeight()/2;

        if(0 <= kirmiziTopMerkezX && kirmiziTopMerkezX <= kus.getWidth() &&
                kusY <= kirmiziTopMerkezY && kirmiziTopMerkezY <= kusY + kus.getHeight()){
            skor = skor + 20;
            kirmiziTopX = -10;
            Score.setText("Score : "+skor);
        }

        int siyahTopMerkezX = siyahTopX + siyahTop.getWidth()/2;
        int siyahTopMerkezY = siyahTopY + siyahTop.getHeight()/2;

        if(0 <= siyahTopMerkezX && siyahTopMerkezX <= kus.getWidth() &&
                kusY <= siyahTopMerkezY && siyahTopMerkezY <= kusY + kus.getHeight()){
            if(can > 0){
                skor = skor - 10;
                siyahTopX = -10;
                Score.setText("Score : "+skor);
                can--;
                if(can == 1){
                    canUc.setVisibility(View.INVISIBLE);

                }
                if(can == 0){
                    canIki.setVisibility(View.INVISIBLE);

                }



            }
            else{
                canBir.setVisibility(View.INVISIBLE);

                timer.cancel();
                timer = null;

                String puan = Integer.toString(skor);
                Intent bitis = new Intent(ilk_sayfa.this,son_sayfa.class);
                bitis.putExtra("sonuc", puan);
                startActivity(bitis);
                finish();
            }
        }

    };


}