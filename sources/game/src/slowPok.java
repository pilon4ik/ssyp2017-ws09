import Algorithms.Algorithm;
import Game.Bike;
import org.newdawn.slick.geom.Point;

/**
 * Created by denis on 30.07.2017.
 */
public class slowPok extends Algorithm {
    long timer = System.currentTimeMillis() ;
    int state=1;
    Boolean rotate;
    int round=0;
    int sup2=0;
    Boolean chng=false,change=false;
    @Override
    public void doSomething(Bike bike) {
        rotate=true;
        long curr = System.currentTimeMillis() ;
        if (bike.isDead()){
             timer = System.currentTimeMillis() ;
             state=1;
             rotate=true;
             round=0;
             sup2=0;
             chng=false;
            change=false;
        }
        if (round==0&&bike.getPosition().getX()<800&&bike.getPosition().getX()>400)
        {
            if(bike.getPosition().getY()<350)
                bike.rotate(true);
            else
                bike.rotate(false);
            round++;
        }


        if (chng){bike.rotate(rotate);}

        if (curr > timer + 10000 && state == 1) {
            bike.rotate(rotate);
            ++state;
            timer=System.currentTimeMillis();

        }
        if (curr > timer + 1&&state==2){

                ++state;
            //if (sup2==3&&round==3)
              //  return;
           // else
                bike.rotate(rotate);
            timer=System.currentTimeMillis();

        }
        if (curr>timer+10000&&state==3){
            bike.rotate(!rotate);
            ++state;
            timer=System.currentTimeMillis();

        }
        if (curr>timer+1&&state==4)
        {   bike.rotate(!rotate);
            state=1;
            round++;
            timer=System.currentTimeMillis();
            ++this.sup2;
            this.chng = Boolean.valueOf(false);
            //changa =false;
            //if(this.sup2 == 50) {++this.round;chng=true;this.state = 1;}

        }
        if (bike.getVelocity() < 98) {
            bike.accelerate(true);

            bike.accelerate(true);
        }
//        if (curr > timer +990) {
  //          bike.accelerate(true);
          //  timer=System.currentTimeMillis();
        /*    }*/
        }
    }
