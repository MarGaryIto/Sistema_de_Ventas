/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import views.View_About;
import models.Model_About;
/**
 *
 * @author BLABPC23
 */
public class Controller_About {
    public View_About viewAbout;
    public Model_About modelAbout;

    public Controller_About(View_About viewAbout, Model_About modelAbout) {
        this.viewAbout = viewAbout;
        this.modelAbout = modelAbout;
        initView();
    }

    private void initView() {
        this.viewAbout.setVisible(true);
    }
}

