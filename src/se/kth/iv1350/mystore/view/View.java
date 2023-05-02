package se.kth.iv1350.mystore.view;

import se.kth.iv1350.mystore.controller.Controller;

/*
Public class view
Representation of the outside interface. this is where calls to
the program originates
 */
public class View {
    private Controller contr;

    /*
    public constructor Controller
    @param Controller
    @return View

    creates instance of View
     */
    public View(Controller contr){
        this.contr = contr;
    }

}
