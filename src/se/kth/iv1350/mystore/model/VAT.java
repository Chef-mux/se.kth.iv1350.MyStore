package se.kth.iv1350.mystore.model;

// todo ask about this in handledning
/*
Maybe this class should contain final static CONSTANTS instead of private final constants.
There could be possible benefits.

maybe this particular class could utilize a static method, because nothing will ever change here and
returning a VAT-rate is all it will ever do. is there need for an instance of this class?
 */
public class VAT {
    private final double vat1 = 0.06;
    private final double vat2 = 0.12;
    private final double vat3 = 0.25;
    public VAT(){
    }

    /*
    public method getVat
    @param int type
    @return double Vat multiplier
     */
    public double getVat(int type){
        switch (type){
            case 1:
                return vat1;
            case 2:
                return vat2;
            case 3:
                return vat3;
        }
        return 0; // todo Exception implementation here!
    }
}
