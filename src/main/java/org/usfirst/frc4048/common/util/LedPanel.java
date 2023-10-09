package org.usfirst.frc4048.common.util;

import edu.wpi.first.wpilibj.DigitalOutput;

public class LedPanel {
    public enum PicID {
        ZERO(false,false,false),
        ONE(true,false,false),
        TWO(false,true,false),
        THREE(true,true,false),
        FOUR(false,false,true),
        FIVE(true,false,true),
        SIX(false,true,true),
        SEVEN(true,true,true);
        private final boolean op1;
        private final boolean op2;
        private final boolean op3;
        PicID(boolean op1, boolean op2, boolean op3) {
            this.op1 = op1;
            this.op2 = op2;
            this.op3 = op3;
        }
    }
    private PicID ID;

    private final DigitalOutput output1;
    private final DigitalOutput output2;
    private final DigitalOutput output3;

    public LedPanel(int digitalOut1, int digitalOut2, int digitalOut3) {
        ID = PicID.ZERO;
        output1 = new DigitalOutput(digitalOut1);
        output2 = new DigitalOutput(digitalOut2);
        output3 = new DigitalOutput(digitalOut3);
    }

    public PicID getID() {
        return ID;
    }

    public void setID(PicID picID) {
        ID = picID;
        setPic(picID);
    }
    /*      Output1      Output 2    Output 3 f(x)
True Values{  1            2           4      x%2!=0
              3            3           5
              5            6           6
              7            7           7  }
     */

    private void setPic(PicID picID) {
        output1.set(picID.op1);
        output2.set(picID.op2);
        output3.set(picID.op3);
//        if(picID == 0) {
//            output1.set(false);
//            output2.set(false);
//            output3.set(false);
//        }
//        else if(picID == 1) {
//            output1.set(true);
//            output2.set(false);
//            output3.set(false);
//        }
//        else if(picID == 2) {
//            output1.set(false);
//            output2.set(true);
//            output3.set(false);
//        }
//        else if(picID == 3) {
//            output1.set(true);
//            output2.set(true);
//            output3.set(false);
//        }
//        else if(picID == 4) {
//            output1.set(false);
//            output2.set(false);
//            output3.set(true);
//        }
//        else if(picID == 5) {
//            output1.set(true);
//            output2.set(false);
//            output3.set(true);
//        }
//        else if(picID == 6) {
//            output1.set(false);
//            output2.set(true);
//            output3.set(true);
//        }
//        else if(picID == 7) {
//            output1.set(true);
//            output2.set(true);
//            output3.set(true);
//        }
    }
}
