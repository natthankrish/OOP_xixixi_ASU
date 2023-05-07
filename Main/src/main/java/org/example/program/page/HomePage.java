package org.example.program.page;

import javafx.stage.Screen;
import org.example.program.components.NewImage;
import org.example.program.components.NewLabel;

public class HomePage extends BasePage {
    public HomePage() {

        NewImage logo = new NewImage("assets/logo/1.png");
        double dim = Screen.getPrimary().getVisualBounds().getHeight() * 5 / 30;
        logo.setDimension(dim * 16 / 9, dim);
        logo.setPosition(Screen.getPrimary().getVisualBounds().getWidth() / 20, Screen.getPrimary().getVisualBounds().getHeight() * 3 / 30);
        this.getChildren().add(logo);

        NewLabel productName = new NewLabel("BNMO Point Sales Machine", 75, "#867070", 700);
        productName.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 10);
        productName.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 4 / 15);
        this.getChildren().add(productName);

        NewLabel rights = new NewLabel("All rights reserved to OOP_xixixi_ASU", 30, "#867070", 700);
        rights.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 10);
        rights.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 6 / 15);
        this.getChildren().add(rights);

        NewLabel member = new NewLabel("13521052 Melvin Kent Jonathan\n13521056 Daniel Egiant Sitanggang\n13521068 Ilham Akbar\n13521116 Juan Christopher Santoso\n13521156 Brigita Tri Carolina\n13521162 Antonio Natthan Krishna\n", 30, "#867070", 700);
        member.setLayoutX(Screen.getPrimary().getVisualBounds().getWidth() / 10);
        member.setLayoutY(Screen.getPrimary().getVisualBounds().getHeight() * 7 / 15);
        this.getChildren().add(member);
    }
}
