package se.kth.iv1350.mystore.model;

public class ChangeDTO {
    private final double change;

    ChangeDTO(double change){
        this.change =change;
    }

    public double getChange() {
        return change;
    }
}
